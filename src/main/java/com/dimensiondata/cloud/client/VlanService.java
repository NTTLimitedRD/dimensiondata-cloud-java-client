package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

import java.util.concurrent.Callable;

public interface VlanService extends StateService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_DATACENTER_ID = "datacenterId";
    String PARAMETER_NAME = "name";
    String PARAMETER_PRIVATE_IPV4_ADDRESS = "privateIpv4Address";
    String PARAMETER_IPV6_ADDRESS = "ipv6Address";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";

    ResponseType deployVlan(DeployVlanType deployVlan);

    Vlans listVlans(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    VlanType getVlan(String id);

    ResponseType editVlan(EditVlanType editVlan);

    ResponseType deleteVlan(String id);

    ResponseType expandVlan(ExpandVlanType expandVlan);

    String getIdFromDeployResponse(ResponseType response);

    Callable<Boolean> isVlanInNormalState(String id);

    Callable<Boolean> isVlanDeleted(String id);
}
