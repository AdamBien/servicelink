package com.airhacks.urimator.boundary;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author airhacks.com
 */
@RunWith(CdiTestRunner.class)
public class URIExposerTest {

    @LinkedContainer(linkName = "ping", portNumber = 8080, resource = "/hugo")
    String fullyConfiguredHost;

    @Test
    public void linkAndPortAndResourceAreSet() {
        assertThat(fullyConfiguredHost, is("http://42.42.42.42:8080/hugo"));
    }

}
