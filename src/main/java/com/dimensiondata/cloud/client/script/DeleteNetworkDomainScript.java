package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.Cloud;
import com.dimensiondata.cloud.client.User;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;

import java.util.Map;
import java.util.HashMap;

import static com.dimensiondata.cloud.client.script.Script.awaitUntil;
import static com.dimensiondata.cloud.client.script.Script.print;

public class DeleteNetworkDomainScript implements NetworkDomainScript
{
    public static void main(String[] args)
    {
        Map<String,NetworkDomainScript> scripts = new HashMap<>();
        scripts.put("firewallRules", new DeleteAllFirewallRulesScript());
        scripts.put("ipAddressLists", new DeleteAllIpAddressListsScript());
        scripts.put("natRules", new DeleteAllNatRulesScript());
        scripts.put("nodes", new DeleteAllNodesScript());
        scripts.put("pools", new DeleteAllPoolsScript());
        scripts.put("portLists", new DeleteAllPortListsScript());
        scripts.put("publicIpBlocks", new DeleteAllPublicIpBlocksScript());
        scripts.put("servers", new DeleteAllServersScript());
        scripts.put("virtualListeners", new DeleteAllVirtualListenersScript());
        scripts.put("vlans", new DeleteAllVlansScript());
        scripts.put("networkDomain", new DeleteNetworkDomainScript());
        scripts.put("ALL", (cloud, networkDomainId) ->
        {
            new DeleteAllServersScript().execute(cloud, networkDomainId);

            new DeleteAllFirewallRulesScript().execute(cloud, networkDomainId);
            new DeleteAllIpAddressListsScript().execute(cloud, networkDomainId);
            new DeleteAllPortListsScript().execute(cloud, networkDomainId);

            new DeleteAllVirtualListenersScript().execute(cloud, networkDomainId);

            new DeleteAllPoolsScript().execute(cloud, networkDomainId);
            new DeleteAllNatRulesScript().execute(cloud, networkDomainId);
            new DeleteAllNodesScript().execute(cloud, networkDomainId);

            new DeleteAllVlansScript().execute(cloud, networkDomainId);

            new DeleteAllPublicIpBlocksScript().execute(cloud, networkDomainId);

            new DeleteNetworkDomainScript().execute(cloud, networkDomainId);
        });

        if (args.length < 5)
        {
            System.out.println("required parameters: [api url] [user] [password] [networkdomain id] [target]");
            System.exit(-1);
        }

        String url = args[0];
        String user = args[1];
        String password = args[2];
        String networkDomainId = args[3];
        String operation = args[4];

        if (!scripts.containsKey(operation))
        {
            System.out.println("ERROR: invalid Target. Targets available:");
            scripts.keySet().stream().sorted().forEach(System.out::println);
            System.exit(-1);
        }

        try
        {
            UserSession.set(new User(user, password));
            Cloud cloud = new CloudImpl(url);
            scripts.get(operation).execute(cloud, networkDomainId);
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

    @Override
    public void execute(Cloud cloud, String networkDomainId)
    {
        cloud.networkDomain().deleteNetworkDomain(networkDomainId);
        awaitUntil("Deleting NetworkDomain " + networkDomainId, new CallableDeletedState(cloud.networkDomain(), "networkDomain", networkDomainId));
    }
}
