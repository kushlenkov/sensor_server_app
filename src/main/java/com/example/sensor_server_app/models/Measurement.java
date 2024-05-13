package com.example.sensor_server_app.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int value;

    private boolean raining;

    private String sensorName;

    public Measurement() {

    }

    public Measurement(int id, int value, boolean raining, String sensorName) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensorName = sensorName;
    }

}
