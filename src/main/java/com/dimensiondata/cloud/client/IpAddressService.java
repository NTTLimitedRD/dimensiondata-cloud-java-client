package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface IpAddressService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_BASE_IP = "baseIp";
    String PARAMETER_SIZE = "size";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_NETWORK_ID = "networkId";
    String PARAMETER_IP_BLOCK_ID = "ipBlockId";
    String PARAMETER_IP_ADDRESS = "ipAddress";
    String PARAMETER_VLAN_ID = "vlanId";

    ResponseType addPublicIpBlock(AddPublicIpBlockType addPublicIpBlock);

    PublicIpBlocks listPublicIpBlocks(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    PublicIpBlockType getPublicIpBlock(String id);

    ResponseType removePublicIpBlock(String id);

    ReservedPublicIpv4Addresses listReservedPublicIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ReservedPrivateIpv4Addresses listReservedPrivateIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ReservedIpv6Addresses listReservedIpv6Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType reservePrivateIpv4Address(ReservePrivateIpv4AddressType reservePrivateIpv4Address);

    ResponseType reserveIpv6Address(ReserveIpv6AddressType reserveIpv6Address);

    ResponseType unreservePrivateIpv4Address(UnreservePrivateIpv4AddressType unreservePrivateIpv4Address);

    ResponseType unreserveIpv6Address(UnreserveIpv6AddressType unreserveIpv6Address);
}