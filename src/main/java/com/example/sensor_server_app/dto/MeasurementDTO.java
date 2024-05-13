package com.example.sensor_server_app.dto;

import com.example.sensor_server_app.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MeasurementDTO {
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotEmpty(message = "The value should not be empty")
    @Size(min = -100, max = 100, message = "The value should be between -100 and 100")
    private int value;

    @Column(name = "raining")
    @NotEmpty(message = "The raining should not be empty")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName ="id")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
