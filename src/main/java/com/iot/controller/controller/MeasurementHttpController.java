package com.iot.controller.controller;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.UserException;
import com.iot.controller.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurement")
@AllArgsConstructor
@Slf4j
public class MeasurementHttpController implements MeasurementController{

    private final MeasurementService measurementService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@RequestBody @Valid MeasurementDto dto, BindingResult bindingResult) throws AppException {
        log.info("Measurement in: {}", dto);
        if (bindingResult.hasErrors()) {
            throw new UserException();
        }
        return measurementService.create(dto);
    }
}
