package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

@Controller("/reverse")
public class StringReverserController {
    @Post("/")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String reverse(@Body String string) {
        return new StringBuilder(string).reverse().toString();
    }
}
