package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

@Controller("/pi")
public class PiComputerController {
    private static final Logger LOGGER = Logger.getLogger(PiComputerController.class.getName());
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

    @Get("/")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String compute(@QueryValue int decimals) {
        LOGGER.info("Received request to compute pi to " + decimals + " decimals");

        long iterations = (long) Math.pow(10, decimals);
        BigDecimal result = BigDecimal.ZERO;
        for (long i = 0; i < iterations; i++) {
            BigDecimal augend = BigDecimal.ONE.setScale(1000, RoundingMode.UNNECESSARY)
                    .divide(TWO.multiply(new BigDecimal(i)).add(BigDecimal.ONE), RoundingMode.HALF_UP);
            if (i % 2 == 1) {
                augend = augend.negate();
            }
            result = result.add(augend);
        }

        result = result.multiply(FOUR);
        LOGGER.info("Result for pi for " + decimals + " decimals before rounding: " + result);
        return result.setScale(decimals, RoundingMode.HALF_UP).toString();
    }
}
