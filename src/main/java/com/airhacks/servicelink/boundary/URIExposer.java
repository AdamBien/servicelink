package com.airhacks.servicelink.boundary;

import com.airhacks.servicelink.control.URIProvider;
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
    @LegacyLink
    public String exposeLegacyLink(InjectionPoint ip) {
        Annotated field = ip.getAnnotated();
        LegacyLink link = field.getAnnotation(LegacyLink.class);
        String linkName = link.name();
        if (linkName.isEmpty()) {
            linkName = ip.getMember().getName().toUpperCase();
        }
        int portNumber = link.portNumber();
        String resource = link.path();
        if (resource.isEmpty()) {
            return URIProvider.computeURIWithEnvironmentEntries(linkName, portNumber);
        } else {
            return URIProvider.computeURIWithEnvironmentEntries(linkName, portNumber, resource);
        }
    }

    @Produces
    @LegacyLink
    public WebTarget exposeLegacyLinkWebTarget(InjectionPoint ip) {
        Client client = ClientBuilder.newClient();
        return client.target(exposeLegacyLink(ip));
    }

    @Produces
    @Link
    public String expose(InjectionPoint ip) {
        Annotated field = ip.getAnnotated();
        Link link = field.getAnnotation(Link.class);
        String linkName = link.name();
        if (linkName.isEmpty()) {
            linkName = ip.getMember().getName().toUpperCase();
        }
        int portNumber = link.portNumber();
        String resource = link.path();
        if (resource.isEmpty()) {
            return URIProvider.computeUri(linkName, portNumber);
        } else {
            return URIProvider.computeUri(linkName, portNumber, resource);
        }
    }

    @Produces
    @Link
    public WebTarget exposeTarget(InjectionPoint ip) {
        Client client = ClientBuilder.newClient();
        return client.target(expose(ip));
    }

}
