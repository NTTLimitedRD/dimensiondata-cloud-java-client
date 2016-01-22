package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class NotFoundException extends ClientRuntimeException
{
    public NotFoundException()
    {
        super("Unknown resource");
    }
}