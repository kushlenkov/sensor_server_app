package com.example.sensor_server_app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "The value should not be null")
    @Min(value = -100, message = "The value should be between -100 and 100")
    @Max(value = 100, message = "The value should be between -100 and 100")
    private double value;

    @NotNull(message = "The raining should not be null")
    private boolean raining;

    @NotNull(message = "The sensorDTO should not be null")
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // Jackson смотрит на название геттера, отсекает is и отсавляет название поля
    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    // rename method for use with Jackson and Measurement class
    public SensorDTO getSensor() {
        return sensor;
    }

    // rename method for use with Jackson and Measurement class
    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}