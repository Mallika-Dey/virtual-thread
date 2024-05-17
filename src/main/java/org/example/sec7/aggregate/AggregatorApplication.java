package org.example.sec7.aggregate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class AggregatorApplication {
    private static final Logger log = LoggerFactory.getLogger("AggregatorApplication.class");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        List<Future<ProductDTO>> productDTOFuture = IntStream.rangeClosed(1, 50).mapToObj((id) -> executor.submit(() -> aggregator.getProduct(id))).toList();
        var productDTOs = productDTOFuture.stream().map(AggregatorApplication::getProductDto).toList();

        log.info("product-1: {}", productDTOs);
    }

    private static ProductDTO getProductDto(Future<ProductDTO> productDTOFuture) {
        try {
            return productDTOFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
