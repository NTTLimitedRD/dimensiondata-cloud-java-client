package com.dimensiondata.cloud.client;

import java.util.concurrent.Callable;

import com.dimensiondata.cloud.client.model.AddDiskType;
import com.dimensiondata.cloud.client.model.AddNicType;
import com.dimensiondata.cloud.client.model.AddScsiControllerType;
import com.dimensiondata.cloud.client.model.AntiAffinityRules;
import com.dimensiondata.cloud.client.model.DeployServerType;
import com.dimensiondata.cloud.client.model.NotifyNicIpChangeType;
import com.dimensiondata.cloud.client.model.ReconfigureServerType;
import com.dimensiondata.cloud.client.model.ResponseType;
import com.dimensiondata.cloud.client.model.ServerType;
import com.dimensiondata.cloud.client.model.Servers;

public interface ServerService
{
    String PARAMETER_ID = "id";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_NETWORK_ID = "networkId";
    String PARAMETER_VLAN_ID = "vlanId";
    String PARAMETER_SOURCE_IMAGE_ID = "sourceImageId";
    String PARAMETER_DEPLOYED_ID = "deployed";
    String PARAMETER_NAME = "name";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_STATE = "state";
    String PARAMETER_STARTED = "started";
    String PARAMETER_OPERATING_SYSTEM_ID = "operatingSystemId";
    String PARAMETER_IPV6 = "ipv6";
    String PARAMETER_PRIVATE_IPV4 = "privateIpv4";
    String PARAMETER_SERVER_ID = "serverId";

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

    ResponseType addDisk(AddDiskType addDisk);

    ResponseType addScsiController(AddScsiControllerType addController);

    ResponseType removeNic(String id);

    ResponseType notifyNicIpChange(NotifyNicIpChangeType notifyNicIpChange);

    ResponseType cleanFailedServerDeployment(String id);

    AntiAffinityRules listAntiAffinityRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType reconfigureServer(ReconfigureServerType reconfigureServer);

    ResponseType upgradeVirtualHardware(String id);

    void disableServerBackup(String id);

    String getIdFromDeployResponse(ResponseType response);

    Callable<Boolean> isServerInNormalState(String id);

    Callable<Boolean> isServerDeleted(String id);
}