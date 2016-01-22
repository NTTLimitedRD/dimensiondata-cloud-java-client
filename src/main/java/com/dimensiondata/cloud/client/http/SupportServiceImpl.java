package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.SupportService;
import com.dimensiondata.cloud.client.model.DefaultHealthMonitors;
import com.dimensiondata.cloud.client.model.DefaultIrules;
import com.dimensiondata.cloud.client.model.DefaultPersistenceProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SupportServiceImpl extends AbstractRestfulService implements SupportService
{
    public static final String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_NODE_COMPATIBLE = "nodeCompatible";
    public static final String PARAMETER_POOL_COMPATIBLE = "poolCompatible";

    public static final String PARAMETER_FALLBACK_COMPATIBLE = "fallbackCompatible";
    public static final String PARAMETER_VIRTUAL_LISTENER_TYPE = "virtualListenerType";
    public static final String PARAMETER_VIRTUAL_LISTENER_PROTOCOl = "virtualListenerProtocol";

    public static final List<String> MONITOR_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NAME,
            PARAMETER_NODE_COMPATIBLE,
            PARAMETER_POOL_COMPATIBLE
            ));

    public static final List<String> MONITOR_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_ID,
            PARAMETER_NAME,
            PARAMETER_NODE_COMPATIBLE,
            PARAMETER_POOL_COMPATIBLE
            ));

    public static final List<String> PROFILE_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID
            ));

    public static final List<String> PROFILE_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_ID,
            PARAMETER_NAME,
            PARAMETER_FALLBACK_COMPATIBLE,
            PARAMETER_VIRTUAL_LISTENER_TYPE,
            PARAMETER_VIRTUAL_LISTENER_PROTOCOl
            ));

    public static final List<String> IRULE_ORDER_BY_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_ID
            ));

    public static final List<String> IRULE_FILTER_PARAMETERS = Collections.unmodifiableList(Arrays.asList(
            PARAMETER_NETWORKDOMAIN_ID,
            PARAMETER_ID,
            PARAMETER_NAME,
            PARAMETER_VIRTUAL_LISTENER_TYPE,
            PARAMETER_VIRTUAL_LISTENER_PROTOCOl
            ));

    public SupportServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public DefaultHealthMonitors listDefaultHealthMonitors(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(MONITOR_ORDER_BY_PARAMETERS);
        filter.validate(MONITOR_FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/defaultHealthMonitor", DefaultHealthMonitors.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public DefaultPersistenceProfiles listDefaultPersistenceProfiles(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(PROFILE_ORDER_BY_PARAMETERS);
        filter.validate(PROFILE_FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/defaultPersistenceProfile", DefaultPersistenceProfiles.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public DefaultIrules listDefaultIrules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        orderBy.validate(IRULE_ORDER_BY_PARAMETERS);
        filter.validate(IRULE_FILTER_PARAMETERS);

        return httpClient.get("networkDomainVip/defaultIrule", DefaultIrules.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }
}