package com.iot.simulator;

import com.iot.controller.IotControllerApplication;
import com.iot.controller.constant.AssetCategory;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.util.HttpUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class IotSimulatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void startMicroservices() {
//        new SpringApplicationBuilder(IotControllerApplication.class).run();
    }

    @Test
    @DisplayName("POST http://localhost:8081/asset - success")
    void given_AssetDto_when_postAsset_then_success() throws Exception {
        mockMvc.perform(post("/api/asset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockAssetDtoJson()))
                .andDo(print())

                .andExpectAll(
                        status().isCreated()
//                        result -> result.getResponse().getContentAsString()
                );
    }

    private static AssetDto mockAssetDto() {
        return AssetDto.builder()
                .name("Test Device")
                .category(AssetCategory.DEVICE)
                .build();
    }

    private static String mockAssetDtoJson() {
        return HttpUtils.toJson(mockAssetDto());
    }
}
