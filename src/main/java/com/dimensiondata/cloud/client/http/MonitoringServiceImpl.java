package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.MonitoringService;
import com.dimensiondata.cloud.client.model.ChangeServerMonitoringPlanType;
import com.dimensiondata.cloud.client.model.EnableServerMonitoringType;
import com.dimensiondata.cloud.client.model.ResponseType;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class MonitoringServiceImpl extends AbstractRestfulService implements MonitoringService
{

    public MonitoringServiceImpl(String baseUrl)
    {
        super(baseUrl);
    }

    @Override
    public ResponseType enableServerMonitoring(EnableServerMonitoringType enableServerMonitoring)
    {
        return httpClient.post("server/enableServerMonitoring",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "enableServerMonitoring"), EnableServerMonitoringType.class, enableServerMonitoring),
                ResponseType.class);
    }

    @Override
    public ResponseType changeServerMonitoringPlan(ChangeServerMonitoringPlanType changeServerMonitoringPlan)
    {
        return httpClient.post("server/changeServerMonitoringPlan",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "changeServerMonitoringPlan"), ChangeServerMonitoringPlanType.class, changeServerMonitoringPlan),
                ResponseType.class);
    }

    @Override
    public ResponseType disableServerMonitoring(String id)
    {
        return httpClient.post("server/disableServerMonitoring",
                Entity.xml("<disableServerMonitoring xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public String viewMonitoringUsageReport(String startDate, String endDate)
    {
        throw new UnsupportedOperationException();
    }
}