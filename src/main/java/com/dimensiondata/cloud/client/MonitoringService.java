package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.ChangeServerMonitoringPlanType;
import com.dimensiondata.cloud.client.model.EnableServerMonitoringType;
import com.dimensiondata.cloud.client.model.ResponseType;

public interface MonitoringService
{
    ResponseType enableServerMonitoring(EnableServerMonitoringType enableServerMonitoring);

    ResponseType changeServerMonitoringPlan(ChangeServerMonitoringPlanType changeServerMonitoringPlan);

    ResponseType disableServerMonitoring(String id);

    String viewMonitoringUsageReport(String startDate, String endDate);
}