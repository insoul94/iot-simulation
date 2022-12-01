package com.iot.simulator.service;

import com.iot.controller.controller.AssetController;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.AssetNotFoundException;
import com.iot.simulator.producer.AssetHttpSimulator;
import com.iot.simulator.producer.MeasurementDtoProducer;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SimulatorService {

    private final Map<Long, MeasurementDtoProducer> producers;
    private final AssetController assetConnector;

    @Autowired
    public SimulatorService(AssetController assetConnector) {
        this.producers = new HashMap<>();
        this.assetConnector = assetConnector;
    }

    public Long create(@NotNull AssetDto assetDto) throws AppException {
        Long id = assetConnector.create(assetDto);
        assetDto.setId(id);
        producers.put(id, new AssetHttpSimulator(assetDto));
        return id;
    }

    public void start(long id, int intervalSec) throws AppException {
        MeasurementDtoProducer producer = getProducer(id);
        if (producer == null) {
            // TODO: Should be asynchronous
            AssetDto assetDto = assetConnector.read(id);
            if (assetDto != null) {
                producers.put(id, new AssetHttpSimulator(assetDto));
            } else {
                throw new AssetNotFoundException(id);
            }
        }
        getProducer(id).start(intervalSec);
    }

    public void stop(long id) throws AppException {
        getProducer(id).stop();
    }

    public void delete(long id) throws AppException {
        Optional.ofNullable(producers.remove(id))
                .orElseThrow(() -> new AssetNotFoundException(id));
        assetConnector.delete(id);
    }

    private MeasurementDtoProducer getProducer(long id) throws AssetNotFoundException {
        return Optional.ofNullable(producers.get(id))
                .orElseThrow(() -> new AssetNotFoundException(id));
    }
}
