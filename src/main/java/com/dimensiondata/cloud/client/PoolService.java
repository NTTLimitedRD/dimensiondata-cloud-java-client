package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface PoolService extends StateService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_NAME = "name";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_LOAD_BALANCE_METHOD = "loadBalanceMethod";
    String PARAMETER_SERVICE_DOWN_ACTION = "serviceDownAction";
    String PARAMETER_SLOW_RAMP_TIME = "slowRampTime";

    String PARAMETER_POOL_ID = "poolId";
    String PARAMETER_POOL_NAME = "poolName";
    String PARAMETER_NODE_ID = "nodeId";
    String PARAMETER_NODE_NAME = "nodeName";
    String PARAMETER_NODE_IP = "nodeIp";
    String PARAMETER_NODE_STATUS = "nodeStatus";
    String PARAMETER_PORT = "port";
    String PARAMETER_STATUS = "status";

    ResponseType createPool(CreatePool createPool);

    Pools listPools(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    PoolType getPool(String id);

    ResponseType editPool(EditPool editPool);

    ResponseType deletePool(String id);

    ResponseType addPoolMember(AddPoolMember addPoolMember);

    PoolMembers listPoolMembers(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    PoolMemberType getPoolMember(String id);

    ResponseType editPoolMember(EditPoolMember editPoolMember);

    ResponseType removePoolMember(String id);
}