package com.iot.simulator.generator;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.dto.MeasurementDto;

public interface TelemetriesGenerator {
    MeasurementDto generate();

    AssetDto getAssetDto();
}
