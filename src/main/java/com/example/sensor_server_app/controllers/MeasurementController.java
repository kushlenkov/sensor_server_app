package com.example.sensor_server_app.controllers;

import com.example.sensor_server_app.dto.MeasurementDTO;
import com.example.sensor_server_app.exceptions.MeasurementNotCreatedException;
import com.example.sensor_server_app.exceptions.MeasurementNotFoundException;
import com.example.sensor_server_app.models.Measurement;
import com.example.sensor_server_app.services.MeasurementService;
import com.example.sensor_server_app.util.MeasurementErrorResponse;
import com.example.sensor_server_app.util.MeasurementValidator;
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

import static com.example.sensor_server_app.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 MeasurementValidator measurementValidator,
                                 ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()); // Jackson convert People objects to JSON
    }

    @GetMapping("/{id}")
    public MeasurementDTO getMeasurement(@PathVariable int id) {
        // Status 200
        return convertToMeasurementDTO(measurementService.findOne(id)); // Jackson convert Person objects to JSON
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult); // interesting: import static methods????????
        }

        measurementService.add(measurementToAdd);
        // send HTTP response with empty body and status 200
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotFoundException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                "Measurement with this Id wasn`t found!",
                System.currentTimeMillis()
        );

        // in HTTP response in body response and status in header
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // NOT_FUND - 404 status
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), // Build error message in another method
                System.currentTimeMillis()
        );

        // in HTTP response in body response and status in header
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // BAD_REQUEST - 400 status
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
