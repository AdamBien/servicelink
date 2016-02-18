# urimator

URI Helper For Linked Docker Containers

# Installation (~ 6k)

```xml
<dependency>
    <groupId>com.airhacks</groupId>
    <artifactId>urimator</artifactId>
    <version>[RECENT]</version>
</dependency>
```
# Usage

A docker container started with:
`docker run (...) --link something:ping (...)`
exposes the "ping" link name as environment variables with
HOST and PORT information (e.g. `PING_PORT_8080_TCP_ADDR` and `PING_PORT_8080_TCP_PORT`): 

URImator extracts the information from the environment entries and makes the information available via Dependency Injection:

```java
@Inject
@LinkedContainer(linkName = "ping",portNumber = 8080,path = "/ping/resources/pings/echo/")
WebTarget ping;
```

The link name doesn't have to be set in the annotation and can be derived from the field name. The portNumber defaults to 8080:

```java
@Inject
@LinkedContainer(path = "/ping/resources/pings/echo/")
WebTarget ping;
```
