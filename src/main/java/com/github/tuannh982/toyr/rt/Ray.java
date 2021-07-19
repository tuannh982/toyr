package com.github.tuannh982.toyr.rt;

import com.github.tuannh982.toyr.geometry.Vec3;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ray {
    private Vec3 origin;
    private Vec3 direction;

    public Vec3 cast(double t) {
        return origin.add(direction.mul(t));
    }
}
