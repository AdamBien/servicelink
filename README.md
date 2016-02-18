# urimator

URI Helper and Injector For Linked Docker Containers

# Installation (~ 6k)

```xml
<dependency>
    <groupId>com.airhacks</groupId>
    <artifactId>servicelink</artifactId>
    <version>[RECENT]</version>
</dependency>
```
# Usage

A docker container started with a [legacy link](https://docs.docker.com/engine/userguide/networking/default_network/dockerlinks/):
`docker run (...) --link something:ping (...)`
exposes the "ping" link name as environment variables with
HOST and PORT information (e.g. `PING_PORT_8080_TCP_ADDR` and `PING_PORT_8080_TCP_PORT`): 

Servicelink extracts the information from the environment entries and makes the information available via Dependency Injection:

```java
@Inject
@LegacyLink(linkName = "ping",portNumber = 8080,path = "/ping/resources/pings/echo/")
WebTarget ping;
```

The link name doesn't have to be set in the annotation and can be derived from the field name. 
The portNumber defaults to 8080:

```java
@Inject
@LegacyLink(path = "/ping/resources/pings/echo/")
WebTarget ping;
```

The same semantics apply for the injection of links in [user-defined networks](https://docs.docker.com/engine/userguide/networking/work-with-networks/#linking-containers-in-user-defined-networks).
Links in user-defined networks do not manifest as environment variables and can be directly
used without any additional treatment.

```java
@Inject
@Link(linkName = "ping",portNumber = 8080,path = "/ping/resources/pings/echo/")
WebTarget ping;
```
