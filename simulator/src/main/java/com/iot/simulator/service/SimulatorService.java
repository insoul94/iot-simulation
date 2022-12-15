package com.iot.simulator.service;

import com.iot.controller.controller.AssetController;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.AssetNotFoundException;
import com.iot.controller.exception.UserException;
import com.iot.simulator.generator.impl.TelemetriesGeneratorImpl;
import com.iot.simulator.sender.impl.TelemetriesHttpSender;
import com.iot.simulator.simulator.Simulator;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SimulatorService {

    private final AssetController assetRemoteController;
    private Simulator simulator;

    @Autowired
    public SimulatorService(AssetController assetRemoteController) {
        this.assetRemoteController = assetRemoteController;
    }

    public Long create(@NotNull AssetDto assetDto) throws AppException {
        Long id = assetRemoteController.create(assetDto);
        assetDto.setId(id);
        // TODO: add possibility to create multiple configurable simulators
        simulator = new Simulator(
                new TelemetriesGeneratorImpl(assetDto),
                new TelemetriesHttpSender());
        return id;
    }

    public void start(long id, int intervalSec) throws AppException {
        AssetDto assetDto = assetRemoteController.read(id);
        checkSimulator(id);
        if (assetDto != null) {
            simulator.start(intervalSec);
        } else {
            throw new AssetNotFoundException(id);
        }
    }

    public void stop(long id) throws AppException {
        checkSimulator(id);
        simulator.stop();
    }

    public void delete(long id) throws AppException {
        checkSimulator(id);
        simulator.stop();
        simulator = null;
        assetRemoteController.delete(id);
    }

    private void checkSimulator(long id) throws AppException{
        if (simulator == null || !simulator.getAssetId().equals(id)) {
            throw new UserException("Simulator for asset #" + id + " is not found.");
        }
    }
}
