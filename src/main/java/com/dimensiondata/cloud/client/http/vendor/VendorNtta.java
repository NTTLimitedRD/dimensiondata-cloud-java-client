package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorNtta implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "sacloudapi.nttamerica.com");
    public static final GeographicRegion AsiaPacific_AP = new GeographicRegion("AP", "Asia Pacific", "hkcloudapi.nttamerica.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "aucloudapi.nttamerica.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "eucloudapi.nttamerica.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "cloudapi.nttamerica.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                AsiaPacific_AP,
                Australia_AU,
                Europe_EU,
                NorthAmerica_NA
        };
    }
}