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
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_STATE = "state";

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
}