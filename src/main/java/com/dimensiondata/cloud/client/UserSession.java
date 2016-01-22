package com.dimensiondata.cloud.client;

import javax.ws.rs.core.Response;

public class UserSession
{
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Response> responseThreadLocal = new ThreadLocal<>();

    public static void set(User user)
    {
        userThreadLocal.set(user);
    }

    public static User get()
    {
        if (userThreadLocal.get() == null)
        {
            throw new RuntimeException("UserSession not set");
        }

        return userThreadLocal.get();
    }

    public static void setLastResponse(Response response)
    {
        responseThreadLocal.set(response);
    }

    public static Response getLastResponse()
    {
        return responseThreadLocal.get();
    }
}