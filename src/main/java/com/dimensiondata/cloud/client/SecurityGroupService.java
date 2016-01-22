package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface SecurityGroupService
{
    ResponseType createSecurityGroup(CreateSecurityGroup createSecurityGroup);

    ResponseType addNicToSecurityGroup(AddNicToSecurityGroup addNicToSecurityGroup);

    ResponseType removeNicFromSecurityGroup(RemoveNicFromSecurityGroup removeNicFromSecurityGroup);

    ResponseType deleteSecurityGroup(String id);

    SecurityGroups listSecurityGroups(int pageSize, int pageNumber, OrderBy orderBy);

    SecurityGroupType getSecurityGroup(String id);

    ResponseType editSecurityGroup(EditSecurityGroup editSecurityGroup);
}