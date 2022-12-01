package com.iot.controller.mock;

import com.iot.controller.constant.AssetCategory;
import com.iot.controller.dto.AssetDto;
import com.iot.controller.entity.Asset;

import static com.iot.controller.util.HttpUtils.toJson;

public class DataMock {

    public static final Long ASSET_ID = 1L;
    public static final String ASSET_NAME = "Test Asset";
    public static final AssetCategory ASSET_CATEGORY = AssetCategory.DEVICE;


    public static Asset mockAsset() {
        return Asset.builder()
                .id(ASSET_ID)
                .name(ASSET_NAME)
                .category(ASSET_CATEGORY)
                .build();
    }

    public static AssetDto mockAssetDto() {
        return AssetDto.builder()
                .name(ASSET_NAME)
                .category(ASSET_CATEGORY)
                .build();
    }

    public static String mockAssetDtoJson() {
        return toJson(mockAssetDto());
    }
}
