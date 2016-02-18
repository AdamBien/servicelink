package com.airhacks.servicelink.control;

import java.util.Objects;

/**
 *
 * @author airhacks.com
 */
public interface URIProvider {

    public static String computeURIWithEnvironmentEntries(String linkName, int portNumber, String resource) {
        return computeURIWithEnvironmentEntries(linkName, portNumber) + resource;
    }

    public static String computeURIWithEnvironmentEntries(String linkName, int portNumber) {
        String stringifiedPort = String.valueOf(portNumber);
        String portKey = computePortKey(linkName, stringifiedPort);
        String addressKey = computeAddressKey(linkName, stringifiedPort);
        return "http://" + getEnvironmentVariable(addressKey) + ":" + getEnvironmentVariable(portKey);
    }

    public static String computeUri(String linkName, int portNumber, String resource) {
        return "http://" + linkName + ":" + portNumber + resource;
    }

    public static String computeUri(String linkName, int portNumber) {
        return computeUri(linkName, portNumber, "");
    }

    static String getEnvironmentVariable(String key) {
        String variable = System.getenv(key);
        if (variable == null) {
            throw new IllegalStateException("No environment variable found for: " + key);
        }
        return variable;
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
