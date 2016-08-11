package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.Cloud;
import com.dimensiondata.cloud.client.User;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.ResponseType;

import static com.dimensiondata.cloud.client.script.Script.print;
import static com.dimensiondata.cloud.client.script.Script.println;

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

            DeleteAllVlansScript.execute(cloud, networkDomainId);

            DeleteAllPublicIpBlocksScript.execute(cloud, networkDomainId);

            execute(cloud, networkDomainId);
        }
        catch (RequestException e)
        {
            println("ERROR!");
            ResponseType response = e.getResponse();
            println("Operation: " + response.getOperation());
            println("ResponseCode: " + response.getResponseCode());
            println("Message: " + response.getMessage());
            println("Request ID: " + response.getRequestId());
            print("INFO", response.getInfo());
            print("WARN", response.getWarning());
            print("ERROR", response.getError());
            e.printStackTrace();
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
