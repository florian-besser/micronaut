package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.logging.Logger;

@Controller("/reverse")
public class StringReverserController {
    private static final Logger LOGGER = Logger.getLogger(StringReverserController.class.getName());

    @Post("/")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String reverse(@Body String string) {
        int length = string.length();
        if (length > 30) {
            LOGGER.info("Received request to reverse " + string.substring(0, 10) + "..." + string.substring(length - 10, length));
        } else {
            LOGGER.info("Received request to reverse " + string);
        }

        String result = new StringBuilder(string).reverse().toString();

        if (length > 30) {
            LOGGER.info("Result: " + result.substring(0, 10) + "..." + string.substring(length - 10, length));
        } else {
            LOGGER.info("Result: " + result);
        }
        return result;
    }
}
