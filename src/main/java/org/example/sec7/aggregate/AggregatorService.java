package org.example.sec7.aggregate;

import org.example.sec7.externalservice.Client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class AggregatorService {
    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ProductDTO getProduct(int id) throws ExecutionException, InterruptedException {
        var product = executorService.submit(() -> Client.getProduct(id));
        var rating = executorService.submit(() -> Client.getRating(id));
        return new ProductDTO(id, product.get(), rating.get());
    }
}
