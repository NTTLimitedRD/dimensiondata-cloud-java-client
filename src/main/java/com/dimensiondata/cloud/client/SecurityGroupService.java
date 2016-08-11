package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface SecurityGroupService extends StateService
{
    ResponseType createSecurityGroup(CreateSecurityGroup createSecurityGroup);

    ResponseType addNicToSecurityGroup(AddNicToSecurityGroup addNicToSecurityGroup);

    ResponseType removeNicFromSecurityGroup(RemoveNicFromSecurityGroup removeNicFromSecurityGroup);

    ResponseType deleteSecurityGroup(String id);

    SecurityGroups listSecurityGroups(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    SecurityGroupType getSecurityGroup(String id);

    ResponseType editSecurityGroup(EditSecurityGroup editSecurityGroup);
}