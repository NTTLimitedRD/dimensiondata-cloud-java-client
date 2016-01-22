package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorBsnl implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "afapi.bsnlcloud.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "auapi.bsnlcloud.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "euapi.bsnlcloud.com");
    public static final GeographicRegion India_IN = new GeographicRegion("IN", "India", "api.bsnlcloud.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "usapi.bsnlcloud.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                Australia_AU,
                Europe_EU,
                India_IN,
                NorthAmerica_NA
        };
    }
}