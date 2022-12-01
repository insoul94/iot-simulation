package com.iot.controller.controller;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;

public interface AssetController {
    Long create(AssetDto dto) throws AppException;
    AssetDto read(Long id) throws AppException;
    void delete(Long id) throws AppException;
}
