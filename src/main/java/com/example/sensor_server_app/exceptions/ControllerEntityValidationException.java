package com.example.sensor_server_app.exceptions;

public class ControllerEntityValidationException extends  RuntimeException {
    public ControllerEntityValidationException(String message) {
        super(message);
    }
}