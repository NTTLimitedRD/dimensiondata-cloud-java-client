package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class ServiceUnavailableException extends ClientRuntimeException
{
    public ServiceUnavailableException()
    {
        super("System is down for scheduled maintenance");
    }
}