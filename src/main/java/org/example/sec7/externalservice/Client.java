package org.example.sec7.externalservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Client {
    public static final Logger log = LoggerFactory.getLogger("Client.class");
    public static final String PRODUCT_REQUEST = "http://localhost:7070/sec01/product/%d";
    public static final String RATING_REQUEST = "http://localhost:7070/sec01/rating/%d";

    public static String getProduct(int id) {
        return callEternalService(PRODUCT_REQUEST.formatted(id));
    }

    public static Integer getRating(int id) {
        return Integer.parseInt(callEternalService(RATING_REQUEST.formatted(id)));
    }

    private static String callEternalService(String url) {
        log.info("calling {}", url);
        try (var stream = URI.create(url).toURL().openStream()) {
            return new String(stream.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
