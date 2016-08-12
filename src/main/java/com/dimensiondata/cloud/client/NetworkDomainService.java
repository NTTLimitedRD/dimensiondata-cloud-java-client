package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

import java.util.concurrent.Callable;

public interface NetworkDomainService extends StateService
{
    NetworkDomains listNetworkDomains(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NetworkDomainType getNetworkDomain(String id);

    ResponseType deployNetworkDomain(DeployNetworkDomainType deployNetworkDomain);

    ResponseType editNetworkDomain(EditNetworkDomainType editNetworkDomain);

    ResponseType deleteNetworkDomain(String id);

    String getIdFromDeployResponse(ResponseType response);

    Callable<Boolean> isNetworkDomainInNormalState(String id);

    Callable<Boolean> isNetworkDomainDeleted(String id);
}