package com.iot.controller.service;

import com.iot.controller.dao.AssetRepository;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.entity.Asset;
import com.iot.controller.exception.AppException;
import com.iot.controller.exception.SystemException;
import com.iot.controller.exception.UserException;
import com.iot.controller.mapper.AssetMapper;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AssetService {

    private final AssetRepository assetRepository;

    public Long create(@NotNull final AssetDto dto) throws AppException {
        Asset entity = AssetMapper.toEntity(dto);
        Asset savedEntity = assetRepository.saveAndFlush(entity);
        return Optional.ofNullable(savedEntity.getId()).orElseThrow(SystemException::new);
    }

    public AssetDto read(@NotNull final Long id) throws AppException {
        return AssetMapper.toDto(findById(id));
    }

    public void delete(@NotNull final Long id) throws AppException {
        assetRepository.delete(findById(id));
    }

    private Asset findById(@NotNull final Long id) throws AppException {
        return Optional
                .of(assetRepository.findById(id))
                .get()
                .orElseThrow(() -> new UserException("Asset #" + id + " not found."));
    }
}
