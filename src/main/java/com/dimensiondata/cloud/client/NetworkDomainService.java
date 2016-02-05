package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

import java.util.concurrent.Callable;

public interface NetworkDomainService
{
    NetworkDomains listNetworkDomains(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NetworkDomainType getNetworkDomain(String id);

    ResponseType deployNetworkDomain(DeployNetworkDomainType deployNetworkDomain);

    ResponseType editNetworkDomain(EditNetworkDomainType editNetworkDomain);

    ResponseType deleteNetworkDomain(String id);

    Callable<Boolean> isNetworkDomainInNormalState(String id);

    Callable<Boolean> isNetworkDomainDeployed(ResponseType responseType);

    Callable<Boolean> isNetworkDomainDeleted(String id);
}