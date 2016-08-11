package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface VirtualListenerService extends StateService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_NAME = "name";
    String PARAMETER_ENABLED = "enabled";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_TYPE = "type";
    String PARAMETER_PROTOCOL = "protocol";
    String PARAMETER_LISTENER_IP_ADDRESS = "listenerIpAddress";
    String PARAMETER_PORT = "port";
    String PARAMETER_POOL_ID = "poolId";
    String PARAMETER_CLIENT_CLONE_POOL_ID = "clientClonePoolId";
    String PARAMETER_PERSISTENCE_PROFILE_ID = "persistenceProfileId";
    String PARAMETER_FALLBACK_PERSISTENCE_PROFILE_ID = "fallbackPersistenceProfileId";

    ResponseType createVirtualListener(CreateVirtualListener createVirtualListener);

    VirtualListeners listVirtualListeners(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    VirtualListenerType getVirtualListener(String id);

    ResponseType editVirtualListener(EditVirtualListener editVirtualListener);

    ResponseType deleteVirtualListener(String id);
}