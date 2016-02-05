package com.dimensiondata.cloud.client.http;

import java.util.concurrent.Callable;

import static com.dimensiondata.cloud.client.http.CallableNormalState.isStateNormal;

public class CallableDeletedState implements Callable<Boolean>
{
    private final StateService stateService;
    private final String entityType;
    private final String entityId;

    public CallableDeletedState(StateService stateService, String entityType, String entityId)
    {
        this.stateService = stateService;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    @Override
    public Boolean call() throws Exception
    {
        try
        {
            isStateNormal(entityType, entityId, stateService.getState(entityId));
            return false;
        }
        catch (RequestException e)
        {
            if ("RESOURCE_NOT_FOUND".equals(e.getResponse().getResponseCode()))
            {
                return true;
            }
            else
            {
                throw e;
            }
        }
    }
}
