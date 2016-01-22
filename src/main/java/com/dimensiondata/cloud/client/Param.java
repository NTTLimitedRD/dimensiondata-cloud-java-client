package com.dimensiondata.cloud.client;

public class Param
{
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String ORDER_BY = "orderBy";

    private final String name;
    private final String value;

    public Param(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public Param(String name, int value)
    {
        this(name, Integer.toString(value));
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return name + "=" + value;
    }
}