package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.VirtualListenerService;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VirtualListenerServiceImpl extends AbstractRestfulService implements VirtualListenerService
{
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_DATACENTER_ID = "datacenterId";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_ENABLED = "enabled";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_TYPE = "type";
    public static final String PARAMETER_PROTOCOL = "protocol";
    public static final String PARAMETER_LISTENER_IP_ADDRESS = "listenerIpAddress";
    public static final String PARAMETER_PORT = "port";
    public static final String PARAMETER_POOL_ID = "poolId";
    public static final String PARAMETER_CLIENT_CLONE_POOL_ID = "clientClonePoolId";
    public static final String PARAMETER_PERSISTENCE_PROFILE_ID = "persistenceProfileId";
    public static final String PARAMETER_FALLBACK_PERSISTENCE_PROFILE_ID = "fallbackPersistenceProfileId";

    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME
            ));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_ENABLED,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME,
            PARAMETER_TYPE,
            PARAMETER_PROTOCOL,
            PARAMETER_LISTENER_IP_ADDRESS,
            PARAMETER_PORT,
            PARAMETER_POOL_ID,
            PARAMETER_CLIENT_CLONE_POOL_ID,
            PARAMETER_PERSISTENCE_PROFILE_ID,
            PARAMETER_FALLBACK_PERSISTENCE_PROFILE_ID
            ));

    public VirtualListenerServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType createVirtualListener(CreateVirtualListener createVirtualListener)
    {
        return httpClient.post("networkDomainVip/createVirtualListener",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createVirtualListener"), CreateVirtualListener.class, createVirtualListener),
                ResponseType.class);
    }

    @Override
    public VirtualListeners listVirtualListeners(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/virtualListener", VirtualListeners.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public VirtualListenerType getVirtualListener(String id)
    {
        return httpClient.get("networkDomainVip/virtualListener/" + id, VirtualListenerType.class);
    }

    @Override
    public ResponseType editVirtualListener(EditVirtualListener editVirtualListener)
    {
        return httpClient.post("networkDomainVip/editVirtualListener",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editVirtualListener"), EditVirtualListener.class, editVirtualListener),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteVirtualListener(String id)
    {
        return httpClient.post("networkDomainVip/deleteVirtualListener",
                Entity.xml("<deleteVirtualListener xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }
}