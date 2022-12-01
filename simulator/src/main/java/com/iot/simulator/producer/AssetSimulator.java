package com.iot.simulator.producer;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.UserException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Slf4j
public abstract class AssetSimulator implements MeasurementDtoProducer {

    private Thread simulator;
    private int intervalSec;
    protected AssetDto assetDto;

    public AssetSimulator(@NotNull AssetDto assetDto) {
        this.assetDto = Objects.requireNonNull(assetDto);
    }

    public AssetDto getAssetDto() {
        return assetDto;
    }

    public int getIntervalSec() {
        return intervalSec;
    }

    @Override
    public void start(int intervalSec) throws AppException {
        setIntervalSec(intervalSec);
        if (simulator == null || !simulator.isAlive()) {
            simulator = new Thread(() -> {
                try {
                    while (true) {
                        transfer(produce());
                        Thread.sleep(intervalSec * 1000L);
                    }
                } catch (InterruptedException | AppException e) {
                    log.info("AssetSimulator #{} interrupted.", assetDto.getId());
                }
            });
        }
        simulator.start();
    }

    @Override
    public void stop() {
        if (!simulator.isInterrupted()) {
            simulator.interrupt();
        }
    }

    @Override
    public MeasurementDto produce() {
        return MeasurementDto.builder()
                .assetId(assetDto.getId())
                .timestamp(new Date())
                .value(new Random().nextFloat(100f))
                .build();
    }

    public void setIntervalSec(int intervalSec) throws UserException {
        if (intervalSec <= 0) {
            throw new UserException("Interval must be positive.");
        }
        this.intervalSec = intervalSec;
    }
}
