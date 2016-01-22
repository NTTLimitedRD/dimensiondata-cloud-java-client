package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorCisco implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "iaas-api-mea.cisco-ccs.com");
    public static final GeographicRegion AsiaPacific_AP = new GeographicRegion("AP", "Asia Pacific", "iaas-api-ap.cisco-ccs.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "iaas-api-au.cisco-ccs.com");
    public static final GeographicRegion Canada_CA = new GeographicRegion("CA", "Canada", "iaas-api-ca.cisco-ccs.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "iaas-api-eu.cisco-ccs.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "iaas-api-na.cisco-ccs.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                AsiaPacific_AP,
                Australia_AU,
                Canada_CA,
                Europe_EU,
                NorthAmerica_NA
        };
    }
}