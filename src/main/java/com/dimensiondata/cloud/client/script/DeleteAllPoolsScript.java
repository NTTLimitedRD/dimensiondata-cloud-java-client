package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.model.PoolType;
import com.dimensiondata.cloud.client.model.Pools;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllPoolsScript
{
    static void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(PoolService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Pools pools = cloud.pool().listPools(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
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

    public static void main(String[] args)
    {
        if (args.length < 4)
        {
            System.out.println("required parameters: [api url] [user] [password] [networkdomain id]");
            System.exit(-1);
        }

        String url = args[0];
        String user = args[1];
        String password = args[2];
        String networkDomainId = args[3];

        try
        {
            UserSession.set(new User(user, password));
            Cloud cloud = new CloudImpl(url);
            execute(cloud, networkDomainId);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }
}
