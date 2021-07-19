package com.github.tuannh982.toyr.rt.hittable;

import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.material.Material;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HitRecord {
    private double t;
    private Vec3 hitPoint;
    private Vec3 normal;
    private Material material;
}
