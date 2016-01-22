package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface VlanService
{
    ResponseType deployVlan(DeployVlanType deployVlan);

    Vlans listVlans(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    VlanType getVlan(String id);

    ResponseType editVlan(EditVlanType editVlan);

    ResponseType deleteVlan(String id);

    ResponseType expandVlan(ExpandVlanType expandVlan);
}
