package com.example.sensor_server_app.dto;

import java.util.List;

public class SensorsResponseDTO {
    private List<SensorDTO> sensors;

    public SensorsResponseDTO(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }
}
