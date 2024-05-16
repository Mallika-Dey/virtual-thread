package org.example.sec7.aggregate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class AggregatorApplication {
    private static final Logger log = LoggerFactory.getLogger("AggregatorApplication.class");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        log.info("product-1: {}",aggregator.getProduct(1));
    }
}
