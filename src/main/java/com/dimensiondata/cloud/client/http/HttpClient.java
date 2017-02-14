package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.model.ResponseType;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME;

public class HttpClient
{
    public static final String API_VERSION = "2.4";
    public static final String DEFAULT_NAMESPACE = "urn:didata.com:api:cloud:types";
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_XML_TYPE;
    private static final JAXBContext JAXB_CONTEXT;
    private final WebTarget baseTarget;

    static
    {
        try
        {
            JAXB_CONTEXT = JAXBContext.newInstance("com.dimensiondata.cloud.client.model");
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }


    public HttpClient(String baseUrl)
    {
        Client client = ClientBuilder.newClient();
        client = client.register(HttpAuthenticationFeature.basicBuilder().build());
        baseTarget = client.target(baseUrl).path("caas");
    }

    private static void checkApiResponseStatus(int responseStatus)
    {
        if (responseStatus == 401)
        {
            throw new UnauthorizedException();
        }
        else if (responseStatus == 403)
        {
            throw new ForbiddenException();
        }
        else if (responseStatus == 404)
        {
            throw new NotFoundException();
        }
        else if (responseStatus == 500 || responseStatus == 503)
        {
            throw new ServiceUnavailableException();
        }
        // status 400 has different meaning between api 1 and 2
    }

    public static void checkApi1ResponseStatus(int responseStatus)
    {
        checkApiResponseStatus(responseStatus);
        if (responseStatus == 400)
        {
            throw new ServiceUnavailableException();
        }
    }

    private static void checkApi2ResponseStatus(Response response)
    {
        try
        {
            checkApiResponseStatus(response.getStatus());
        }
        catch (ClientRuntimeException e)
        {
            response.close();
            throw e;
        }

        if (response.getStatus() == 400)
        {
            ResponseType responseType = response.readEntity(ResponseType.class);
            // response is closed after reading Entity
            throw new RequestException(responseType);
        }
    }

    private static <T> T get(WebTarget baseTarget, String path, Map<String,List<String>> parameters, Class<T> responseType)
    {
        Response response = get(baseTarget, path, parameters);
        UserSession.setLastResponse(response);
        checkApi2ResponseStatus(response);
        return response.readEntity(responseType);
    }

    private static Response get(WebTarget baseTarget, String path, Map<String,List<String>> parameters)
    {
        WebTarget target = baseTarget.path(API_VERSION)
                .path(UserSession.get().getOrgId())
                .path(path);

        if (parameters != null)
        {
            for (String name : parameters.keySet())
            {
                List<String> values = parameters.get(name);
                target = target.queryParam(name, (Object[]) values.toArray(new String[values.size()]));
            }
        }

        return createRequest(target).get();
    }

    public <T> T get(String path, Class<T> responseType, Param... params)
    {
        return HttpClient.get(baseTarget, path, getParameters(params), responseType);
    }

    private Map<String,List<String>> getParameters(Param ... params)
    {
        Map<String,List<String>> parameters = new HashMap<>();
        for (Param param : params)
        {
            parameters.put(param.getName(), Collections.singletonList(param.getValue()));
        }
        return parameters;
    }

    private static <T> T post(WebTarget baseTarget, String path, Entity payload, Class<T> responseType)
    {
        WebTarget target = baseTarget.path(API_VERSION)
                .path(UserSession.get().getOrgId())
                .path(path);

        Response response = createRequest(target).post(payload);
        UserSession.setLastResponse(response);
        checkApi2ResponseStatus(response);
        return response.readEntity(responseType);
    }

    public <T> T post(String path, JAXBElement jaxbElement, Class<T> responseType)
    {
        try
        {
            Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(jaxbElement, stringWriter);
            return post(path, Entity.xml(stringWriter.toString()), responseType);
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

    public <T> T post(String path, Entity payload, Class<T> responseType)
    {
        return HttpClient.post(baseTarget, path, payload, responseType);
    }

    private static Invocation.Builder createRequest(WebTarget target)
    {
        Invocation.Builder request = target.request(MEDIA_TYPE);
        request.property(HTTP_AUTHENTICATION_BASIC_USERNAME, UserSession.get().getUser())
                .property(HTTP_AUTHENTICATION_BASIC_PASSWORD, UserSession.get().getPassword());
        return request;
    }
}