package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.IpAddressService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IpAddressServiceImpl extends AbstractRestfulService implements IpAddressService
{
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_DATACENTER_ID = "datacenterId";
    public static final String PARAMETER_BASE_IP = "baseIp";
    public static final String PARAMETER_SIZE = "size";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_NETWORK_ID = "networkId";
    public static final String PARAMETER_IP_BLOCK_ID = "ipBlockId";
    public static final String PARAMETER_IP_ADDRESS = "ipAddress";
    public static final String PARAMETER_VLAN_ID = "vlanId";

    public static final List<String> PUBLIC_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_BASE_IP,
            PARAMETER_SIZE,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME));

    public static final List<String> PUBLIC_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_BASE_IP,
            PARAMETER_SIZE,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME));

    public static final List<String> RESERVED_PUBLIC_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORK_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_IP_BLOCK_ID,
            PARAMETER_IP_ADDRESS));

    public static final List<String> RESERVED_PUBLIC_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORK_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_IP_BLOCK_ID,
            PARAMETER_IP_ADDRESS));

    public static final List<String> RESERVED_PRIVATE_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORK_ID,
            PARAMETER_VLAN_ID,
            PARAMETER_IP_ADDRESS));

    public static final List<String> RESERVED_PRIVATE_FILTER_PARAMETERS = Collections.unmodifiableList(Collections.EMPTY_LIST);


    public IpAddressServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType addPublicIpBlock(AddPublicIpBlockType addPublicIpBlock)
    {
        return httpClient.post("network/addPublicIpBlock",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "addPublicIpBlock"), AddPublicIpBlockType.class, addPublicIpBlock),
                ResponseType.class);
    }

    @Override
    public PublicIpBlocks listPublicIpBlocks(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(PUBLIC_ORDER_BY_PARAMETERS);
        filter.validate(PUBLIC_FILTER_PARAMETERS);

        return httpClient.get("network/publicIpBlock", PublicIpBlocks.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public PublicIpBlockType getPublicIpBlock(String id)
    {
        return httpClient.get("network/publicIpBlock/" + id, PublicIpBlockType.class);
    }

    @Override
    public ResponseType removePublicIpBlock(String id)
    {
        return httpClient.post("network/removePublicIpBlock",
                Entity.xml("<removePublicIpBlock xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ReservedPublicIpv4Addresses listReservedPublicIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(RESERVED_PUBLIC_ORDER_BY_PARAMETERS);
        filter.validate(RESERVED_PUBLIC_FILTER_PARAMETERS);

        return httpClient.get("network/reservedPublicIpv4Address", ReservedPublicIpv4Addresses.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public ReservedPrivateIpv4Addresses listReservedPrivateIpv4Addresses(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(RESERVED_PRIVATE_ORDER_BY_PARAMETERS);
        filter.validate(RESERVED_PRIVATE_FILTER_PARAMETERS);

        return httpClient.get("network/reservedPrivateIpv4Address", ReservedPrivateIpv4Addresses.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public ResponseType reservePrivateIpv4Address(ReservePrivateIpv4AddressType reservePrivateIpv4Address)
    {
        return httpClient.post("network/reservePrivateIpv4Address",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "reservePrivateIpv4Address"), ReservePrivateIpv4AddressType.class, reservePrivateIpv4Address),
                ResponseType.class);
    }

    @Override
    public ResponseType reserveIpv6Address(ReserveIpv6AddressType reserveIpv6Address)
    {
        return httpClient.post("network/reserveIpv6Address",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "reservePrivateIpv4Address"), ReserveIpv6AddressType.class, reserveIpv6Address),
                ResponseType.class);
    }
}