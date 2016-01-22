package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorIndosat implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "iaas-afapi.indosat.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "iaas-auapi.indosat.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "iaas-euapi.indosat.com");
    public static final GeographicRegion Indonesia_ID = new GeographicRegion("ID", "Indonesia", "iaas-api.indosat.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "iaas-usapi.indosat.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                Australia_AU,
                Europe_EU,
                Indonesia_ID,
                NorthAmerica_NA
        };
    }
}