package com.github.tuannh982.toyr.rt.hittable;

import com.github.tuannh982.toyr.rt.Ray;

import java.util.Optional;

public interface Hittable {
    Optional<HitRecord> hit(Ray ray, double tMin, double tMax);
}
