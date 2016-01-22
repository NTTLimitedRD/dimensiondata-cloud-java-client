package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface NodeService
{
    ResponseType createNode(CreateNodeType createNode);

    Nodes listNodes(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NodeType getNode(String id);

    ResponseType editNode(EditNode editNode);

    ResponseType deleteNode(String id);
}