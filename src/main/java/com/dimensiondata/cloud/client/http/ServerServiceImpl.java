package com.dimensiondata.cloud.client.http;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.dimensiondata.cloud.client.ClientRuntimeException;
import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.ServerService;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.model.AddDiskType;
import com.dimensiondata.cloud.client.model.AddNicType;
import com.dimensiondata.cloud.client.model.AntiAffinityRules;
import com.dimensiondata.cloud.client.model.DeployServerType;
import com.dimensiondata.cloud.client.model.NotifyNicIpChangeType;
import com.dimensiondata.cloud.client.model.ReconfigureServerType;
import com.dimensiondata.cloud.client.model.ResponseType;
import com.dimensiondata.cloud.client.model.ServerType;
import com.dimensiondata.cloud.client.model.Servers;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME;

public class ServerServiceImpl extends AbstractRestfulService implements ServerService, StateService
{
    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_SOURCE_IMAGE_ID,
            PARAMETER_DEPLOYED_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME,
            PARAMETER_STATE,
            PARAMETER_STARTED,
            PARAMETER_OPERATING_SYSTEM_ID
            ));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_NETWORK_ID,
            PARAMETER_VLAN_ID,
            PARAMETER_SOURCE_IMAGE_ID,
            PARAMETER_DEPLOYED_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME,
            PARAMETER_STATE,
            PARAMETER_STARTED,
            PARAMETER_OPERATING_SYSTEM_ID,
            PARAMETER_IPV6,
            PARAMETER_PRIVATE_IPV4
            ));

    public static final List<String> RULES_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME
            ));

    public static final List<String> RULES_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_SERVER_ID,
            PARAMETER_NETWORK_ID,
            PARAMETER_ID,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME
            ));

    public ServerServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType deployServer(DeployServerType deployServer)
    {
        return httpClient.post("server/deployServer",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "deployServer"), DeployServerType.class, deployServer),
                ResponseType.class);
    }

    @Override
    public Servers listServers(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("server/server", Servers.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public ServerType getServer(String id)
    {
        return httpClient.get("server/server/" + id, ServerType.class);
    }

    @Override
    public ResponseType deleteServer(String id)
    {
        return httpClient.post("server/deleteServer",
                Entity.xml("<deleteServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType startServer(String id)
    {
        return httpClient.post("server/startServer",
                Entity.xml("<startServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType shutdownServer(String id)
    {
        return httpClient.post("server/shutdownServer",
                Entity.xml("<shutdownServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType rebootServer(String id)
    {
        return httpClient.post("server/rebootServer",
                Entity.xml("<rebootServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType resetServer(String id)
    {
        return httpClient.post("server/resetServer",
                Entity.xml("<resetServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType powerOffServer(String id)
    {
        return httpClient.post("server/powerOffServer",
                Entity.xml("<powerOffServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType updateVmwareTools(String id)
    {
        return httpClient.post("server/updateVmwareTools",
                Entity.xml("<updateVmwareTools xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType addNic(AddNicType addNic)
    {
        return httpClient.post("server/addNic",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "addNic"), AddNicType.class, addNic),
                ResponseType.class);
    }

    @Override
    public ResponseType addDisk(AddDiskType addDisk)
    {
        return httpClient.post("server/addDisk",
            new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "addDisk"), AddDiskType.class, addDisk),
            ResponseType.class);
    }

    @Override
    public ResponseType removeNic(String id)
    {
        return httpClient.post("server/removeNic",
                Entity.xml("<removeNic xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType notifyNicIpChange(NotifyNicIpChangeType notifyNicIpChange)
    {
        return httpClient.post("server/notifyNicIpChange",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "notifyNicIpChange"), NotifyNicIpChangeType.class, notifyNicIpChange),
                ResponseType.class);
    }

    @Override
    public ResponseType cleanFailedServerDeployment(String id)
    {
        return httpClient.post("server/cleanServer",
                Entity.xml("<cleanServer xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public AntiAffinityRules listAntiAffinityRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(RULES_ORDER_BY_PARAMETERS);
        filter.validate(RULES_FILTER_PARAMETERS);

        return httpClient.get("server/antiAffinityRule", AntiAffinityRules.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public ResponseType reconfigureServer(ReconfigureServerType reconfigureServer)
    {
        return httpClient.post("server/reconfigureServer",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "reconfigureServer"), ReconfigureServerType.class, reconfigureServer),
                ResponseType.class);
    }

    @Override
    public ResponseType upgradeVirtualHardware(String id)
    {
        return httpClient.post("server/upgradeVirtualHardware",
                Entity.xml("<upgradeVirtualHardware xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public void disableServerBackup(String id)
    {
        Client client = ClientBuilder.newClient();
        client = client.register(HttpAuthenticationFeature.basicBuilder().build());
        WebTarget target = client.target(baseUrl)
                .path("oec")
                .path("0.9")
                .path(UserSession.get().getOrgId())
                .path("server")
                .path(id)
                .path("backup")
                .queryParam("disable");

        Invocation.Builder request = target.request()
                .property(HTTP_AUTHENTICATION_BASIC_USERNAME, UserSession.get().getUser())
                .property(HTTP_AUTHENTICATION_BASIC_PASSWORD, UserSession.get().getPassword());
        Response response = request.get();
        try
        {
            if (response.getStatus() == 400)
            {
                String xmlResponse = response.readEntity(String.class);
                throw new ClientRuntimeException("Unable to Disable Backup for Server " + id + ":\n" + xmlResponse);
            }
        }
        finally
        {
            response.close();
        }
    }

    @Override
    public String getIdFromDeployResponse(ResponseType response)
    {
        assertParameterEquals("Operation", "DEPLOY", response.getOperation());
        assertParameterEquals("ResponseCode", "IN_PROGRESS", response.getResponseCode());
        return findRequiredNameValuePair("serverId", response.getInfo()).getValue();
    }

    @Override
    public Callable<Boolean> isServerInNormalState(String id)
    {
        return new CallableNormalState(this, "server", id);
    }

    @Override
    public Callable<Boolean> isServerDeleted(String id)
    {
        return new CallableDeletedState(this, "server", id);
    }

    @Override
    public String getState(String id)
    {
        return getServer(id).getState();
    }
}