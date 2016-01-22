package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorRootAxcess implements Vendor
{
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "api.rootaxcesscloud.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] { NorthAmerica_NA };
    }
}
