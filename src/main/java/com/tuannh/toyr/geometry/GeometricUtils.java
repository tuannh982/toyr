package com.tuannh.toyr.geometry;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometricUtils {
    private static final Random RAND = new Random(System.currentTimeMillis());

    public static Vec3 reflect(Vec3 v, Vec3 normal) {
        double d = v.dotProduct(normal);
        return v.sub(normal.mul(2 * d));
    }

    public static Optional<Vec3> refract(Vec3 v, Vec3 normal, double niOverNt) {
        double dt = v.dotProduct(normal);
        double discriminant = 1.0 - (niOverNt * niOverNt) * (1 - (dt * dt));
        if (discriminant <= 0) {
            return Optional.empty();
        }
        // ni_over_nt * (v - normal * dt) - normal * sqrt(discriminant)
        return Optional.of(v.sub(normal.mul(dt)).mul(niOverNt).sub(normal.mul(Math.sqrt(discriminant))));
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.div(v.norm());
    }

    public static Vec3 randomUnitSphere() {
        double x;
        double y;
        double z;
        do {
            x = RAND.nextDouble();
            y = RAND.nextDouble();
            z = RAND.nextDouble();
        } while ((x * x + y * y + z * z) >= 1);
        return new Vec3(x, y ,z);
    }
}
