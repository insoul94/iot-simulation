package com.iot.controller;


import com.iot.controller.dao.AssetRepository;
import com.iot.controller.dao.MeasurementRepository;
import com.iot.controller.dto.AssetDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static com.iot.controller.mock.DataMock.mockAssetDto;
import static com.iot.controller.mock.DataMock.mockAssetDtoJson;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IotControllerApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @AfterEach
    void cleanUp() {
        assetRepository.deleteAll();
        measurementRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /asset - success")
    void Given_AssetDto_When_PostAsset_Then_Success() {
        // Given, When
        ResponseEntity<Long> response = this.restTemplate.exchange(
                RequestEntity
                        .post(getApiRootPath())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mockAssetDtoJson()),
                Long.class
        );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isGreaterThan(0);
    }

    @Test
    @DisplayName("GET /asset/{id} - success")
    void Given_IdOfExistingAsset_When_GetAsset_Then_Success() {
        // Given
        AssetDto requestDto = mockAssetDto();
        Long id = this.restTemplate.postForObject(getApiRootPath(), requestDto, Long.class);
        // When
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                RequestEntity
                        .get(getApiRootPath() + "/" + id).build(),
                AssetDto.class
        );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(id);
        assertThat(response.getBody().getName()).isEqualTo(requestDto.getName());
        assertThat(response.getBody().getCategory()).isEqualTo(requestDto.getCategory());
    }

    @Test
    @DisplayName("DELETE /asset/{id} - success")
    void Given_IdOfExistingAsset_When_DeleteAsset_Then_Success() {
        // Given
        AssetDto requestDto = mockAssetDto();
        Long id = this.restTemplate.postForObject(getApiRootPath(), requestDto, Long.class);
        // When
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                RequestEntity
                        .delete(getApiRootPath() + "/" + id).build(),
                AssetDto.class
        );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    private String getApiRootPath() {
        return "http://localhost:" + port + "/asset";
    }
}
