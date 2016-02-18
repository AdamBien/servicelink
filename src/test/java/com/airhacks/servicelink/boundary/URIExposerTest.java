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
import com.airhacks.servicelink.boundary.LegacyLink;

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

    @Test
    public void fullyConfiguredURIInjection() {
        assertThat(fullyConfiguredHost, is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void fullyConfiguredWebTargetInjection() throws MalformedURLException {
        assertNotNull(fullyConfiguredWebTarget);
        assertThat(fullyConfiguredWebTarget.getUri().toString(), is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void linkNameDerivedFromFieldName() {
        assertThat(ping, is("http://42.42.42.42:8080/hugo"));
    }

    @Test
    public void uriWithEmptyPath() {
        assertThat(withoutResource, is("http://42.42.42.42:8080"));
    }

}