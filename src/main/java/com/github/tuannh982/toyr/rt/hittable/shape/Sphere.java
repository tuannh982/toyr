package com.github.tuannh982.toyr.rt.hittable.shape;

import com.github.tuannh982.toyr.geometry.GeometricUtils;
import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.Ray;
import com.github.tuannh982.toyr.rt.hittable.HitRecord;
import com.github.tuannh982.toyr.rt.hittable.Hittable;
import com.github.tuannh982.toyr.rt.material.Material;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Sphere implements Hittable {
    private final Vec3 center;
    private final double radius;
    private final Material material;

    @Override
    public Optional<HitRecord> hit(Ray ray, double tMin, double tMax) {
        Vec3 oc = ray.getOrigin().sub(center);
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = oc.dotProduct(ray.getDirection());
        double c = oc.dotProduct(oc) - radius * radius;
        double discriminant = b * b - a * c;
        if (discriminant > 0) {
            double dt = Math.sqrt(discriminant);
            double t = (-b - dt) / a;
            if (t > tMin && t < tMax) {
                Vec3 hitPoint = ray.cast(t);
                HitRecord ret = new HitRecord(t, hitPoint, GeometricUtils.unitVector(hitPoint.sub(center).div(radius)), material);
                return Optional.of(ret);
            }
            t = (-b + dt) / a;
            if (t > tMin && t < tMax) {
                Vec3 hitPoint = ray.cast(t);
                HitRecord ret = new HitRecord(t, hitPoint, GeometricUtils.unitVector(hitPoint.sub(center).div(radius)), material);
                return Optional.of(ret);
            }
        }
        return Optional.empty();
    }
}
