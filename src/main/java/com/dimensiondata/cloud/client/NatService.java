package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.CreateNatRule;
import com.dimensiondata.cloud.client.model.NatRuleType;
import com.dimensiondata.cloud.client.model.NatRules;
import com.dimensiondata.cloud.client.model.ResponseType;

public interface NatService extends StateService
{
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_ID = "id";
    String PARAMETER_STATE = "state";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_INTERNAL_IP = "internalIp";
    String PARAMETER_EXTERNAL_IP = "externalIp";
    String PARAMETER_NODE_ID = "nodeId";

    ResponseType createNatRule(CreateNatRule createNatRule);

    NatRules listNatRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NatRuleType getNatRule(String natRule);

    ResponseType deleteNatRule(String id);
}
