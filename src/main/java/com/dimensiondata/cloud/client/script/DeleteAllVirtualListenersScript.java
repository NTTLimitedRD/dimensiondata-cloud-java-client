package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.model.VirtualListenerType;
import com.dimensiondata.cloud.client.model.VirtualListeners;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllVirtualListenersScript
{
    static void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(VirtualListenerService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        VirtualListeners virtualListeners = cloud.virtualListener().listVirtualListeners(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        while (virtualListeners.getTotalCount() > 0)
        {
            deleteVirtualListeners(cloud, virtualListeners.getVirtualListener());
            virtualListeners = cloud.virtualListener().listVirtualListeners(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteVirtualListeners(Cloud cloud, List<VirtualListenerType> virtualListeners)
    {
        // check all items are in a NORMAL state
        for (VirtualListenerType virtualListener : virtualListeners)
        {
            if (!virtualListener.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("VirtualListener not in NORMAL state: " + virtualListener.getId());
            }
        }

        for (VirtualListenerType virtualListener : virtualListeners)
        {
            cloud.virtualListener().deleteVirtualListener(virtualListener.getId());
            awaitUntil("Deleting VirtualListener " + virtualListener.getId(), new CallableDeletedState(cloud.virtualListener(), "virtualListener", virtualListener.getId()));
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
