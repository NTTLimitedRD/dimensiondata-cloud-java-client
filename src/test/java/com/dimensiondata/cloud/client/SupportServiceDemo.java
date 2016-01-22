package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.http.FirewallServiceImpl;
import com.dimensiondata.cloud.client.http.SupportServiceImpl;
import com.dimensiondata.cloud.client.model.*;

public class SupportServiceDemo
{
    public static void main(String[] args)
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));

        try
        {
            SupportService supportService = new SupportServiceImpl("http://localhost:8081/oec");

            DefaultHealthMonitors defaultHealthMonitors = supportService.listDefaultHealthMonitors(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "2af37fb7-8c22-4af6-89ef-ad5bc87c63ba")
            ));
            for (DefaultHealthMonitorType defaultHealthMonitor : defaultHealthMonitors.getDefaultHealthMonitor())
            {
                System.out.println("monitor: " + defaultHealthMonitor.getId() + "=" + defaultHealthMonitor.getName());
            }

            DefaultIrules defaultIrules = supportService.listDefaultIrules(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "2af37fb7-8c22-4af6-89ef-ad5bc87c63ba")
            ));
            for (DefaultIruleType defaultIrule : defaultIrules.getDefaultIrule())
            {
                System.out.println("iRule: " + defaultIrule.getIrule().getId() + "=" + defaultIrule.getIrule().getName());
            }

            DefaultPersistenceProfiles defaultPersistenceProfiles = supportService.listDefaultPersistenceProfiles(100, 1, OrderBy.EMPTY, new Filter(
                    new Param(FirewallServiceImpl.PARAMETER_NETWORKDOMAIN_ID, "2af37fb7-8c22-4af6-89ef-ad5bc87c63ba")
            ));
            for (DefaultPersistenceProfileType defaultPersistenceProfile : defaultPersistenceProfiles.getDefaultPersistenceProfile())
            {
                System.out.println("profile: " + defaultPersistenceProfile.getId() + "=" + defaultPersistenceProfile.getName());
            }
        }
        catch (RequestException e)
        {
            ResponseType response = e.getResponse();
            System.out.println("bad request: " + response.getMessage());
        }
    }
}
