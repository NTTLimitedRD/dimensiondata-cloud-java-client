package com.dimensiondata.cloud.client;

public class ClientRuntimeException extends RuntimeException
{
    public ClientRuntimeException(String message)
    {
        super(message);
    }

    public ClientRuntimeException(Throwable cause)
    {
        super(cause);
    }
}
