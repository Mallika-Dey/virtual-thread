package org.example.sec7;

import org.example.sec7.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccessResponseFuture {
    private static final Logger log = LoggerFactory.getLogger("AccessResponseFuture.class");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> future = executor.submit(() -> Client.getProduct(1));
            Future<String> future1 = executor.submit(() -> Client.getProduct(2));
            Future<String> future2 = executor.submit(() -> Client.getProduct(3));
            log.info("product-1: {}", future.get());
        }
    }
}
