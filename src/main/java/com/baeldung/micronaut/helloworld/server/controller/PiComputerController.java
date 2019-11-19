package com.baeldung.micronaut.helloworld.server.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller("/pi")
public class PiComputerController {

    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

    @Get("/")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String compute(@QueryValue int decimals) {
        long iterations = (long) Math.pow(10, decimals);
        BigDecimal result = new BigDecimal(0);
        for (long i = 0; i < iterations; i++) {
            BigDecimal augend = BigDecimal.ONE.setScale(1000, RoundingMode.UNNECESSARY)
                    .divide(TWO.multiply(new BigDecimal(i)).add(BigDecimal.ONE), RoundingMode.UP);
            if (i % 2 == 1) {
                augend = augend.negate();
            }
            result = result.add(augend);
        }
        return result.multiply(FOUR).setScale(decimals, RoundingMode.HALF_UP).toString();
    }
}
