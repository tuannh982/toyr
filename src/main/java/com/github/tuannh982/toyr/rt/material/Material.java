package com.github.tuannh982.toyr.rt.material;

import com.github.tuannh982.toyr.commons.tuple.Tuple2;
import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.Ray;
import com.github.tuannh982.toyr.rt.hittable.HitRecord;

import java.util.Optional;

public interface Material {
    // return scatter ray & attenuation
    Optional<Tuple2<Ray, Vec3>> scatter(Ray ray, HitRecord hitRecord);
}
