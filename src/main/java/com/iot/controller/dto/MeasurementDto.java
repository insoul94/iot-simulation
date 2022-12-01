package com.iot.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    @Positive
    @NotNull
    @JsonProperty("asset_id")
    private Long assetId;

    @NotNull
    private Date timestamp;

    @NotNull
    private Float value;
}
