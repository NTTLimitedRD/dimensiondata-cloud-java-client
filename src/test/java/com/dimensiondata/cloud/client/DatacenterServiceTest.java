package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.DatacenterServiceImpl;
import com.dimensiondata.cloud.client.model.DatacenterType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DatacenterServiceTest extends AbstractServiceTest
{
    @Test
    public void testList()
    {
        DatacenterService datacenterService = new DatacenterServiceImpl(getBaseUrl());
        List<DatacenterType> datacenters = datacenterService.listDatacenters(10, 1, OrderBy.EMPTY).getDatacenter();
        Assert.assertEquals(3, datacenters.size());
        Assert.assertEquals("DEV_NA1", datacenters.get(0).getId());
        Assert.assertEquals("DEV_NA3", datacenters.get(1).getId());
        Assert.assertEquals("DEV_NET2", datacenters.get(2).getId());
    }

    @Test
    public void testListDescending()
    {
        DatacenterService datacenterService = new DatacenterServiceImpl(getBaseUrl());
        List<DatacenterType> datacenters = datacenterService.listDatacenters(10, 1, new OrderBy(DatacenterServiceImpl.PARAMETER_ID + OrderBy.DESCENDING_SUFFIX)).getDatacenter();
        Assert.assertEquals(3, datacenters.size());
        Assert.assertEquals("DEV_NET2", datacenters.get(0).getId());
        Assert.assertEquals("DEV_NA3", datacenters.get(1).getId());
        Assert.assertEquals("DEV_NA1", datacenters.get(2).getId());
    }

    @Test
    public void testGet()
    {
        DatacenterService datacenterService = new DatacenterServiceImpl(getBaseUrl());
        DatacenterType datacenterType = datacenterService.getDatacenter("DEV_NA1");
        Assert.assertEquals("DEV_NA1", datacenterType.getId());
    }
}