package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class ForbiddenException extends ClientRuntimeException
{
    public ForbiddenException()
    {
        super("Invalid role or invalid {org-id} (does not match credentials)");
    }
}