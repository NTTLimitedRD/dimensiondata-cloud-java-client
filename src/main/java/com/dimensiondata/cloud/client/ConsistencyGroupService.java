package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface ConsistencyGroupService extends StateService
{
    ResponseType createConsistencyGroup(CreateConsistencyGroupType createConsistencyGroup);

    ConsistencyGroups listConsistencyGroups(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ConsistencyGroupType getConsistencyGroup(String consistencyGroupId);

    ResponseType deleteConsistencyGroup(String consistencyGroupId);

    ConsistencyGroupSnapshots listSnapshots(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType startFailoverPreview(String consistencyGroupId, String snapshotId);

    ResponseType stopFailoverPreview(String consistencyGroupId);

    ResponseType initiateFailover(String consistencyGroupId);
}
