## Java Client for DimensionData Cloud API 2

### API Documentation
https://community.opsourcecloud.net/Browse.jsp?id=e5b1a66815188ad439f76183b401f026

### Requirements
Java 8

### Maven Configuration
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.DimensionDataCBUSydney</groupId>
  <artifactId>dimensiondata-cloud-java-client</artifactId>
  <version>2.1.2</version>
</dependency>
```

### Java Example
```java
UserSession.set(new User("username", "password"));

Cloud cloud = new CloudImpl(ApiUrls.DIMENSIONDATA.Europe_EU.getApiUrl());

Datacenters datacenters = cloud.datacenter().listDatacenters(10, 1, OrderBy.EMPTY);

NetworkDomains networkDomains = cloud.networkDomain().listNetworkDomains(10, 1, OrderBy.EMPTY, new Filter(
    new Param("datacenterId", "EU7")
));
```

### Javascript Example
Using Oracle Nashorn, a Javascript interpreter included in Java 8, is easy to create executable scripts
to configure the DimensionData Cloud. Nashorn has also a shell that can be used to execute the
same commands on the fly.

The following is an example of a script that prints all the datacenters and then the
network domains of a specific datacenter:
```javascript
#!/usr/bin/env jjs -scripting -J-Djava.class.path=dimensiondata-cloud-api-2.1.2-jar-with-dependencies.jar

var DimensionData = new JavaImporter(
  com.dimensiondata.cloud.client,
  com.dimensiondata.cloud.client.http,
  com.dimensiondata.cloud.client.model
);

with (DimensionData) {
  UserSession.set(new User("username", "password"));

  var cloud = new CloudImpl("https://api-eu.dimensiondata.com");

  var datacenters = cloud.datacenter().listDatacenters(10, 1, OrderBy.EMPTY).getDatacenter();
  datacenters.forEach(function(i) { print("Datacenter: " + i.getId()); });

  var networkDomains = cloud.networkDomain().listNetworkDomains(10, 1, OrderBy.EMPTY, new Filter(
    new Param("datacenterId", "EU7"))).getNetworkDomain();
  networkDomains.forEach(function(i) { print("NetworkDomain: " + i.getName()); });
}
```
To execute a script on the command line (jjs executable is included in Java 8):
```bash
jjs -J-Djava.class.path=dimensiondata-cloud-api-2.1.2-jar-with-dependencies.jar my-script.js
```
The Jar that include all the dependencies can be downloaded from the Release Page:
https://github.com/DimensionDataCBUSydney/dimensiondata-cloud-java-client/releases/download/2.1.2/dimensiondata-cloud-api-2.1.2-jar-with-dependencies.jar

### Asynchronous operations
Methods returning a Callable implementation can be used to wait for asynchronous operations completion.
In the following examples we're using the [Awaitility library](https://github.com/jayway/awaitility).

Deploy Network Domain:
```java
ResponseType deployNetworkDomainResponse = cloud.networkDomain().deployNetworkDomain(deployNetworkDomain);
String networkDomainId = cloud.networkDomain().getIdFromDeployResponse(deployNetworkDomainResponse);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.networkDomain().isNetworkDomainInNormalState(networkDomainId));
```
Edit Network Domain:
```java
cloud.networkDomain().editNetworkDomain(editNetworkDomain);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.networkDomain().isNetworkDomainInNormalState(networkDomainId));
```
Delete Network Domain:
```java
cloud.networkDomain().deleteNetworkDomain(networkDomainId);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.networkDomain().isNetworkDomainDeleted(networkDomainId));
```
Deploy Vlan:
```java
ResponseType deployVlanResponse = cloud.vlan().deployVlan(deployVlan);
String vlanId = cloud.vlan().getIdFromDeployResponse(deployVlanResponse);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.vlan().isVlanInNormalState(vlanId));
```
Delete Vlan:
```java
cloud.vlan().deleteVlan(vlanId);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.vlan().isVlanDeleted(vlanId));
```
Deploy Server:
```java
ResponseType deployServerResponse = cloud.server().deployServer(deployServer);
String serverId = cloud.server().getIdFromDeployResponse(deployServerResponse);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.server().isServerInNormalState(serverId));
```
Delete Server:
```java
cloud.server().deleteServer(serverId);
await().atMost(5, TimeUnit.MINUTES)
  .pollDelay(30, TimeUnit.SECONDS)
  .until(cloud.server().isServerDeleted(serverId));
```