package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.FirewallService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirewallServiceImpl extends AbstractRestfulService implements FirewallService
{
    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME,
            PARAMETER_STATE));

    public FirewallServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public FirewallRules listFirewallRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("network/firewallRule", FirewallRules.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public ResponseType createFirewallRule(CreateFirewallRuleType createFirewallRule)
    {
        return httpClient.post("network/createFirewallRule",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createFirewallRule"), CreateFirewallRuleType.class, createFirewallRule),
                ResponseType.class);
    }

    @Override
    public FirewallRuleType getFirewallRule(String id)
    {
        return httpClient.get("network/firewallRule/" + id, FirewallRuleType.class);
    }

    @Override
    public ResponseType editFirewallRule(EditFirewallRuleType editFirewallRule)
    {
        return httpClient.post("network/editFirewallRule",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editFirewallRule"), EditFirewallRuleType.class, editFirewallRule),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteFirewallRule(String id)
    {
        return httpClient.post("network/deleteFirewallRule",
                Entity.xml("<deleteFirewallRule xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public String getState(String id)
    {
        return getFirewallRule(id).getState();
    }

    @Override
    public ResponseType createIpAddressList(CreateIpAddressList createIpAddressList)
    {
        return httpClient.post("network/createIpAddressList",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createIpAddressList"), CreateIpAddressList.class, createIpAddressList),
                ResponseType.class);
    }

    @Override
    public IpAddressLists listIpAddressLists(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        return httpClient.get("network/ipAddressList", IpAddressLists.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public IpAddressListType getIpAddressList(String id)
    {
        return httpClient.get("network/ipAddressList/" + id, IpAddressListType.class);
    }

    @Override
    public ResponseType editIpAddressList(EditIpAddressList editIpAddressList)
    {
        return httpClient.post("network/editIpAddressList",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editIpAddressList"), EditIpAddressList.class, editIpAddressList),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteIpAddressList(String id)
    {
        return httpClient.post("network/deleteIpAddressList",
                Entity.xml("<deleteIpAddressList xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType createPortList(CreatePortList createPortList)
    {
        return httpClient.post("network/createPortList",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createPortList"), CreatePortList.class, createPortList),
                ResponseType.class);
    }

    @Override
    public PortLists listPortLists(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        return httpClient.get("network/portList", PortLists.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public PortListType getPortList(String id)
    {
        return httpClient.get("network/portList/" + id, PortListType.class);
    }

    @Override
    public ResponseType editPortList(EditPortList editPortList)
    {
        return httpClient.post("network/editPortList",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editIpAddressList"), EditPortList.class, editPortList),
                ResponseType.class);
    }

    @Override
    public ResponseType deletePortList(String id)
    {
        return httpClient.post("network/deletePortList",
                Entity.xml("<deletePortList xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }
}