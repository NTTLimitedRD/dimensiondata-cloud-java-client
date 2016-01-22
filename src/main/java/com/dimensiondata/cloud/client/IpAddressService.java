package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface IpAddressService
{
    ResponseType addPublicIpBlock(AddPublicIpBlockType addPublicIpBlock);

    PublicIpBlocks listPublicIpBlocks(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    PublicIpBlockType getPublicIpBlock(String id);

    ResponseType removePublicIpBlock(String id);

    ReservedPublicIpv4Addresses listReservedPublicIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ReservedPrivateIpv4Addresses listReservedPrivateIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType reservePrivateIpv4Address(ReservePrivateIpv4AddressType reservePrivateIpv4Address);

    ResponseType reserveIpv6Address(ReserveIpv6AddressType reserveIpv6Address);
}