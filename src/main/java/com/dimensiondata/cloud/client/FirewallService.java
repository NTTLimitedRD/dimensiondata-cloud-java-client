package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface FirewallService
{
    FirewallRules listFirewallRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType createFirewallRule(CreateFirewallRuleType createFirewallRule);

    FirewallRuleType getFirewallRule(String id);

    ResponseType editFirewallRule(EditFirewallRuleType editFirewallRule);

    ResponseType deleteFirewallRule(String id);
}