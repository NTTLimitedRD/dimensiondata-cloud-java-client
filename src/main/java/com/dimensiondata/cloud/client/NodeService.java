package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface NodeService extends StateService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_NAME = "name";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_IPV4ADDRESS = "ipv4Address";
    String PARAMETER_IPV6ADDRESS = "ipv6Address";

    ResponseType createNode(CreateNodeType createNode);

    Nodes listNodes(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NodeType getNode(String id);

    ResponseType editNode(EditNode editNode);

    ResponseType deleteNode(String id);
}