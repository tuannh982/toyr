package com.github.tuannh982.toyr.rt;

import com.github.tuannh982.toyr.commons.random.RandomUtils;
import com.github.tuannh982.toyr.commons.tuple.Tuple2;
import com.github.tuannh982.toyr.geometry.GeometricUtils;
import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.hittable.HitRecord;
import com.github.tuannh982.toyr.rt.option.SceneOption;
import com.github.tuannh982.toyr.rt.option.TracerOption;

import java.util.Optional;
import java.util.concurrent.*;

public class Scene {
    private final SceneOption option;
    private final TracerOption tracerOption;

    public Scene(SceneOption option) {
        this(option, TracerOption.DEFAULT);
    }

    public Scene(SceneOption option, TracerOption tracerOption) {
        this.option = option;
        this.tracerOption = tracerOption;
    }

    private Vec3 color(Ray ray, int currentTraceDepth) {
        Optional<HitRecord> optionalHitRecord = option.getWorld().hit(ray, tracerOption.getTMin(), tracerOption.getTMax());
        if (optionalHitRecord.isPresent()) {
            HitRecord hitRecord = optionalHitRecord.get();
            if (currentTraceDepth < tracerOption.getTraceDepth()) {
                Optional<Tuple2<Ray, Vec3>> optionalHrs = hitRecord.getMaterial().scatter(ray, hitRecord);
                if (optionalHrs.isPresent()) {
                    Ray scattered = optionalHrs.get().getA0();
                    Vec3 attenuation = optionalHrs.get().getA1();
                    return attenuation.elWiseMul(color(scattered, currentTraceDepth + 1));
                } else {
                    return new Vec3(0, 0, 0);
                }
            } else {
                return new Vec3(0, 0, 0);
            }
        } else {
            Vec3 uv = GeometricUtils.unitVector(ray.getDirection());
            double t = 0.5 * (uv.getX() + 1);
            return new Vec3(1, 1, 1).lerp(new Vec3(0.5, 0.7, 1), t);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Color[][] draw() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool(tracerOption.getThreadPoolSize());
        int sceneHeight = option.getHeight();
        int sceneWidth = option.getWidth();
        Camera camera = option.getCamera();
        Vec3[][] ret = new Vec3[sceneHeight][sceneWidth];
        for (int i = 0; i < sceneHeight; i++) {
            for (int j = 0; j < sceneWidth; j++) {
                ret[i][j] = new Vec3(0, 0, 0);
                int finalI = i;
                int finalJ = j;
                executor.submit(() -> {
                    for (int k = 0; k < tracerOption.getSampleSize(); k++) {
                        double u = (finalI + RandomUtils.normal()) / sceneHeight;
                        double v = (finalJ + RandomUtils.normal()) / sceneWidth;
                        ret[finalI][finalJ] = ret[finalI][finalJ].add(color(camera.getRay(u, v), 0));
                    }
                    ret[finalI][finalJ] = ret[finalI][finalJ].div(tracerOption.getSampleSize());
                });
            }
        }
        executor.shutdown();
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        Color[][] image = new Color[sceneHeight][sceneWidth];
        for (int i = sceneHeight - 1; i > -1; i--) {
            for (int j = 0; j < sceneWidth; j++) {
                ret[i][j] = ret[i][j].elWisePow(tracerOption.getAlpha());
                ret[i][j] = ret[i][j].clamp(0, 1).mul(255.99);
                image[sceneHeight - 1 - i][j] = new Color((int)ret[i][j].getX(), (int)ret[i][j].getY(), (int)ret[i][j].getZ());
            }
        }
        return image;
    }
}
