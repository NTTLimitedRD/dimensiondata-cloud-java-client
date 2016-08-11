package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.Cloud;
import com.dimensiondata.cloud.client.User;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.http.CloudImpl;

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

            // TODO delete CGs

            DeleteAllServersScript.execute(cloud, networkDomainId);

            DeleteAllFirewallRulesScript.execute(cloud, networkDomainId);

            DeleteAllVirtualListenersScript.execute(cloud, networkDomainId);
            DeleteAllPoolsScript.execute(cloud, networkDomainId);
            DeleteAllNatRulesScript.execute(cloud, networkDomainId);
            DeleteAllNodesScript.execute(cloud, networkDomainId);

            execute(cloud, networkDomainId);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    private static void execute(Cloud cloud, String networkDomainId)
    {
        // TODO delete Network Domain
    }
}
