package com.tuannh.toyr.rt.hittable;

import com.tuannh.toyr.rt.Ray;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class HittableList implements Hittable {
    private final List<Hittable> list;

    @Override
    public Optional<HitRecord> hit(Ray ray, double tMin, double tMax) {
        double closestT = tMax;
        HitRecord ret = null;
        for (Hittable hittable : list) {
            Optional<HitRecord> optional = hittable.hit(ray, tMin, tMax);
            if (optional.isPresent()) {
                HitRecord hitRecord = optional.get();
                if (hitRecord.getT() < closestT) {
                    closestT = hitRecord.getT();
                    ret = hitRecord;
                }
            }
        }
        if (ret == null) {
            return Optional.empty();
        } else {
            return Optional.of(ret);
        }
    }
}
