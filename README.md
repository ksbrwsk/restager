# Restager

Tool for restating all Java Apps in a CloudFoundry Space, using
spring-cloud-cloudfoundry-discovery.

** Prerequisites **

* Java 11
* Maven (https://maven.apache.org)

** Build: **

```
mvn clean install
```

Set your Cloudfoundry properties in the file application.properties or 
as Environment Variables, eg. for your local CF Dev Installation.

```
spring.cloud.cloudfoundry.org=cfdev-org
spring.cloud.cloudfoundry.space=cfdev-space
spring.cloud.cloudfoundry.url=https://api.dev.cfdev.sh
spring.cloud.cloudfoundry.username=user
spring.cloud.cloudfoundry.password=pass
```
 
