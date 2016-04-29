package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.ApiUrls;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.model.*;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

public class AsyncDemo
{
    public static void main(String args[])
    {
        Cloud cloud = new CloudImpl(ApiUrls.DIMENSIONDATA.Europe_EU.getApiUrl());

        DeployNetworkDomainType deployNetworkDomain = new DeployNetworkDomainType();
        EditNetworkDomainType editNetworkDomain = new EditNetworkDomainType();
        DeployVlanType deployVlan = new DeployVlanType();
        DeployServerType deployServer = new DeployServerType();

        // Deploy Network Domain:
        ResponseType deployNetworkDomainResponse = cloud.networkDomain().deployNetworkDomain(deployNetworkDomain);
        String networkDomainId = cloud.networkDomain().getIdFromDeployResponse(deployNetworkDomainResponse);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.networkDomain().isNetworkDomainInNormalState(networkDomainId));

        // Edit Network Domain:
        cloud.networkDomain().editNetworkDomain(editNetworkDomain);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.networkDomain().isNetworkDomainInNormalState(networkDomainId));

        // Delete Network Domain:
        cloud.networkDomain().deleteNetworkDomain(networkDomainId);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.networkDomain().isNetworkDomainDeleted(networkDomainId));

        // Deploy Vlan:
        ResponseType deployVlanResponse = cloud.vlan().deployVlan(deployVlan);
        String vlanId = cloud.vlan().getIdFromDeployResponse(deployVlanResponse);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.vlan().isVlanInNormalState(vlanId));

        // Delete Vlan:
        cloud.vlan().deleteVlan(vlanId);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.vlan().isVlanDeleted(vlanId));

        // Deploy Server:
        ResponseType deployServerResponse = cloud.server().deployServer(deployServer);
        String serverId = cloud.server().getIdFromDeployResponse(deployServerResponse);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.server().isServerInNormalState(serverId));

        // Delete Server:
        cloud.server().deleteServer(serverId);
        await().atMost(5, TimeUnit.MINUTES)
                .pollDelay(30, TimeUnit.SECONDS)
                .until(cloud.server().isServerDeleted(serverId));
    }
}
