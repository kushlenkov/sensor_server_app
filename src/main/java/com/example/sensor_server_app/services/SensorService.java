package com.example.sensor_server_app.services;

import com.example.sensor_server_app.models.Sensor;
import com.example.sensor_server_app.repositories.SensorRepositories;
import com.example.sensor_server_app.exceptions.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorService {

    private final SensorRepositories sensorRepositories;

    @Autowired
    public SensorService(SensorRepositories sensorRepositories) {
        this.sensorRepositories = sensorRepositories;
    }

    public List<Sensor> findAll() {
        return sensorRepositories.findAll();
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundSensor = sensorRepositories.findById(id);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }

    @Transactional
    public void save(Sensor sensor) {
        enrichSensor(sensor);
        sensorRepositories.save(sensor);
    }

    public void enrichSensor(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
    }

    // For SensorValidator
    public Optional<Sensor> findByName(String name) {
        return sensorRepositories.findByName(name);
    }
}
