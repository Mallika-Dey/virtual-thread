package org.example.sec7;

import org.example.sec7.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyLimit {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimit.class);

    public static void main(String[] args) {
//        execute(Executors.newCachedThreadPool(), 20);
//        execute(Executors.newFixedThreadPool(3), 20);
        execute(Executors.newFixedThreadPool(3, Thread.ofVirtual().name("VT", 1).factory()), 20);
    }

    public static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 1; i <= taskCount; i++) {
                int j = i;
                executorService.submit(() -> printProductInfo(j));
            }
            log.info("submitted");
        }
    }

    private static void printProductInfo(int id) {
        log.info("{} -> {}", id, Client.getProduct(id));
    }
}
