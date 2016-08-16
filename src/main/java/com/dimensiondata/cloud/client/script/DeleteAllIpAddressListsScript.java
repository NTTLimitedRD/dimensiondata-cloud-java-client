package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.IpAddressListType;
import com.dimensiondata.cloud.client.model.IpAddressLists;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllIpAddressListsScript implements NetworkDomainScript
{
    @Override
    public void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(FirewallService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        IpAddressLists ipAddressLists = cloud.firewall().listIpAddressLists(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("IpAddressLists to delete: " + ipAddressLists.getTotalCount());
        while (ipAddressLists.getTotalCount() > 0)
        {
            deleteIpAddressLists(cloud, ipAddressLists.getIpAddressList());
            ipAddressLists = cloud.firewall().listIpAddressLists(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteIpAddressLists(Cloud cloud, List<IpAddressListType> ipAddressLists)
    {
        // check all items are in a NORMAL state
        for (IpAddressListType ipAddressList : ipAddressLists)
        {
            if (!ipAddressList.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("IpAddressList not in NORMAL state: " + ipAddressList.getId());
            }
        }

        for (IpAddressListType ipAddressList : ipAddressLists)
        {
            try
            {
                cloud.firewall().deleteIpAddressList(ipAddressList.getId());
                awaitUntil("Deleting IpAddressList " + ipAddressList.getId(), new CallableDeletedState(
                        id -> cloud.firewall().getIpAddressList(id).getState(), "ipAddressList", ipAddressList.getId()));
            }
            catch (RequestException e)
            {
                if ("HAS_DEPENDENCY".equals(e.getResponse().getResponseCode()))
                {
                    // TODO if in the first page we get only items that trigger this error, we won't exit the loop
                    print(e);
                    println("Unable to delete IpAddressList " + ipAddressList.getId() + " since it's in use");
                }
                else
                {
                    // something is wrong
                    throw e;
                }
            }
        }
    }
}
