package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.FirewallServiceImpl;
import com.dimensiondata.cloud.client.http.NetworkDomainServiceImpl;
import com.dimensiondata.cloud.client.model.*;

public class FirewallServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        NetworkDomainService networkDomainService = new NetworkDomainServiceImpl("http://localhost:8081/oec");
        FirewallService firewallService = new FirewallServiceImpl("http://localhost:8081/oec");

        networkDomainService.listNetworkDomains(100, 1, OrderBy.EMPTY, Filter.EMPTY);

        FirewallRules firewallRules = firewallService.listFirewallRules(100, 1, OrderBy.EMPTY, new Filter(
                new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "1c813510-216a-434d-bd07-226ee5424ca3")
        ));
        for (FirewallRuleType firewallRule : firewallRules.getFirewallRule())
        {
            System.out.println(firewallRule.getId() + " " + firewallRule.getName());
        }

        FirewallRuleType firewallRule = firewallRules.getFirewallRule().get(0);
        firewallRule = firewallService.getFirewallRule(firewallRule.getId());
        System.out.println(firewallRule.getId() + " " + firewallRule.getName() + " " + firewallRule.isEnabled());

        EditFirewallRuleType editFirewallRuleType = new EditFirewallRuleType();
        editFirewallRuleType.setId(firewallRule.getId());
        editFirewallRuleType.setEnabled(!firewallRule.isEnabled());
        ResponseType response = firewallService.editFirewallRule(editFirewallRuleType);
        System.out.println(response.getMessage());

        firewallRule = firewallService.getFirewallRule(firewallRule.getId());
        System.out.println(firewallRule.getId() + " " + firewallRule.getName() + " " + firewallRule.isEnabled());

        CreateFirewallRuleType createFirewallRule = new CreateFirewallRuleType();
        createFirewallRule.setAction(firewallRule.getAction());
        createFirewallRule.setDestination(firewallRule.getDestination());
        createFirewallRule.setEnabled(false);
        createFirewallRule.setIpVersion(firewallRule.getIpVersion());
        createFirewallRule.setName("a clone");
        createFirewallRule.setNetworkDomainId(firewallRule.getNetworkDomainId());
        RulePlacementType rulePlacement = new RulePlacementType();
        rulePlacement.setPosition(RulePositionType.FIRST);
        createFirewallRule.setPlacement(rulePlacement);
        createFirewallRule.setProtocol(firewallRule.getProtocol());
        createFirewallRule.setSource(firewallRule.getSource());
        response = firewallService.createFirewallRule(createFirewallRule);
        System.out.println(response.getMessage());

        response = firewallService.deleteFirewallRule("22df7176-2719-40fb-914a-5c8fd612fe33");
        System.out.println(response.getMessage());
    }
}