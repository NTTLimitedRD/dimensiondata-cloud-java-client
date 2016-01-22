package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorDimensionDataGovernment implements Vendor
{
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "api-canberra.dimensiondata.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Australia_AU
        };
    }
}