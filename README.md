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
HOST and PORT information.


@Inject
@LinkedContainer(linkName = "ping",portNumber = 8080,path = "/ping/resources/pings/echo/")
WebTarget ping;

@Inject
@LinkedContainer(path = "/ping/resources/pings/echo/")
WebTarget ping;



