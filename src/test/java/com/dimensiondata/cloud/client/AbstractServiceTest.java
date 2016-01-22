package com.dimensiondata.cloud.client;

import org.junit.Before;

public class AbstractServiceTest
{
    @Before
    public void setUserSession()
    {
        UserSession.set(new User("seeded-org-id-1", "seeded-test-user-1", "Password1!"));
    }

    protected String getBaseUrl()
    {
        return "http://localhost:8081/oec";
    }
}
