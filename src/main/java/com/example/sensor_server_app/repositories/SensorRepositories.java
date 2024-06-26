package com.example.sensor_server_app.repositories;

import com.example.sensor_server_app.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepositories extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
