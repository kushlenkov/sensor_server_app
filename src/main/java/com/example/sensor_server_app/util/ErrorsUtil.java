package com.example.sensor_server_app.util;

import com.example.sensor_server_app.exceptions.MeasurementNotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMessage
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage()) // TODO
                    .append(";");
        }

        throw new MeasurementNotCreatedException(errorMessage.toString());
    }
}
