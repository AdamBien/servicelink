package com.airhacks.servicelink.control;

import com.airhacks.servicelink.control.URIProvider;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class URIProviderTest {

    @Test
    public void computeAddressKey() {
        String addressKey = URIProvider.computeAddressKey("ping", "8080");
        assertThat(addressKey, is("PING_PORT_8080_TCP_ADDR"));
    }

    @Test
    public void computePortKey() {
        String portKey = URIProvider.computePortKey("ping", "8080");
        assertThat(portKey, is("PING_PORT_8080_TCP_PORT"));
    }

}
