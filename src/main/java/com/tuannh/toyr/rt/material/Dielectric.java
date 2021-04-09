package com.tuannh.toyr.rt.material;

import com.tuannh.toyr.commons.tuple.Tuple2;
import com.tuannh.toyr.geometry.GeometricUtils;
import com.tuannh.toyr.geometry.Vec3;
import com.tuannh.toyr.rt.hittable.HitRecord;
import com.tuannh.toyr.rt.Ray;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Dielectric implements Material{
    private final Vec3 a;
    private final double ri;

    @Override
    public Optional<Tuple2<Ray, Vec3>> scatter(Ray ray, HitRecord hitRecord) {
        Vec3 uv = GeometricUtils.unitVector(ray.getDirection());
        Vec3 reflected = GeometricUtils.reflect(uv, hitRecord.getNormal());
        Vec3 normal;
        double niOverNt;
        Vec3 refracted;
        if (ray.getDirection().dotProduct(hitRecord.getNormal()) > 0) {
            normal = hitRecord.getNormal().neg();
            niOverNt = ri;
        } else {
            normal = hitRecord.getNormal();
            niOverNt = 1.0 / ri;
        }
        Optional<Vec3> optional = GeometricUtils.refract(uv, normal, niOverNt);
        if (optional.isPresent()) {
            refracted = optional.get();
            Ray scattered = new Ray(hitRecord.getHitPoint(), refracted);
            return Optional.of(Tuple2.of(scattered, a));
        } else {
            Ray scattered = new Ray(hitRecord.getHitPoint(), reflected);
            return Optional.of(Tuple2.of(scattered, a));
        }
    }
}
