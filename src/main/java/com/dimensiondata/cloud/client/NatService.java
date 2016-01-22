package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.CreateNatRule;
import com.dimensiondata.cloud.client.model.NatRuleType;
import com.dimensiondata.cloud.client.model.NatRules;
import com.dimensiondata.cloud.client.model.ResponseType;

public interface NatService
{
    ResponseType createNatRule(CreateNatRule createNatRule);

    NatRules listNatRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    NatRuleType getNatRule(String natRule);

    ResponseType deleteNatRule(String id);
}
