package com.baeldung.micronaut.helloworld.server.controller;

import com.baeldung.micronaut.helloworld.server.model.Weather;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.exceptions.InternalServerException;

import java.security.SecureRandom;
import java.util.logging.Logger;

import static com.baeldung.micronaut.helloworld.server.model.Weather.SUNNY;
import static com.baeldung.micronaut.helloworld.server.model.Weather.UNKNOWN;

@Controller("/weather")
public class WeatherController {
    private static final Logger LOGGER = Logger.getLogger(WeatherController.class.getName());
    private static final SecureRandom r = new SecureRandom();
    private Weather currentWeather = SUNNY;

    @Get("/")
    public String getWeather() {
        if (r.nextInt() % 100 == 0) {
            // Simulate error
            sleep(1);
            throw new InternalServerException("I'm not feeling it!");
        }
        if (r.nextInt() % 10 == 0) {
            // Simulate slow response
            sleep(10);
            return currentWeather.name();
        }
        if (r.nextInt() % 100 == 0) {
            // Simulate unknown (but technically valid) outcome
            sleep(1);
            return UNKNOWN.name();
        }

        // Success case where weather is returned.
        return currentWeather.name();
    }

    @Post("/{weather}")
    @Consumes({MediaType.ALL})
    public void setWeather(@PathVariable String weather) {
        LOGGER.info("The new weather will now be " + weather);
        currentWeather = Weather.valueOf(weather);
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}
