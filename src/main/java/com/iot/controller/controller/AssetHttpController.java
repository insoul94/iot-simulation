package com.iot.controller.controller;

import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;
import com.iot.controller.service.AssetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
@AllArgsConstructor
public class AssetHttpController implements AssetController {

    private final AssetService assetService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@RequestBody @Valid final AssetDto dto) throws AppException {
        return assetService.create(dto);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetDto read(@PathVariable final Long id) throws AppException {
        return assetService.read(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable final Long id) throws AppException {
        assetService.delete(id);
    }
}
