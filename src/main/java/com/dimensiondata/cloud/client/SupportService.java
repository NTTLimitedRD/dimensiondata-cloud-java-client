package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.DefaultHealthMonitors;
import com.dimensiondata.cloud.client.model.DefaultIrules;
import com.dimensiondata.cloud.client.model.DefaultPersistenceProfiles;

public interface SupportService
{
    DefaultHealthMonitors listDefaultHealthMonitors(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    DefaultPersistenceProfiles listDefaultPersistenceProfiles(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    DefaultIrules listDefaultIrules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);
}