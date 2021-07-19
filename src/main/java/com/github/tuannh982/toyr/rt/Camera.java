package com.github.tuannh982.toyr.rt;

import com.github.tuannh982.toyr.geometry.Vec3;

public class Camera {
    private final Vec3 origin;
    private final Vec3 verticalAxis;
    private final Vec3 horizontalAxis;
    private final Vec3 lowerLeftCorner;

    public Camera(Vec3 origin, Vec3 verticalAxis, Vec3 horizontalAxis, Vec3 centerOfView) {
        this.origin = origin;
        this.verticalAxis = verticalAxis;
        this.horizontalAxis = horizontalAxis;
        this.lowerLeftCorner = centerOfView.sub(verticalAxis.mul(0.5)).sub(horizontalAxis.mul(0.5));
    }

    public Ray getRay(double u, double v) {
        return new Ray(origin, lowerLeftCorner.add(verticalAxis.mul(u)).add(horizontalAxis.mul(v)));
    }
}
