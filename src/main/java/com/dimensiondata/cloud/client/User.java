package com.dimensiondata.cloud.client;

public class User
{
    private final String user;
    private final String password;
    private String orgId;

    public User(String user, String password)
    {
        this.user = user;
        this.password = password;
    }

    public User(String orgId, String user, String password)
    {
        this.orgId = orgId;
        this.user = user;
        this.password = password;
    }

    public String getOrgId()
    {
        return orgId;
    }

    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }
}