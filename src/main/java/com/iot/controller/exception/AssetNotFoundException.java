package com.iot.controller.exception;

public class AssetNotFoundException extends UserException{
    public AssetNotFoundException(Long id) {
        super("Asset #" + id + " not found.");
    }
}
