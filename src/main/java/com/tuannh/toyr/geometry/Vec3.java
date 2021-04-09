package com.tuannh.toyr.geometry;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Vec3 {
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    // ----------------------------------------------------------------------------------
    public double squaredNorm() {
        return (x * x) + (y * y) + (z * z);
    }

    public double norm() {
        return Math.sqrt(squaredNorm());
    }

    public Vec3 normalized() {
        double norm = norm();
        return new Vec3(x / norm, y / norm, z / norm);
    }

    // ----------------------------------------------------------------------------------
    public Vec3 neg() {
        return new Vec3(-x, -y, -z);
    }

    // ----------------------------------------------------------------------------------
    public Vec3 add(Vec3 other) {
        return new Vec3(x + other.x, y + other.y, z + other.z);
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(x - other.x, y - other.y, z - other.z);
    }

    public Vec3 mul(double t) {
        return new Vec3(t * x, t * y, t * z);
    }

    public Vec3 div(double t) {
        return new Vec3(x / t, y / t, z / t);
    }

    // ----------------------------------------------------------------------------------
    public Vec3 lerp(Vec3 other, double t) {
        return this.mul(1 - t).add(other.mul(t));
    }

    // ----------------------------------------------------------------------------------
    public double dotProduct(Vec3 other) {
        return (x * other.x) + (y * other.y) + (z * other.z);
    }

    public Vec3 crossProduct(Vec3 other) {
        return new Vec3(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }

    // ----------------------------------------------------------------------------------
    public Vec3 elWiseAdd(Vec3 other) {
        return new Vec3(x + other.x, y + other.y, z + other.z);
    }

    public Vec3 elWiseSub(Vec3 other) {
        return new Vec3(x - other.x, y - other.y, z - other.z);
    }

    public Vec3 elWiseMul(Vec3 other) {
        return new Vec3(x * other.x, y * other.y, z * other.z);
    }

    public Vec3 elWiseDiv(Vec3 other) {
        return new Vec3(x / other.x, y / other.y, z / other.z);
    }

    public Vec3 elWisePow(double t) {
        return new Vec3(Math.pow(x, t), Math.pow(y, t), Math.pow(z, t));
    }

    // ----------------------------------------------------------------------------------
    public Vec3 clamp(double elMin, double elMax) {
        return new Vec3(
                (x < elMin) ? elMin : Math.min(x, elMax),
                (y < elMin) ? elMin : Math.min(y, elMax),
                (z < elMin) ? elMin : Math.min(z, elMax)
        );
    }
}
