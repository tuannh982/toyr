package com.tuannh.toyr.rt.material;

import com.tuannh.toyr.commons.tuple.Tuple2;
import com.tuannh.toyr.geometry.GeometricUtils;
import com.tuannh.toyr.geometry.Vec3;
import com.tuannh.toyr.rt.hittable.HitRecord;
import com.tuannh.toyr.rt.Ray;
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
