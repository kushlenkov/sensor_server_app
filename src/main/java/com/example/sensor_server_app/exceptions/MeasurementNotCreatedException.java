package com.example.sensor_server_app.exceptions;

public class MeasurementNotCreatedException extends RuntimeException {

    public MeasurementNotCreatedException(String message) {
        super(message);
    }
}
