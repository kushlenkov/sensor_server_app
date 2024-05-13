package com.example.sensor_server_app.repositories;

import com.example.sensor_server_app.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepositories extends JpaRepository<Measurement, Integer> {
}
