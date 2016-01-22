package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.DatacenterService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.DatacenterType;
import com.dimensiondata.cloud.client.model.Datacenters;
import com.dimensiondata.cloud.client.model.OperatingSystems;

import java.util.Collections;
import java.util.List;

public class DatacenterServiceImpl extends AbstractRestfulService implements DatacenterService
{
    public static final String PARAMETER_ID = "id";
    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Collections.singletonList(PARAMETER_ID));


    public DatacenterServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public Datacenters listDatacenters(int pageSize, int pageNumber, OrderBy orderBy)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        return httpClient.get("infrastructure/datacenter", Datacenters.class,
                new Param(Param.PAGE_SIZE, pageSize),
                new Param(Param.PAGE_NUMBER, pageNumber),
                new Param(Param.ORDER_BY, orderBy.concatenateParameters()));
    }

    @Override
    public DatacenterType getDatacenter(String id)
    {
        Datacenters datacenters = httpClient.get("infrastructure/datacenter", Datacenters.class, new Param(PARAMETER_ID, id));
        return datacenters.getDatacenter().size() > 0 ? datacenters.getDatacenter().get(0) : null;
    }

    @Override
    public OperatingSystems listOperatingSystems(int pageSize, int pageNumber, OrderBy orderBy)
    {
        // TODO validate parameters
        return httpClient.get("infrastructure/operatingSystem", OperatingSystems.class,
                new Param(Param.PAGE_SIZE, pageSize),
                new Param(Param.PAGE_NUMBER, pageNumber),
                new Param(Param.ORDER_BY, orderBy.concatenateParameters()));
    }
}