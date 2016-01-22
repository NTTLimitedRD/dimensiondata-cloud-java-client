package com.dimensiondata.cloud.client.http;

public class AbstractRestfulService
{
    protected final String baseUrl;

    protected final HttpClient httpClient;

    public AbstractRestfulService(String baseUrl)
    {
        this.baseUrl = baseUrl;
        this.httpClient = new HttpClient(baseUrl);
    }
}
