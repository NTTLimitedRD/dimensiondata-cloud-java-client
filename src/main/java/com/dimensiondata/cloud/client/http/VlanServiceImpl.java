package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.VlanService;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VlanServiceImpl extends AbstractRestfulService implements VlanService
{
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORK_DOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_DATACENTER_ID = "datacenterId";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_PRIVATE_IPV4_ADDRESS = "privateIpv4Address";
    public static final String PARAMETER_IPV6_ADDRESS = "ipv6Address";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";

    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORK_DOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_PRIVATE_IPV4_ADDRESS,
            PARAMETER_IPV6_ADDRESS,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORK_DOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_PRIVATE_IPV4_ADDRESS,
            PARAMETER_IPV6_ADDRESS,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME));


    public VlanServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType deployVlan(DeployVlanType deployVlan)
    {
        return httpClient.post("network/deployVlan",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "deployVlan"), DeployVlanType.class, deployVlan),
                ResponseType.class);
    }

    @Override
    public Vlans listVlans(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("network/vlan", Vlans.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public VlanType getVlan(String id)
    {
        return httpClient.get("network/vlan/" + id, VlanType.class);
    }

    @Override
    public ResponseType editVlan(EditVlanType editVlan)
    {
        return httpClient.post("network/editVlan",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editVlan"), EditVlanType.class, editVlan),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteVlan(String id)
    {
        return httpClient.post("network/deleteVlan",
                Entity.xml("<deleteVlan xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType expandVlan(ExpandVlanType expandVlan)
    {
        return httpClient.post("network/expandVlan",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "expandVlan"), ExpandVlanType.class, expandVlan),
                ResponseType.class);
    }
}