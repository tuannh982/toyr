package com.tuannh.toyr.rt.material;

import com.tuannh.toyr.commons.tuple.Tuple2;
import com.tuannh.toyr.geometry.GeometricUtils;
import com.tuannh.toyr.geometry.Vec3;
import com.tuannh.toyr.rt.hittable.HitRecord;
import com.tuannh.toyr.rt.Ray;
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
