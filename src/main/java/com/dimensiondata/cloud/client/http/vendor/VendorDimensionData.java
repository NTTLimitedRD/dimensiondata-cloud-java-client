package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorDimensionData implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "api-mea.dimensiondata.com");
    public static final GeographicRegion AsiaPacific_AP = new GeographicRegion("AP", "Asia Pacific", "api-ap.dimensiondata.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "api-au.dimensiondata.com");
    public static final GeographicRegion Canada_CA = new GeographicRegion("CA", "Canada", "api-canada.dimensiondata.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "api-eu.dimensiondata.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "api-na.dimensiondata.com");
    public static final GeographicRegion SouthAmerica_SA = new GeographicRegion("SA", "South America", "api-latam.dimensiondata.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                AsiaPacific_AP,
                Australia_AU,
                Canada_CA,
                Europe_EU,
                NorthAmerica_NA,
                SouthAmerica_SA
        };
    }
}