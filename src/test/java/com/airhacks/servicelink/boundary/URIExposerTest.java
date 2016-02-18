package com.airhacks.servicelink.boundary;

import java.net.MalformedURLException;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author airhacks.com
 */
@RunWith(CdiTestRunner.class)
public class URIExposerTest {

    @Inject
    @LegacyLink(name = "ping", portNumber = 8080, path = "/hugo")
    String fullyConfiguredHost;

    @Inject
    @LegacyLink(name = "ping", portNumber = 8080, path = "/hugo")
    WebTarget fullyConfiguredWebTarget;

    @Inject
    @LegacyLink(portNumber = 8080, path = "/hugo")
    String ping;

    @Inject
    @LegacyLink(name = "ping", portNumber = 8080)
    String withoutResource;

    @Inject
    @Link(name = "container-name", portNumber = 8080, path = "/hugo")
    String fullyConfiguredLink;

    @Test
    public void fullyConfiguredURIInjection() {
        assertThat(fullyConfiguredLink, is("http://container-name:8080/hugo"));
    }

    @Test
    public void fullyConfiguredLegacyURIInjection() {
        assertThat(fullyConfiguredHost, is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void fullyConfiguredWebTargetInjection() throws MalformedURLException {
        assertNotNull(fullyConfiguredWebTarget);
        assertThat(fullyConfiguredWebTarget.getUri().toString(), is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void legacyLinkNameDerivedFromFieldName() {
        assertThat(ping, is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void legacyUriWithEmptyPath() {
        assertThat(withoutResource, is("http://42.42.42.42:8080"));
    }

}
