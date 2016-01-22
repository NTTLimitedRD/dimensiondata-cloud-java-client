package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.http.FirewallServiceImpl;
import com.dimensiondata.cloud.client.http.IpAddressServiceImpl;
import com.dimensiondata.cloud.client.model.*;

public class IpAddressServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        try
        {
            IpAddressService ipAddressService = new IpAddressServiceImpl("http://localhost:8081/oec");

            AddPublicIpBlockType addPublicIpBlock = new AddPublicIpBlockType();
            addPublicIpBlock.setNetworkDomainId("1c813510-216a-434d-bd07-226ee5424ca3");
            ResponseType response = ipAddressService.addPublicIpBlock(addPublicIpBlock);
            System.out.println(response.getMessage());

            PublicIpBlocks publicIpBlocks = ipAddressService.listPublicIpBlocks(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "1c813510-216a-434d-bd07-226ee5424ca3")
            ));
            for (PublicIpBlockType publicIpBlock : publicIpBlocks.getPublicIpBlock())
            {
                System.out.println("publicIpBlock=" + publicIpBlock.getId());
            }

            PublicIpBlockType publicIpBlock = ipAddressService.getPublicIpBlock("ip-block-1");
            System.out.println("publicIpBlock=" + publicIpBlock.getId());

            response = ipAddressService.removePublicIpBlock("ip-block-1");
            System.out.println(response.getMessage());

            ReservedPrivateIpv4Addresses reservedPrivateIpv4Addresses = ipAddressService.listReservedPrivateIpv4Addresses(100, 1, OrderBy.EMPTY, Filter.EMPTY);
            for (ReservedPrivateIpv4AddressType reservedPrivateIpv4Address : reservedPrivateIpv4Addresses.getIpv4())
            {
                System.out.println("reservedPrivateIpv4Address=" + reservedPrivateIpv4Address.getValue());
            }

            ReservedPublicIpv4Addresses reservedPublicIpv4Addresses = ipAddressService.listReservedPublicIpv4Addresses(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "1c813510-216a-434d-bd07-226ee5424ca3")
            ));
            for (ReservedPublicIpv4AddressType reservedPublicIpv4Address : reservedPublicIpv4Addresses.getIp())
            {
                System.out.println("reservedPublicIpv4Address=" + reservedPublicIpv4Address.getValue());
            }
        }
        catch (RequestException e)
        {
            ResponseType response = e.getResponse();
            System.out.println("bad request: " + response.getMessage());
        }
    }
}