package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.ClientRuntimeException;

public class FailedStateException extends ClientRuntimeException
{
    public FailedStateException(String entityType, String entityId, String failedState)
    {
        super(entityType + " " + entityId + " is in state " + failedState);
    }
}