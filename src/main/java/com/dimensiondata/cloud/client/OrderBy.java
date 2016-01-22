package com.dimensiondata.cloud.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderBy
{
    public static final String DESCENDING_SUFFIX = ".DESCENDING";
    public static final OrderBy EMPTY = new OrderBy();

    private static final Logger logger = LoggerFactory.getLogger(Filter.class);
    private final String[] parameters;

    public OrderBy(String... parameters)
    {
        this.parameters = parameters;
    }

    public String concatenateParameters()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++)
        {
            if (i > 0) stringBuilder.append(",");
            stringBuilder.append(parameters[i]);
        }
        return stringBuilder.toString();
    }

    public void validate(List<String> validNames)
    {
        for (String parameter : parameters)
        {
            if (parameter.endsWith(DESCENDING_SUFFIX))
            {
                parameter = parameter.substring(0, parameter.length() - DESCENDING_SUFFIX.length());
            }

            if (!validNames.contains(parameter.toLowerCase()))
            {
                logger.warn("Unknown OrderBy parameter: " + parameter);
            }
        }
    }
}