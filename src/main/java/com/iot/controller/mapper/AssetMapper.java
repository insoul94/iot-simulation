package com.iot.controller.mapper;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.entity.Asset;
import jakarta.validation.constraints.NotNull;

public class AssetMapper {

    public static Asset toEntity(@NotNull AssetDto dto) {
        return Asset.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .build();
    }

    public static AssetDto toDto(@NotNull Asset entity) {
        return AssetDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .build();
    }
}
