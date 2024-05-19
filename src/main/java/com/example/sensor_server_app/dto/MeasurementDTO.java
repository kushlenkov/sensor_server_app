package com.example.sensor_server_app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTO {
    @NotNull(message = "The value should not be null")
    @Min(value = -100, message = "The value should be between -100 and 100")
    @Max(value = 100, message = "The value should be between -100 and 100")
    private Double value;

    @NotNull(message = "The raining should not be null")
    private Boolean raining;

    @NotNull(message = "The sensorDTO should not be null")
    private SensorDTO sensor;

    private LocalDateTime createdAt;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    // Jackson смотрит на название геттера, отсекает is и отсавляет название поля
    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
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

    public Boolean getRaining() {
        return raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}