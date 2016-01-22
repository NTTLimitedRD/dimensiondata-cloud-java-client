package com.dimensiondata.cloud.client.http.vendor;

import com.dimensiondata.cloud.client.GeographicRegion;
import com.dimensiondata.cloud.client.Vendor;

public class VendorInternetSolutions implements Vendor
{
    public static final GeographicRegion Africa_AF = new GeographicRegion("AF", "Africa", "meaapi.cloud.is.co.za");
    public static final GeographicRegion AsiaPacific_AP = new GeographicRegion("AP", "Asia Pacific", "apapi.cloud.is.co.za");
    public static final GeographicRegion Australia_AU = new GeographicRegion("AU", "Australia", "auapi.cloud.is.co.za");
    public static final GeographicRegion Canada_CA = new GeographicRegion("CA", "Canada", "canadaapi.cloud.is.co.za");
    public static final GeographicRegion Europe_EU = new GeographicRegion("EU", "Europe", "euapi.cloud.is.co.za");
    public static final GeographicRegion NorthAmerica_NA = new GeographicRegion("NA", "North America", "usapi.cloud.is.co.za");
    public static final GeographicRegion SouthAmerica_SA = new GeographicRegion("SA", "South America", "latamapi.cloud.is.co.za");

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
