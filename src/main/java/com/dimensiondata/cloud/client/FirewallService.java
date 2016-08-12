package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.http.StateService;
import com.dimensiondata.cloud.client.model.*;

public interface FirewallService extends StateService
{
    String PARAMETER_ID = "id";
    String PARAMETER_NETWORKDOMAIN_ID = "networkDomainId";
    String PARAMETER_NAME = "name";
    String PARAMETER_CREATE_TIME = "createTime";
    String PARAMETER_STATE = "state";

    FirewallRules listFirewallRules(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType createFirewallRule(CreateFirewallRuleType createFirewallRule);

    FirewallRuleType getFirewallRule(String id);

    ResponseType editFirewallRule(EditFirewallRuleType editFirewallRule);

    ResponseType deleteFirewallRule(String id);

    ResponseType createIpAddressList(CreateIpAddressList createIpAddressList);

    IpAddressLists listIpAddressLists(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    IpAddressListType getIpAddressList(String id);

    ResponseType editIpAddressList(EditIpAddressList editIpAddressList);

    ResponseType deleteIpAddressList(String id);

    ResponseType createPortList(CreatePortList createPortList);

    PortLists listPortLists(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    PortListType getPortList(String id);

    ResponseType editPortList(EditPortList editPortList);

    ResponseType deletePortList(String id);
}