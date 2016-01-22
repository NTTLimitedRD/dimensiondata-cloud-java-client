package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.NetworkDomainServiceImpl;
import com.dimensiondata.cloud.client.model.DeployNetworkDomainType;
import com.dimensiondata.cloud.client.model.EditNetworkDomainType;
import com.dimensiondata.cloud.client.model.NetworkDomainType;
import com.dimensiondata.cloud.client.model.ResponseType;

import java.util.List;

public class NetworkDomainServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        NetworkDomainService networkDomainService = new NetworkDomainServiceImpl("http://localhost:8081/oec");

        DeployNetworkDomainType deployNetworkDomain = new DeployNetworkDomainType();
        deployNetworkDomain.setDatacenterId("DEV_NET2");
        deployNetworkDomain.setName("client-test-AAA");
        deployNetworkDomain.setType("ADVANCED");
        ResponseType response = networkDomainService.deployNetworkDomain(deployNetworkDomain);
        System.out.println(response.getInfo().get(0).getName());
        System.out.println(response.getInfo().get(0).getValue());
        String networkDomainId1 = response.getInfo().get(0).getValue();

        deployNetworkDomain = new DeployNetworkDomainType();
        deployNetworkDomain.setDatacenterId("DEV_NET2");
        deployNetworkDomain.setName("client-test-BBB");
        deployNetworkDomain.setType("ADVANCED");
        response = networkDomainService.deployNetworkDomain(deployNetworkDomain);
        System.out.println(response.getInfo().get(0).getName());
        System.out.println(response.getInfo().get(0).getValue());
        String networkDomainId2 = response.getInfo().get(0).getValue();

        Filter filter = new Filter(new Param(NetworkDomainServiceImpl.PARAMETER_NAME + Filter.LIKE_SUFFIX, "client-test-*"));
        OrderBy orderBy = new OrderBy(NetworkDomainServiceImpl.PARAMETER_NAME);
        List<NetworkDomainType> networkDomains = networkDomainService.listNetworkDomains(10, 1, orderBy, filter).getNetworkDomain();
        for (NetworkDomainType networkDomain : networkDomains)
        {
            System.out.println(networkDomain.getId() + "=" + networkDomain.getName());
        }

        EditNetworkDomainType editNetworkDomain = new EditNetworkDomainType();
        editNetworkDomain.setId(networkDomainId2);
        editNetworkDomain.setName("client-test-CCC");
        networkDomainService.editNetworkDomain(editNetworkDomain);

        NetworkDomainType networkDomain = networkDomainService.getNetworkDomain(networkDomainId2);
        System.out.println(networkDomain.getId() + "=" + networkDomain.getName());

        networkDomains = networkDomainService.listNetworkDomains(10, 1, orderBy, filter).getNetworkDomain();
        for (NetworkDomainType networkDomainType : networkDomains)
        {
            System.out.println(networkDomainType.getId() + "=" + networkDomainType.getName());
        }

        response = networkDomainService.deleteNetworkDomain(networkDomainId1);
        System.out.println(response.getMessage());
        response = networkDomainService.deleteNetworkDomain(networkDomainId2);
        System.out.println(response.getMessage());
    }
}
