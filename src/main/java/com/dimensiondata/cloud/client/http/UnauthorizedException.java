package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class UnauthorizedException extends ClientRuntimeException
{
    public UnauthorizedException()
    {
        super("User not authorized");
    }
}
