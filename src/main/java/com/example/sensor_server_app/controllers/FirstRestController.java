package com.example.sensor_server_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody for each method
@RequestMapping("/api")
public class FirstRestController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello world!";
    }
}
