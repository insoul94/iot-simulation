package com.iot.simulator.connector;

import com.iot.controller.controller.AssetController;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.exception.AppException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class  AssetHttpConnector implements AssetController {

    private final RestTemplate restTemplate;

    public AssetHttpConnector() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Long create(AssetDto dto) throws AppException {
        return restTemplate.postForObject("http://localhost:8080/asset", dto, Long.class);
    }

    @Override
    public AssetDto read(Long id) throws AppException {
        return restTemplate.getForObject("http://localhost:8080/asset/" + id, AssetDto.class);
    }

    @Override
    public void delete(Long id) throws AppException {
        restTemplate.delete("http://localhost:8080/asset/" + id);
    }
}
