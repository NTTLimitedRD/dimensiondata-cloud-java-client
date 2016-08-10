package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.model.ServerType;
import com.dimensiondata.cloud.client.model.Servers;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;


public class DeleteAllServersApp
{
    private static final String NORMAL_STATE = "NORMAL";
    private static final int MAX_MINUTES_WAIT = 10;
    private static final int POLL_DELAY_SECONDS = 30;

    static void execute(String baseUrl, String networkDomainId)
    {
        Cloud cloud = new CloudImpl(baseUrl);

        int pageSize = 20;
        Filter filter = new Filter(new Param(ServerService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Servers servers = cloud.server().listServers(pageSize, 1, OrderBy.EMPTY, filter);
        while (servers.getTotalCount() > 0)
        {
            deleteServers(cloud, servers.getServer());
            servers = cloud.server().listServers(pageSize, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void awaitUntil(String conditionDescription, Callable<Boolean> conditionEvaluator)
    {
        User user = UserSession.get();
        println(conditionDescription);
        await().atMost(MAX_MINUTES_WAIT, TimeUnit.MINUTES)
                .pollDelay(POLL_DELAY_SECONDS, TimeUnit.SECONDS)
                .until(() -> {
                    UserSession.set(user);
                    print(".");
                    return conditionEvaluator.call();
                });
        println();
        println(conditionDescription + " - DONE");
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

            // TODO Consistency Groups

            // deleteServers server
            cloud.server().deleteServer(server.getId());
            awaitUntil("Deleting Server " + server.getId(), cloud.server().isServerDeleted(server.getId()));
        }
    }

    private static void print(String message)
        {
            System.out.print(message);
        }

    private static void println()
    {
        System.out.println();
    }

    private static void println(String message)
    {
        System.out.println(System.currentTimeMillis() + "|" + message);
    }

    public static void main(String[] args)
    {
        String url = args[0];
        String user = args[1];
        String password = args[2];
        String networkDomainId = args[3];

        try
        {
            UserSession.set(new User(user, password));
            execute(url, networkDomainId);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }
}