package com.iot.controller.service;

import com.iot.controller.dao.MeasurementRepository;
import com.iot.controller.dto.MeasurementDto;
import com.iot.controller.entity.Measurement;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.SystemException;
import com.iot.controller.mapper.MeasurementMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public Long create(MeasurementDto dto) throws AppException {
            Measurement entity = MeasurementMapper.toEntity(dto);
            Measurement savedEntity = measurementRepository.saveAndFlush(entity);
            log.info("Measurement #{} SAVED", savedEntity.getId());
            return Optional.ofNullable(savedEntity.getId()).orElseThrow(SystemException::new);
    }
}
