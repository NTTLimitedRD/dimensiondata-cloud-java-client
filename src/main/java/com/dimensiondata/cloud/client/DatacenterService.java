package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.DatacenterType;
import com.dimensiondata.cloud.client.model.Datacenters;
import com.dimensiondata.cloud.client.model.OperatingSystems;

public interface DatacenterService
{
    Datacenters listDatacenters(int pageSize, int pageNumber, OrderBy orderBy);

    DatacenterType getDatacenter(String id);

    OperatingSystems listOperatingSystems(int pageSize, int pageNumber, OrderBy orderBy);
}