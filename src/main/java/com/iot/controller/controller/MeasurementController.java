package com.iot.controller.controller;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.AppException;
import org.springframework.validation.BindingResult;

public interface MeasurementController {
    Long create(MeasurementDto dto, BindingResult bindingResult) throws AppException;
}
