package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.NodeService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeServiceImpl extends AbstractRestfulService implements NodeService
{
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_DATACENTER_ID = "datacenterId";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_IPV4ADDRESS = "ipv4Address";
    public static final String PARAMETER_IPV6ADDRESS = "ipv6Address";

    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME));

    public static final List<String> FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME,
            PARAMETER_IPV4ADDRESS,
            PARAMETER_IPV6ADDRESS));

    public NodeServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType createNode(CreateNodeType createNode)
    {
        return httpClient.post("networkDomainVip/createNode",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createNode"), CreateNodeType.class, createNode),
                ResponseType.class);
    }

    @Override
    public Nodes listNodes(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        filter.validate(FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/node", Nodes.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public NodeType getNode(String id)
    {
        return httpClient.get("networkDomainVip/node/" + id, NodeType.class);
    }

    @Override
    public ResponseType editNode(EditNode editNode)
    {
        return httpClient.post("networkDomainVip/editNode",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editNode"), EditNode.class, editNode),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteNode(String id)
    {
        return httpClient.post("networkDomainVip/deleteNode",
                Entity.xml("<deleteNode xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }
}
