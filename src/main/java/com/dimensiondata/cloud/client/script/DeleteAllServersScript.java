package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.ServerType;
import com.dimensiondata.cloud.client.model.Servers;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;


public class DeleteAllServersScript
{
    static void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(ServerService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Servers servers = cloud.server().listServers(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("Servers to delete: " + servers.getTotalCount());
        while (servers.getTotalCount() > 0)
        {
            deleteServers(cloud, servers.getServer());
            servers = cloud.server().listServers(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteServers(Cloud cloud, List<ServerType> servers)
    {
        // check all servers are in a NORMAL state
        for (ServerType server : servers)
        {
            if (!server.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("Server not in NORMAL state: " + server.getId());
            }
        }

        for (ServerType server : servers)
        {
            // disable backup
            if (server.getBackup() != null)
            {
                cloud.server().disableServerBackup(server.getId());
                awaitUntil("Disabling Backup for Server " + server.getId(), () ->
                {
                    ServerType updatedServer = cloud.server().getServer(server.getId());
                    return updatedServer.getBackup() == null && updatedServer.getState().equals(NORMAL_STATE);
                });
            }

            // disable monitoring
            if (server.getMonitoring() != null)
            {
                cloud.monitoring().disableServerMonitoring(server.getId());
                awaitUntil("Disabling Monitoring for Server " + server.getId(), () ->
                {
                    ServerType updatedServer = cloud.server().getServer(server.getId());
                    return updatedServer.getMonitoring() == null && updatedServer.getState().equals(NORMAL_STATE);
                });
            }

            // power off
            if (server.isStarted())
            {
                cloud.server().powerOffServer(server.getId());
                awaitUntil("Powering Off Server " + server.getId(), () -> !cloud.server().getServer(server.getId()).isStarted());
            }

            // TODO Consistency Groups (run a different script previous to this one and not execute this script if server is a in CG?)

            // delete server
            cloud.server().deleteServer(server.getId());
            awaitUntil("Deleting Server " + server.getId(), cloud.server().isServerDeleted(server.getId()));
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