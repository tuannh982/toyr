package com.tuannh.toyr.rt.hittable.shape;

import com.tuannh.toyr.geometry.GeometricUtils;
import com.tuannh.toyr.geometry.Vec3;
import com.tuannh.toyr.rt.hittable.HitRecord;
import com.tuannh.toyr.rt.Ray;
import com.tuannh.toyr.rt.hittable.Hittable;
import com.tuannh.toyr.rt.material.Material;
import lombok.AllArgsConstructor;

import java.util.Optional;

@SuppressWarnings("java:S116")
@AllArgsConstructor
public class Triangle implements Hittable {
    private static final double PRECISION_EPSILON = 1e-5;

    private final Vec3 A;
    private final Vec3 B;
    private final Vec3 C;
    private final Material material;

    @Override
    public Optional<HitRecord> hit(Ray ray, double tMin, double tMax) {
        // https://cadxfem.org/inf/Fast%20MinimumStorage%20RayTriangle%20Intersection.pdf
        Vec3 edge1 = B.sub(A);
        Vec3 edge2 = C.sub(A);
        Vec3 h = ray.getDirection().crossProduct(edge2);
        double a = edge1.dotProduct(h);
        if (Math.abs(a) < PRECISION_EPSILON) {
            return Optional.empty();
        }
        double f = 1.0 / a;
        Vec3 s = ray.getOrigin().sub(A);
        double u = f * s.dotProduct(h);
        if (u < 0.0 || u > 1.0) {
            return Optional.empty();
        }
        Vec3 q = s.crossProduct(edge1);
        double v = f * ray.getDirection().dotProduct(q);
        if (v < 0.0 || u + v > 1.0) {
            return Optional.empty();
        }
        double t = f * edge2.dotProduct(q);
        if (t > tMin && t < tMax) {
            Vec3 hitPoint = ray.cast(t);
            HitRecord ret = new HitRecord(t, hitPoint, GeometricUtils.unitVector(edge1.crossProduct(edge2)), material);
            return Optional.of(ret);
        }
        return Optional.empty();
    }
}
