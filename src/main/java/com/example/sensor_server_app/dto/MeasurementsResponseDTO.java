package com.example.sensor_server_app.dto;

import java.util.List;

public class MeasurementsResponseDTO {
    private List<MeasurementDTO> measurements;

    public MeasurementsResponseDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
