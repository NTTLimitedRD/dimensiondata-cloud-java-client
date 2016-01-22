package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorMedOne implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "api-af.cloud.med-1.com");
    public static final GeographicRegion AsiaPacific_AP = new GeographicRegion("AP", "Asia Pacific", "api-ap.cloud.med-1.com");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "api-au.cloud.med-1.com");
    public static final GeographicRegion Canada_CA = new GeographicRegion("CA", "Canada", "api-ca.cloud.med-1.com");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "api-eu.cloud.med-1.com");
    public static final GeographicRegion Israel_IL = new GeographicRegion("IL", "Israel", "api.cloud.med-1.com");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "api-na.cloud.med-1.com");
    public static final GeographicRegion SouthAmerica_SA = new GeographicRegion("SA", "South America", "api-sa.cloud.med-1.com");

    @Override
    public GeographicRegion[] getGeographicRegions()
    {
        return new GeographicRegion[] {
                Africa_AF,
                AsiaPacific_AP,
                Australia_AU,
                Canada_CA,
                Europe_EU,
                Israel_IL,
                NorthAmerica_NA,
                SouthAmerica_SA
        };
    }
}