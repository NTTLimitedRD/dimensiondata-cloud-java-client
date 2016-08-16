package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.model.PoolType;
import com.dimensiondata.cloud.client.model.Pools;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllPoolsScript implements NetworkDomainScript
{
    @Override
    public void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(PoolService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Pools pools = cloud.pool().listPools(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("Pools to delete: " + pools.getTotalCount());
        while (pools.getTotalCount() > 0)
        {
            deletePools(cloud, pools.getPool());
            pools = cloud.pool().listPools(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deletePools(Cloud cloud, List<PoolType> pools)
    {
        // check all items are in a NORMAL state
        for (PoolType pool : pools)
        {
            if (!pool.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("Pool not in NORMAL state: " + pool.getId());
            }
        }

        for (PoolType pool : pools)
        {
            cloud.pool().deletePool(pool.getId());
            awaitUntil("Deleting Pool " + pool.getId(), new CallableDeletedState(cloud.pool(), "pool", pool.getId()));
        }
    }
}
