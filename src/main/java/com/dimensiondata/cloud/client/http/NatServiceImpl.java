package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.NatService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NatServiceImpl extends AbstractRestfulService implements NatService
{
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_INTERNAL_IP = "internalIp";
    public static final String PARAMETER_EXTERNAL_IP = "externalIp";
    public static final String PARAMETER_NODE_ID = "nodeId";

    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_ID,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME,
            PARAMETER_INTERNAL_IP,
            PARAMETER_EXTERNAL_IP,
            PARAMETER_NODE_ID));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_ID,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME,
            PARAMETER_INTERNAL_IP,
            PARAMETER_EXTERNAL_IP,
            PARAMETER_NODE_ID));

    public NatServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType createNatRule(CreateNatRule createNatRule)
    {
        return httpClient.post("network/createNatRule",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createNatRule"), CreateNatRule.class, createNatRule),
                ResponseType.class);
    }

    @Override
    public NatRules listNatRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("network/natRule", NatRules.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public NatRuleType getNatRule(String id)
    {
        return httpClient.get("network/natRule/" + id, NatRuleType.class);
    }

    @Override
    public ResponseType deleteNatRule(String id)
    {
        return httpClient.post("network/deleteNatRule",
                Entity.xml("<deleteNatRule xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }
}