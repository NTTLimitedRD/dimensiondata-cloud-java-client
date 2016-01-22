package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.VlanServiceImpl;
import com.dimensiondata.cloud.client.model.*;

import java.util.List;

public class VlanServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        VlanService vlanService = new VlanServiceImpl("http://localhost:8081/oec");

        List<VlanType> vlans = vlanService.listVlans(10, 1, new OrderBy(), new Filter()).getVlan();
        for (VlanType vlan : vlans)
        {
            System.out.println(vlan.getId() + "=" + vlan.getName());
        }

        VlanType vlan = vlanService.getVlan("035f9675-3421-4f14-8af9-ff60bcf4ae57");
        System.out.println(vlan.getId() + "=" + vlan.getName());

        DeployVlanType deployVlan = new DeployVlanType();
        deployVlan.setNetworkDomainId("4fd652f7-649c-4059-83a6-63fc4d63d26d");
        deployVlan.setName("Production VLAN");
        deployVlan.setPrivateIpv4BaseAddress("10.0.4.0");
        ResponseType response = vlanService.deployVlan(deployVlan);
        System.out.println(response.getMessage());

        EditVlanType editVlan = new EditVlanType();
        editVlan.setId("035f9675-3421-4f14-8af9-ff60bcf4ae57");
        editVlan.setName("myVlan");
        response = vlanService.editVlan(editVlan);
        System.out.println(response.getMessage());

        vlans = vlanService.listVlans(10, 1, OrderBy.EMPTY, Filter.EMPTY).getVlan();
        for (VlanType v : vlans)
        {
            System.out.println(v.getId() + "=" + v.getName());
        }

        ExpandVlanType expandVlan = new ExpandVlanType();
        expandVlan.setId("035f9675-3421-4f14-8af9-ff60bcf4ae57");
        expandVlan.setPrivateIpv4PrefixSize(23);
        response = vlanService.expandVlan(expandVlan);
        System.out.println(response.getMessage());

        response = vlanService.deleteVlan("dbcf3974-65dc-458d-b2a8-ab57fb886bdd");
        System.out.println(response.getMessage());
    }
}