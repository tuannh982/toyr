package com.tuannh.toyr.rt.hittable;

import com.tuannh.toyr.rt.Ray;

import java.util.Optional;

public interface Hittable {
    Optional<HitRecord> hit(Ray ray, double tMin, double tMax);
}
