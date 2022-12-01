package com.iot.controller.dto;

import com.iot.controller.constant.AssetCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private AssetCategory category;
}
