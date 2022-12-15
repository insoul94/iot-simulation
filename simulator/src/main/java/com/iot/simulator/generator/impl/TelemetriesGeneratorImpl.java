package com.iot.simulator.generator.impl;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.dto.MeasurementDto;
import com.iot.simulator.generator.TelemetriesGenerator;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class TelemetriesGeneratorImpl implements TelemetriesGenerator {

    private final AssetDto assetDto;

    public TelemetriesGeneratorImpl(@NotNull AssetDto assetDto) {
        this.assetDto = Objects.requireNonNull(assetDto);
    }

    public AssetDto getAssetDto() {
        return assetDto;
    }

    @Override
    public MeasurementDto generate() {
        return MeasurementDto.builder()
                .assetId(assetDto.getId())
                .timestamp(new Date())
                .value(new Random().nextFloat(100f))
                .build();
    }
}
