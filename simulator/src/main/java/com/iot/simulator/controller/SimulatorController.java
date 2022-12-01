package com.iot.simulator.controller;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.UserException;
import com.iot.simulator.service.SimulatorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset")
@AllArgsConstructor
public class SimulatorController {

    private final SimulatorService simulatorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@RequestBody @Valid AssetDto dto, BindingResult bindingResult) throws AppException {
        if (bindingResult.hasErrors()) {
            throw new UserException();
        }
        return simulatorService.create(dto);
    }

    @PostMapping(path = "/{id}/start/{intervalSec}")
    public void start(@PathVariable long id, @PathVariable int intervalSec) throws AppException {
        simulatorService.start(id, intervalSec);
    }

    @PostMapping(path = "/{id}/stop")
    public void stop(@PathVariable long id) throws AppException {
        simulatorService.stop(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) throws AppException {
        simulatorService.delete(id);
    }
}