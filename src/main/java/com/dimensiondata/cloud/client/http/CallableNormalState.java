package com.dimensiondata.cloud.client.http;

import java.util.concurrent.Callable;

public class CallableNormalState implements Callable<Boolean>
{
    private final StateService stateService;
    private final String entityType;
    private final String entityId;

    public CallableNormalState(StateService stateService, String entityType, String entityId)
    {
        this.stateService = stateService;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    @Override
    public Boolean call() throws Exception
    {
        return isStateNormal(entityType, entityId, stateService.getState(entityId));
    }

    public static boolean isStateNormal(String entityType, String entityId, String state)
    {
        if (state.startsWith("FAILED_") || state.equals("REQUIRES_SUPPORT"))
        {
            throw new FailedStateException(entityType, entityId, state);
        }
        else if (state.equals("NORMAL"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}