package com.iot.controller.mapper;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.entity.Measurement;
import jakarta.validation.constraints.NotNull;

public class MeasurementMapper {

    public static Measurement toEntity(@NotNull MeasurementDto dto) {
        return Measurement.builder()
                .assetId(dto.getAssetId())
                .timestamp(dto.getTimestamp())
                .value(dto.getValue())
                .build();
    }

    public static MeasurementDto toDto(@NotNull Measurement entity) {
        return MeasurementDto.builder()
                .assetId(entity.getAssetId())
                .timestamp(entity.getTimestamp())
                .value(entity.getValue())
                .build();
    }
}
