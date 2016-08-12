package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.*;
import com.dimensiondata.cloud.client.http.CallableDeletedState;
import com.dimensiondata.cloud.client.http.CloudImpl;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.NatRuleType;
import com.dimensiondata.cloud.client.model.NatRules;

import java.util.List;

import static com.dimensiondata.cloud.client.script.Script.*;

public class DeleteAllNatRulesScript
{
    static void execute(Cloud cloud, String networkDomainId)
    {
        Filter filter = new Filter(new Param(NatService.PARAMETER_NETWORKDOMAIN_ID, networkDomainId));
        NatRules natRules = cloud.nat().listNatRules(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        println("NatRules to delete: " + natRules.getTotalCount());
        while (natRules.getTotalCount() > 0)
        {
            deleteNatRules(cloud, natRules.getNatRule());
            natRules = cloud.nat().listNatRules(PAGE_SIZE, 1, OrderBy.EMPTY, filter);
        }
    }

    private static void deleteNatRules(Cloud cloud, List<NatRuleType> natRules)
    {
        // check all items are in a NORMAL state
        for (NatRuleType natRule : natRules)
        {
            if (!natRule.getState().equals(NORMAL_STATE))
            {
                throw new RuntimeException("NatRule not in NORMAL state: " + natRule.getId());
            }
        }

        for (NatRuleType natRule : natRules)
        {
            cloud.nat().deleteNatRule(natRule.getId());
            awaitUntil("Deleting NatRule " + natRule.getId(), new CallableDeletedState(cloud.nat(), "natRule", natRule.getId()));
        }
    }

    public static void main(String[] args)
    {
        if (args.length < 4)
        {
            System.out.println("required parameters: [api url] [user] [password] [networkdomain id]");
            System.exit(-1);
        }

        String url = args[0];
        String user = args[1];
        String password = args[2];
        String networkDomainId = args[3];

        try
        {
            UserSession.set(new User(user, password));
            Cloud cloud = new CloudImpl(url);
            execute(cloud, networkDomainId);
        }
        catch (RequestException e)
        {
            print(e);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }
}
