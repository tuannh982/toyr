package com.github.tuannh982.toyr.rt.material;

import com.github.tuannh982.toyr.commons.tuple.Tuple2;
import com.github.tuannh982.toyr.geometry.GeometricUtils;
import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.Ray;
import com.github.tuannh982.toyr.rt.hittable.HitRecord;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Lambertian implements Material {
    private final Vec3 a;

    @Override
    public Optional<Tuple2<Ray, Vec3>> scatter(Ray ray, HitRecord hitRecord) {
        return Optional.of(
                Tuple2.of(
                        Ray.builder()
                                .origin(hitRecord.getHitPoint())
                                .direction(GeometricUtils.unitVector(hitRecord.getNormal().elWiseAdd(GeometricUtils.randomUnitSphere())))
                                .build(),
                        a
                )
        );
    }
}
