package com.tuannh.toyr.rt.material;

import com.tuannh.toyr.commons.tuple.Tuple2;
import com.tuannh.toyr.geometry.Vec3;
import com.tuannh.toyr.rt.hittable.HitRecord;
import com.tuannh.toyr.rt.Ray;

import java.util.Optional;

public interface Material {
    // return scatter ray & attenuation
    Optional<Tuple2<Ray, Vec3>> scatter(Ray ray, HitRecord hitRecord);
}
