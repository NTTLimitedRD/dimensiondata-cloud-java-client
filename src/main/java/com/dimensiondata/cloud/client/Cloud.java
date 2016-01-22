package com.dimensiondata.cloud.client;

public interface Cloud
{
    DatacenterService datacenter();

    NetworkDomainService networkDomain();

    VlanService vlan();

    IpAddressService ipAddress();

    FirewallService firewall();

    NatService nat();

    ServerService server();

    MonitoringService monitoring();

    NodeService node();

    PoolService pool();

    SupportService support();

    VirtualListenerService virtualListener();

    ImageService image();

    SecurityGroupService securityGroup();
}