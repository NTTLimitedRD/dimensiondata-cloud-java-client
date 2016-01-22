package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface PoolService
{
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