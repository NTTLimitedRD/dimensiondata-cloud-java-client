package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface VirtualListenerService
{
    ResponseType createVirtualListener(CreateVirtualListener createVirtualListener);

    VirtualListeners listVirtualListeners(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    VirtualListenerType getVirtualListener(String id);

    ResponseType editVirtualListener(EditVirtualListener editVirtualListener);

    ResponseType deleteVirtualListener(String id);
}