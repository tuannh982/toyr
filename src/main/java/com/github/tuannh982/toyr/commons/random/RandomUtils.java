package com.github.tuannh982.toyr.commons.random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
    private static final Random RAND = new Random(System.currentTimeMillis());

    // capped normal random
    public static double normal() {
        double x = Math.abs(RAND.nextGaussian() / 2);
        if (x > 1) return 1;
        return x;
    }

    public static double uniform() {
        return RAND.nextDouble();
    }
}
