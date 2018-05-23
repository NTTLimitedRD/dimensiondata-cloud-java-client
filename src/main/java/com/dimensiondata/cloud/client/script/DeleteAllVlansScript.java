package com.dimensiondata.cloud.client.script;

import java.util.List;

import com.dimensiondata.cloud.client.Cloud;
import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.VlanService;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.model.ReservedIpv6AddressType;
import com.dimensiondata.cloud.client.model.ReservedIpv6Addresses;
import com.dimensiondata.cloud.client.model.ReservedPrivateIpv4AddressType;
import com.dimensiondata.cloud.client.model.ReservedPrivateIpv4Addresses;
import com.dimensiondata.cloud.client.model.SecurityGroupType;
import com.dimensiondata.cloud.client.model.SecurityGroups;
import com.dimensiondata.cloud.client.model.UnreserveIpv6AddressType;
import com.dimensiondata.cloud.client.model.UnreservePrivateIpv4AddressType;
import com.dimensiondata.cloud.client.model.VlanType;
import com.dimensiondata.cloud.client.model.Vlans;
import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllVlansScript implements NetworkDomainScript
{
    @Override
    public void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(VlanService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        Vlans vlans = cloud.vlan().listVlans(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("Vlans to delete: " + vlans.getTotalCount());
        while (vlans.getTotalCount() > 0)
        {
            deleteVlans(cloud, vlans.getVlan());
            vlans = cloud.vlan().listVlans(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteVlans(Cloud cloud, List<VlanType> vlans)
    {
        // check all items are in a NORMAL state
        for (VlanType vlan : vlans)
        {
            if (!vlan.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("Vlan not in NORMAL state: " + vlan.getId());
            }
        }

        for (VlanType vlan : vlans)
        {
            // delete Security Groups
            Filter filter = new Filter(new Param("vlanId", vlan.getId()));
            SecurityGroups securityGroups = cloud.securityGroup().listSecurityGroups(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            println("SecurityGroups to delete: " + securityGroups.getTotalCount());
            while (securityGroups.getTotalCount() > 0)
            {
                deleteSecurityGroups(cloud, securityGroups.getSecurityGroup());
                securityGroups = cloud.securityGroup().listSecurityGroups(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            }

            // unreserve ipv4 addresses
            ReservedPrivateIpv4Addresses reservedPrivateIpv4Addresses = cloud.ipAddress().listReservedPrivateIpv4Addresses(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            println("Reserved Private Ipv4 Addresses to unreserve: " + reservedPrivateIpv4Addresses.getTotalCount());
            while (reservedPrivateIpv4Addresses.getTotalCount() > 0)
            {
                unreserveIpv4(cloud, reservedPrivateIpv4Addresses.getIpv4());
                reservedPrivateIpv4Addresses = cloud.ipAddress().listReservedPrivateIpv4Addresses(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            }

            // unreserve ipv6 addresses
            ReservedIpv6Addresses reservedIpv6Addresses = cloud.ipAddress().listReservedIpv6Addresses(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            println("Reserved Ipv6 Addresses to unreserve: " + reservedIpv6Addresses.getTotalCount());
            while (reservedIpv6Addresses.getTotalCount() > 0)
            {
                unreserveIpv6(cloud, reservedIpv6Addresses.getReservedIpv6Address());
                reservedIpv6Addresses = cloud.ipAddress().listReservedIpv6Addresses(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
            }

            cloud.vlan().deleteVlan(vlan.getId());
            awaitUntil("Deleting Vlan " + vlan.getId(), new CallableDeletedState(cloud.vlan(), "vlan", vlan.getId()));
        }
    }

    private static void deleteSecurityGroups(Cloud cloud, List<SecurityGroupType> securityGroups)
    {
        for (SecurityGroupType securityGroup : securityGroups)
        {
            cloud.securityGroup().deleteSecurityGroup(securityGroup.getId());
            awaitUntil("Deleting SecurityGroup " + securityGroup.getId(), new CallableDeletedState(cloud.securityGroup(), "securityGroup", securityGroup.getId()));
        }
    }

    private static void unreserveIpv4(Cloud cloud, List<ReservedPrivateIpv4AddressType> addresses)
    {
        for (ReservedPrivateIpv4AddressType address : addresses)
        {
            UnreservePrivateIpv4AddressType unreservePrivateIpv4Address = new UnreservePrivateIpv4AddressType();
            unreservePrivateIpv4Address.setVlanId(address.getVlanId());
            unreservePrivateIpv4Address.setIpAddress(address.getIpAddress());
            cloud.ipAddress().unreservePrivateIpv4Address(unreservePrivateIpv4Address);
            println("Unreserved Private Ipv4 Address " + address.getIpAddress());
        }
    }

    private static void unreserveIpv6(Cloud cloud, List<ReservedIpv6AddressType> addresses)
    {
        for (ReservedIpv6AddressType address : addresses)
        {
            UnreserveIpv6AddressType unreserveIpv6Address = new UnreserveIpv6AddressType();
            unreserveIpv6Address.setVlanId(address.getVlanId());
            unreserveIpv6Address.setIpAddress(address.getIpAddress());
            cloud.ipAddress().unreserveIpv6Address(unreserveIpv6Address);
            println("Unreserved Ipv6 Address " + address.getIpAddress());
        }
    }
}
