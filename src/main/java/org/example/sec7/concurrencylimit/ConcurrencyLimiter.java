package org.example.sec7.concurrencylimit;

import org.example.sec7.ConcurrencyLimitWithSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class ConcurrencyLimiter implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimiter.class);

    private final Semaphore semaphore;
    private final ExecutorService executorService;

    public ConcurrencyLimiter(ExecutorService executorService, int limit) {
        this.semaphore = new Semaphore(limit);
        this.executorService = executorService;
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return executorService.submit(() -> wrapCallable(callable));
    }

    private <T> T wrapCallable(Callable<T> callable) {
        try {
            semaphore.acquire();
            return callable.call();
        } catch (Exception ex) {
            log.error("error", ex);
        } finally {
            semaphore.release();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.executorService.close();
    }
}
