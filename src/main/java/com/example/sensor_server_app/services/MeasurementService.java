package com.example.sensor_server_app.services;

import com.example.sensor_server_app.models.Measurement;
import com.example.sensor_server_app.repositories.MeasurementRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;

    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories) {
        this.measurementRepositories = measurementRepositories;
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepositories.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepositories.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
