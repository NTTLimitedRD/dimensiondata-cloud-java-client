package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.PortListType;
import com.dimensiondata.cloud.client.model.PortLists;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllPortListsScript
{
    static void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(FirewallService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        PortLists portLists = cloud.firewall().listPortLists(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("PortLists to delete: " + portLists.getTotalCount());
        while (portLists.getTotalCount() > 0)
        {
            deletePortLists(cloud, portLists.getPortList());
            portLists = cloud.firewall().listPortLists(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deletePortLists(Cloud cloud, List<PortListType> portLists)
    {
        // check all items are in a NORMAL state
        for (PortListType portList : portLists)
        {
            if (!portList.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("PortList not in NORMAL state: " + portList.getId());
            }
        }

        for (PortListType portList : portLists)
        {
            try
            {
                cloud.firewall().deletePortList(portList.getId());
                awaitUntil("Deleting PortList " + portList.getId(), new CallableDeletedState(
                        id -> cloud.firewall().getPortList(id).getState(), "portList", portList.getId()));
            }
            catch (RequestException e)
            {
                if ("HAS_DEPENDENCY".equals(e.getResponse().getResponseCode()))
                {
                    // TODO if in the first page we get only items that trigger this error, we won't exit the loop
                    print(e);
                    println("Unable to delete PortList " + portList.getId() + " since it's in use");
                }
                else
                {
                    // something is wrong
                    throw e;
                }
            }
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
        catch (RequestException e)
        {
            print(e);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }
}
