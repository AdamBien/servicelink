package com.airhacks.urimator.boundary;

import com.airhacks.urimator.control.URIProvider;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
public class URIExposer {

    @Produces
    @LinkedContainer
    public String expose(InjectionPoint ip) {
        Annotated field = ip.getAnnotated();
        LinkedContainer link = field.getAnnotation(LinkedContainer.class);
        String linkName = link.linkName();
        if (linkName.isEmpty()) {
            linkName = ip.getMember().getName().toUpperCase();
        }
        int portNumber = link.portNumber();
        String resource = link.resource();
        if (resource.isEmpty()) {
            return URIProvider.computeURI(linkName, portNumber);
        } else {
            return URIProvider.computeURI(linkName, portNumber, resource);
        }
    }

    @Produces
    @LinkedContainer
    public WebTarget exposeWebTarget(InjectionPoint ip) {
        Client client = ClientBuilder.newClient();
        return client.target(expose(ip));
    }

}
