package com.iot.simulator.sender;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.AppException;

public interface TelemetriesSender {

    void send(MeasurementDto measurement) throws AppException;
}
