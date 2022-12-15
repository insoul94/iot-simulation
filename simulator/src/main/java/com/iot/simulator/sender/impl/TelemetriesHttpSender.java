package com.iot.simulator.sender.impl;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.SystemException;
import com.iot.simulator.sender.TelemetriesSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;


@Slf4j
public class TelemetriesHttpSender implements TelemetriesSender {

    private final RestTemplate restTemplate;

    public TelemetriesHttpSender() {
        restTemplate = new RestTemplate();
    }

    @Override
    public void send(MeasurementDto measurement) throws SystemException {
        Long measurementId = restTemplate
                .postForObject("http://localhost:8080/measurement", measurement, Long.class);
        log.info("Measurement #{} SENT", measurementId);
        if (measurementId == null) {
            throw new SystemException("Could not save measurement");
        }
    }
}
