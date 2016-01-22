package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME;

public class CloudImpl implements Cloud
{
    private final DatacenterService datacenter;
    private final NetworkDomainService networkDomain;
    private final VlanService vlan;
    private final IpAddressService ipAddress;
    private final FirewallService firewall;
    private final NatService nat;
    private final ServerService server;
    private final MonitoringService monitoring;
    private final NodeService node;
    private final PoolService pool;
    private final SupportService support;
    private final VirtualListenerService virtualListener;
    private final ImageService image;
    private final SecurityGroupService securityGroup;

    public CloudImpl(String baseUrl)
    {
        datacenter = new DatacenterServiceImpl(baseUrl);
        networkDomain = new NetworkDomainServiceImpl(baseUrl);
        vlan = new VlanServiceImpl(baseUrl);
        ipAddress = new IpAddressServiceImpl(baseUrl);
        firewall = new FirewallServiceImpl(baseUrl);
        nat = new NatServiceImpl(baseUrl);
        server = new ServerServiceImpl(baseUrl);
        monitoring = new MonitoringServiceImpl(baseUrl);
        node = new NodeServiceImpl(baseUrl);
        pool = new PoolServiceImpl(baseUrl);
        support = new SupportServiceImpl(baseUrl);
        virtualListener = new VirtualListenerServiceImpl(baseUrl);
        image = new ImageServiceImpl(baseUrl);
        securityGroup = new SecurityGroupServiceImpl(baseUrl);

        if (UserSession.get().getOrgId() == null)
        {
            UserSession.get().setOrgId(findUserOrgId(baseUrl, UserSession.get().getUser(), UserSession.get().getPassword()));
        }
    }

    private String findUserOrgId(String baseUrl, String user, String password)
    {
        Client client = ClientBuilder.newClient();
        client = client.register(HttpAuthenticationFeature.basicBuilder().build());
        WebTarget target = client.target(baseUrl).path("oec").path("0.9").path("myaccount");
        Invocation.Builder request = target.request()
                .property(HTTP_AUTHENTICATION_BASIC_USERNAME, user)
                .property(HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
        Response response = request.get();
        try
        {
            HttpClient.checkApi1ResponseStatus(response.getStatus());

            String xmlResponse = response.readEntity(String.class);
            try
            {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                documentBuilderFactory.setNamespaceAware(true);
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                InputSource inputSource = new InputSource();
                inputSource.setCharacterStream(new StringReader(xmlResponse));
                Document document = documentBuilder.parse(inputSource);
                return document.getElementsByTagNameNS("http://oec.api.opsource.net/schemas/directory", "orgId").item(0).getTextContent();
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                throw new ClientRuntimeException(e);
            }
        }
        finally
        {
            response.close();
        }
    }

    @Override
    public DatacenterService datacenter()
    {
        return datacenter;
    }

    @Override
    public NetworkDomainService networkDomain()
    {
        return networkDomain;
    }

    @Override
    public VlanService vlan()
    {
        return vlan;
    }

    @Override
    public IpAddressService ipAddress()
    {
        return ipAddress;
    }

    @Override
    public FirewallService firewall()
    {
        return firewall;
    }

    @Override
    public NatService nat()
    {
        return nat;
    }

    @Override
    public ServerService server()
    {
        return server;
    }

    @Override
    public MonitoringService monitoring()
    {
        return monitoring;
    }

    @Override
    public NodeService node()
    {
        return node;
    }

    @Override
    public PoolService pool()
    {
        return pool;
    }

    @Override
    public SupportService support()
    {
        return support;
    }

    @Override
    public VirtualListenerService virtualListener()
    {
        return virtualListener;
    }

    @Override
    public ImageService image()
    {
        return image;
    }

    @Override
    public SecurityGroupService securityGroup()
    {
        return securityGroup;
    }
}