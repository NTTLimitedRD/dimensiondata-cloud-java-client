package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.SecurityGroupService;
import com.dimensiondata.cloud.client.model.*;

import javax.ws.rs.client.Entity;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class SecurityGroupServiceImpl implements SecurityGroupService
{
    private final HttpClient httpClient;

    public SecurityGroupServiceImpl(String baseUrl)
    {
        httpClient = new HttpClient(baseUrl);
    }

    @Override
    public ResponseType createSecurityGroup(CreateSecurityGroup createSecurityGroup)
    {
        return httpClient.post("securityGroup/createSecurityGroup",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "createSecurityGroup"), CreateSecurityGroup.class, createSecurityGroup),
                ResponseType.class);
    }

    @Override
    public ResponseType addNicToSecurityGroup(AddNicToSecurityGroup addNicToSecurityGroup)
    {
        return httpClient.post("securityGroup/addNicToSecurityGroup",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "addNicToSecurityGroup"), AddNicToSecurityGroup.class, addNicToSecurityGroup),
                ResponseType.class);
    }

    @Override
    public ResponseType removeNicFromSecurityGroup(RemoveNicFromSecurityGroup removeNicFromSecurityGroup)
    {
        return httpClient.post("securityGroup/removeNicFromSecurityGroup",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "removeNicFromSecurityGroup"), RemoveNicFromSecurityGroup.class, removeNicFromSecurityGroup),
                ResponseType.class);
    }

    @Override
    public ResponseType deleteSecurityGroup(String id)
    {
        return httpClient.post("securityGroup/deleteSecurityGroup",
                Entity.xml("<deleteSecurityGroup xmlns=\"" + HttpClient.DEFAULT_NAMESPACE + "\" id=\"" + id + "\"/>"),
                ResponseType.class);
    }

    @Override
    public SecurityGroups listSecurityGroups(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        return httpClient.get("securityGroup/securityGroup", SecurityGroups.class,
                filter.concatenateParameters(
                        new Param(Param.PAGE_SIZE, pageSize),
                        new Param(Param.PAGE_NUMBER, pageNumber),
                        new Param(Param.ORDER_BY, orderBy.concatenateParameters())));
    }

    @Override
    public SecurityGroupType getSecurityGroup(String id)
    {
        return httpClient.get("securityGroup/securityGroup/" + id, SecurityGroupType.class);
    }

    @Override
    public ResponseType editSecurityGroup(EditSecurityGroup editSecurityGroup)
    {
        return httpClient.post("securityGroup/editSecurityGroup",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "editSecurityGroup"), EditSecurityGroup.class, editSecurityGroup),
                ResponseType.class);
    }

    @Override
    public String getState(String id)
    {
        return getSecurityGroup(id).getState();
    }
}