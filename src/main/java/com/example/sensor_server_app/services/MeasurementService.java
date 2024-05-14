package com.example.sensor_server_app.services;

import com.example.sensor_server_app.exceptions.MeasurementNotFoundException;
import com.example.sensor_server_app.models.Measurement;
import com.example.sensor_server_app.repositories.MeasurementRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories,
                              SensorService sensorService) {
        this.measurementRepositories = measurementRepositories;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepositories.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementRepositories.findById(id);
        return foundMeasurement.orElseThrow(MeasurementNotFoundException::new);
    }

    @Transactional
    public void add(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepositories.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        // Find the sensor in DB and add to the measurement
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }
}