package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface ServerService
{
    ResponseType deployServer(DeployServerType deployServer);

    Servers listServers(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ServerType getServer(String id);

    ResponseType deleteServer(String id);

    ResponseType startServer(String id);

    ResponseType shutdownServer(String id);

    ResponseType rebootServer(String id);

    ResponseType resetServer(String id);

    ResponseType powerOffServer(String id);

    ResponseType updateVmwareTools(String id);

    ResponseType addNic(AddNicType addNic);

    ResponseType removeNic(String id);

    ResponseType notifyNicIpChange(NotifyNicIpChangeType notifyNicIpChange);

    ResponseType cleanFailedServerDeployment(String id);

    AntiAffinityRules listAntiAffinityRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType reconfigureServer(ReconfigureServerType reconfigureServer);

    ResponseType upgradeVirtualHardware(String id);
}