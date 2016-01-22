package com.dimensiondata.cloud.client;

public class GeographicRegion
{
    private final String id;
    private final String name;
    private final String apiUrl;

    public GeographicRegion(String id, String name, String apiUrl)
    {
        this.id = id;
        this.name = name;
        this.apiUrl = "https://" + apiUrl;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getApiUrl()
    {
        return apiUrl;
    }
}