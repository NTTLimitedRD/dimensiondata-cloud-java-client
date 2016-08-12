package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.Cloud;
import com.dimensiondata.cloud.client.User;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;

import static com.dimensiondata.cloud.client.script.Script.awaitUntil;
import static com.dimensiondata.cloud.client.script.Script.print;

public class DeleteNetworkDomainScript
{
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

            DeleteAllServersScript.execute(cloud, networkDomainId);

            DeleteAllFirewallRulesScript.execute(cloud, networkDomainId);
            DeleteAllIpAddressListsScript.execute(cloud, networkDomainId);
            DeleteAllPortListsScript.execute(cloud, networkDomainId);

            DeleteAllVirtualListenersScript.execute(cloud, networkDomainId);

            DeleteAllPoolsScript.execute(cloud, networkDomainId);
            DeleteAllNatRulesScript.execute(cloud, networkDomainId);
            DeleteAllNodesScript.execute(cloud, networkDomainId);

            DeleteAllVlansScript.execute(cloud, networkDomainId);

            DeleteAllPublicIpBlocksScript.execute(cloud, networkDomainId);

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

    private static void execute(Cloud cloud, String networkDomainId)
    {
        cloud.networkDomain().deleteNetworkDomain(networkDomainId);
        awaitUntil("Deleting NetworkDomain " + networkDomainId, new CallableDeletedState(cloud.networkDomain(), "networkDomain", networkDomainId));
    }
}
