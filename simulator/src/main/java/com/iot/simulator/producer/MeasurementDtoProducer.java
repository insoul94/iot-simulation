package com.iot.simulator.producer;

import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.exception.AppException;

public interface MeasurementDtoProducer {
    void start(int intervalSec) throws AppException;
    void stop();
    MeasurementDto produce();
    void transfer(MeasurementDto measurement) throws AppException;
}
