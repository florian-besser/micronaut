package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.baeldung.micronaut.helloworld.server.model.Weather.RAIN;
import static com.baeldung.micronaut.helloworld.server.model.Weather.UNKNOWN;

@Controller("/umbrella")
public class UmbrellaController {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Logger LOGGER = Logger.getLogger(UmbrellaController.class.getName());

    @Get("/")
    public boolean getUmbrella() {
        LOGGER.info("Received request to get umbrella");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://weatherapp:9080/weather"))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_CLIENT
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                LOGGER.log(Level.WARNING, "Received non-OK status code " + response.statusCode());
                return true;
            }
            String currentWeather = response.body();
            return RAIN.name().equals(currentWeather) ||
                    UNKNOWN.name().equals(currentWeather);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Got exception", e);
            return true;
        }
    }
}
