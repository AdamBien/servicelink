package com.airhacks.urimator;

import java.util.Objects;

/**
 *
 * @author airhacks.com
 */
public interface URIProvider {

    public static String computeURI(String linkName, String portNumber, String reminder) {
        return computeURI(linkName, portNumber) + reminder;
    }

    public static String computeURI(String linkName, String portNumber) {
        String portKey = computePortKey(linkName, portNumber);
        String addressKey = computeAddressKey(linkName, portNumber);
        return "http://" + System.getenv(addressKey) + ":" + System.getenv(portKey);

    }

    static String computeKeyPrefix(String linkName, String portNumber) {
        Objects.requireNonNull(linkName, "Docker link cannot be null");
        Objects.requireNonNull(portNumber, "Docker port cannot be null");
        String upperLink = linkName.toUpperCase();
        return upperLink + "_PORT_" + portNumber + "_TCP_";
    }

    static String computePortKey(String linkName, String portNumber) {
        return computeKeyPrefix(linkName, portNumber) + "PORT";
    }

    static String computeAddressKey(String linkName, String portNumber) {
        return computeKeyPrefix(linkName, portNumber) + "ADDR";
    }
}
