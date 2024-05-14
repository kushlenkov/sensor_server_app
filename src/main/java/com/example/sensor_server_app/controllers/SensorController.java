package com.example.sensor_server_app.controllers;

import com.example.sensor_server_app.dto.SensorDTO;
import com.example.sensor_server_app.exceptions.SensorNotCreatedException;
import com.example.sensor_server_app.exceptions.SensorNotFoundException;
import com.example.sensor_server_app.models.Sensor;
import com.example.sensor_server_app.services.SensorService;
import com.example.sensor_server_app.util.MeasurementErrorResponse;
import com.example.sensor_server_app.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<SensorDTO> getSensors() {
        return sensorService.findAll()
                .stream().map(this::convertToSensorDTO)
                .collect(Collectors.toList()); // Jackson convert People objects to JSON
    }

    @GetMapping("/{id}")
    public SensorDTO getSensor(@PathVariable int id) {
        // Status 200
        return convertToSensorDTO(sensorService.findOne(id)); // Jackson convert Person objects to JSON
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMessage.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));

        // send HTTP response with empty body and status 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(SensorNotFoundException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                "Sensor with this Id wasn`t found!",
                System.currentTimeMillis()
        );

        // in HTTP response in body response and status in header
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // NOT_FUND - 404 status
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(SensorNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        // in HTTP response in body response and status in header
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
