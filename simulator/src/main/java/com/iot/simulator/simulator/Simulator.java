package com.iot.simulator.simulator;

import com.iot.controller.exception.AppException;
import com.iot.simulator.generator.TelemetriesGenerator;
import com.iot.simulator.sender.TelemetriesSender;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Simulator {

    private final TelemetriesGenerator generator;
    private final TelemetriesSender sender;
    private final ScheduledExecutorService executorService;

    public Simulator(@NotNull TelemetriesGenerator generator, @NotNull TelemetriesSender sender) {
        this.generator = generator;
        this.sender = sender;
        executorService = Executors.unconfigurableScheduledExecutorService(
                Executors.newScheduledThreadPool(10));
    }

    public void start(int intervalSec) {
        executorService.scheduleAtFixedRate(this::send, 0, intervalSec, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public Long getAssetId() {
        return generator.getAssetDto().getId();
    }

    private void send() {
        try {
            sender.send(generator.generate());
        } catch (AppException e) {
            log.info("Simulator #{} failed to send telemetry.", getAssetId());
        }
    }
}
