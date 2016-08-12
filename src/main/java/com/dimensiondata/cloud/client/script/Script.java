package com.dimensiondata.cloud.client.script;

import com.dimensiondata.cloud.client.User;
import com.dimensiondata.cloud.client.UserSession;
import com.dimensiondata.cloud.client.http.RequestException;
import com.dimensiondata.cloud.client.model.NameValuePairType;
import com.dimensiondata.cloud.client.model.ResponseType;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

public class Script
{
    public static final int PAGE_SIZE = 50;
    public static final String NORMAL_STATE = "NORMAL";
    public static final int MAX_MINUTES_WAIT = 15;
    public static final int POLL_DELAY_SECONDS = 5;
    public static final int POLL_INTERVAL_SECONDS = 10;

    public static void awaitUntil(String conditionDescription, Callable<Boolean> conditionEvaluator)
    {
        User user = UserSession.get();
        println(conditionDescription);
        await().atMost(MAX_MINUTES_WAIT, TimeUnit.MINUTES)
                .pollInterval(POLL_INTERVAL_SECONDS, TimeUnit.SECONDS)
                .pollDelay(POLL_DELAY_SECONDS, TimeUnit.SECONDS)
                .until(() -> {
                    UserSession.set(user);
                    print(".");
                    return conditionEvaluator.call();
                });
        println();
        println(conditionDescription + " - DONE");
    }

    public static void print(String message)
    {
        System.out.print(message);
    }

    public static void println()
    {
        System.out.println();
    }

    public static void println(String message)
    {
        System.out.println(System.currentTimeMillis() + "|" + message);
    }

    public static void print(String level, List<NameValuePairType> pairs)
    {
        for (NameValuePairType pair : pairs)
        {
            println(level + "|" + pair.getName() + "=" + pair.getValue());
        }
    }

    public static void print(RequestException e)
    {
        println("ERROR!");
        ResponseType response = e.getResponse();
        println("Operation: " + response.getOperation());
        println("ResponseCode: " + response.getResponseCode());
        println("Message: " + response.getMessage());
        println("Request ID: " + response.getRequestId());
        print("INFO", response.getInfo());
        print("WARN", response.getWarning());
        print("ERROR", response.getError());
        e.printStackTrace();
    }
}
