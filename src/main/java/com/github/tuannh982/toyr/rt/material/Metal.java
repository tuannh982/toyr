package com.github.tuannh982.toyr.rt.material;

import com.github.tuannh982.toyr.commons.tuple.Tuple2;
import com.github.tuannh982.toyr.geometry.GeometricUtils;
import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.Ray;
import com.github.tuannh982.toyr.rt.hittable.HitRecord;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Metal implements Material {
    private final Vec3 a;
    private final double fuzz;

    @Override
    public Optional<Tuple2<Ray, Vec3>> scatter(Ray ray, HitRecord hitRecord) {
        Vec3 reflected = GeometricUtils.reflect(GeometricUtils.unitVector(ray.getDirection()), hitRecord.getNormal());
        Ray scatter = Ray.builder()
                .origin(hitRecord.getHitPoint())
                .direction(GeometricUtils.unitVector(reflected.add(GeometricUtils.randomUnitSphere().mul(fuzz))))
                .build();
        double d = scatter.getDirection().dotProduct(hitRecord.getNormal());
        if (d > 0) {
            return Optional.of(
                    Tuple2.of(
                            scatter,
                            a
                    )
            );
        }
        return Optional.empty();
    }
}
