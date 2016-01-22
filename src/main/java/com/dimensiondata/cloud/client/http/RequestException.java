package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;
import com.dimensiondata.cloud.client.model.ResponseType;

public class RequestException extends ClientRuntimeException
{
    private final ResponseType response;

    public RequestException(ResponseType response)
    {
        super(response.getMessage());
        this.response = response;
    }

    public ResponseType getResponse()
    {
        return response;
    }
}