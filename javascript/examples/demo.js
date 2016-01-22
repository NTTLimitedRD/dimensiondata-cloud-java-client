#!/usr/bin/env jjs -scripting -J-Djava.class.path=dimensiondata-cloud-api-2.1.1-SNAPSHOT-jar-with-dependencies.jar

var DimensionData = new JavaImporter(
  com.dimensiondata.cloud.client,
  com.dimensiondata.cloud.client.http,
  com.dimensiondata.cloud.client.model
);

with (DimensionData) {
  UserSession.set(new User("user", "password"));

  var cloud = new CloudImpl("https://api-eu.dimensiondata.com");

  var datacenters = cloud.datacenter().listDatacenters(10, 1, OrderBy.EMPTY).getDatacenter();
  datacenters.forEach(function(i) { print("Datacenter: " + i.getId()); });

  var networkDomains = cloud.networkDomain().listNetworkDomains(10, 1, OrderBy.EMPTY, new Filter(
    new Param("datacenterId", "EU7"))).getNetworkDomain();
  networkDomains.forEach(function(i) { print("NetworkDomain: " + i.getName()); });
}
