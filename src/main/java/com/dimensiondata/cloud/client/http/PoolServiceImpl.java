package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.PoolService;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PoolServiceImpl extends AbstractRestfulService implements PoolService
{
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_DATACENTER_ID = "datacenterId";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_STATE = "state";
    public static final String PARAMETER_CREATE_TIME = "createTime";
    public static final String PARAMETER_LOAD_BALANCE_METHOD = "loadBalanceMethod";
    public static final String PARAMETER_SERVICE_DOWN_ACTION = "serviceDownAction";
    public static final String PARAMETER_SLOW_RAMP_TIME = "slowRampTime";

    public static final String PARAMETER_POOL_ID = "poolId";
    public static final String PARAMETER_POOL_NAME = "poolName";
    public static final String PARAMETER_NODE_ID = "nodeId";
    public static final String PARAMETER_NODE_NAME = "nodeName";
    public static final String PARAMETER_NODE_IP = "nodeIp";
    public static final String PARAMETER_NODE_STATUS = "nodeStatus";
    public static final String PARAMETER_PORT = "port";
    public static final String PARAMETER_STATUS = "status";

    public static final List<String> POOL_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_CREATE_TIME));

    public static final List<String> POOL_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_NAME,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME,
            PARAMETER_LOAD_BALANCE_METHOD,
            PARAMETER_SERVICE_DOWN_ACTION,
            PARAMETER_SLOW_RAMP_TIME));

    public static final List<String> POOLMEMBER_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_DATACENTER_ID,
            PARAMETER_POOL_NAME,
            PARAMETER_NODE_NAME,
            PARAMETER_CREATE_TIME));

    public static final List<String> POOLMEMBER_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID,
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_DATACENTER_ID,
            PARAMETER_POOL_ID,
            PARAMETER_POOL_NAME,
            PARAMETER_NODE_ID,
            PARAMETER_NODE_NAME,
            PARAMETER_NODE_IP,
            PARAMETER_NODE_STATUS,
            PARAMETER_PORT,
            PARAMETER_STATUS,
            PARAMETER_STATE,
            PARAMETER_CREATE_TIME));


    public PoolServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType createPool(CreatePool createPool)
    {
        return httpClient.post("networkDomainVip/createPool",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createPool"), CreatePool.class, createPool),
                ResponseType.class);
    }

    @Override
    public Pools listPools(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(POOL_ORDER_BY_PARAMETERS);
        filter.validate(POOL_FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/pool", Pools.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public PoolType getPool(String id)
    {
        return httpClient.get("networkDomainVip/pool/" + id, PoolType.class);
    }

    @Override
    public ResponseType editPool(EditPool editPool)
    {
        return httpClient.post("networkDomainVip/editPool",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editPool"), EditPool.class, editPool),
                ResponseType.class);
    }

    @Override
    public ResponseType deletePool(String id)
    {
        return httpClient.post("networkDomainVip/deletePool",
                Entity.xml("<deletePool xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public ResponseType addPoolMember(AddPoolMember addPoolMember)
    {
        return httpClient.post("networkDomainVip/addPoolMember",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "addPoolMember"), AddPoolMember.class, addPoolMember),
                ResponseType.class);
    }

    @Override
    public PoolMembers listPoolMembers(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(POOLMEMBER_ORDER_BY_PARAMETERS);
        filter.validate(POOLMEMBER_FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/poolMember", PoolMembers.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public PoolMemberType getPoolMember(String id)
    {
        return httpClient.get("networkDomainVip/poolMember/" + id, PoolMemberType.class);
    }

    @Override
    public ResponseType editPoolMember(EditPoolMember editPoolMember)
    {
        return httpClient.post("networkDomainVip/editPoolMember",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editPoolMember"), EditPoolMember.class, editPoolMember),
                ResponseType.class);
    }

    @Override
    public ResponseType removePoolMember(String id)
    {
        return httpClient.post("networkDomainVip/removePoolMember",
                Entity.xml("<removePoolMember xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }
}