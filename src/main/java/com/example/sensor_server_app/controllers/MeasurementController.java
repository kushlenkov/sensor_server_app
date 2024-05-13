package com.example.sensor_server_app.controllers;

import com.example.sensor_server_app.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService service;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


}
