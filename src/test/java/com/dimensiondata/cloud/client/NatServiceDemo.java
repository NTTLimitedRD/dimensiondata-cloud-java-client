package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.http.NatServiceImpl;
import com.dimensiondata.cloud.client.model.CreateNatRule;
import com.dimensiondata.cloud.client.model.NatRuleType;
import com.dimensiondata.cloud.client.model.NatRules;
import com.dimensiondata.cloud.client.model.ResponseType;

import static com.dimensiondata.cloud.client.http.NatServiceImpl.PARAMETER_NETWORKDOMAIN_ID;

public class NatServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        try
        {
            NatService natService = new NatServiceImpl("http://localhost:8081/oec");

            natService.getNatRule("123");

            natService.deleteNatRule("123");

            CreateNatRule createNatRule = new CreateNatRule();
            createNatRule.setNetworkDomainId("1c813510-216a-434d-bd07-226ee5424ca3");
            createNatRule.setInternalIp("10.0.0.1");
            ResponseType response = natService.createNatRule(createNatRule);
            System.out.println(response.getMessage());

            // TODO

            NatRules natRules = natService.listNatRules(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(PARAMETER_NETWORKDOMAIN_ID, "1c813510-216a-434d-bd07-226ee5424ca3")
            ));
            for (NatRuleType natRule : natRules.getNatRule())
            {
                System.out.println(natRule.getId());
            }
        }
        catch (RequestException e)
        {
            ResponseType response = e.getResponse();
            System.out.println("bad request: " + response.getMessage());
        }
    }
}