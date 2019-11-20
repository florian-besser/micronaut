package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.logging.Logger;

@Controller("/greet")
public class GreetController {
    private static final Logger LOGGER = Logger.getLogger(GreetController.class.getName());

    @Get("/{name}")
    public String greet(String name) {
        LOGGER.info("Received request to greet");

        return "Hello " + name;
    }
}
