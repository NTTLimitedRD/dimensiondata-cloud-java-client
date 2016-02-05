package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class InvalidParameterException extends ClientRuntimeException
{
    public InvalidParameterException(String message)
    {
        super(message);
    }
}
