package com.iot.simulator.producer;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.SystemException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AssetHttpSimulator extends AssetSimulator{

    private final RestTemplate restTemplate;

    public AssetHttpSimulator(@NotNull AssetDto assetDto) {
        super(assetDto);
        restTemplate = new RestTemplate();
    }

    @Override
    public void transfer(MeasurementDto measurement) throws SystemException {
        Long measurementId = restTemplate
                .postForObject("http://localhost:8080/measurement", measurement, Long.class);
        log.info("Measurement #{} SENT", measurementId);
        if (measurementId == null) {
            throw new SystemException("Could not save measurement");
        }
    }
}
