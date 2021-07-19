package com.github.tuannh982.toyr;

import com.github.tuannh982.toyr.geometry.Vec3;
import com.github.tuannh982.toyr.rt.Camera;
import com.github.tuannh982.toyr.rt.Color;
import com.github.tuannh982.toyr.rt.Scene;
import com.github.tuannh982.toyr.rt.hittable.Hittable;
import com.github.tuannh982.toyr.rt.hittable.HittableList;
import com.github.tuannh982.toyr.rt.hittable.shape.Sphere;
import com.github.tuannh982.toyr.rt.hittable.shape.Triangle;
import com.github.tuannh982.toyr.rt.material.Dielectric;
import com.github.tuannh982.toyr.rt.material.Lambertian;
import com.github.tuannh982.toyr.rt.material.Metal;
import com.github.tuannh982.toyr.rt.option.SceneOption;
import com.github.tuannh982.toyr.rt.option.TracerOption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Camera camera = new Camera(
                new Vec3(0,0,0),
                new Vec3(0,3,0),
                new Vec3(4, 0, 0),
                new Vec3(0, 0, 1)
        );
        Hittable world = new HittableList(Arrays.asList(
                // earth
                new Sphere(new Vec3(0, -100.5, 1), 100, new Lambertian(new Vec3((double)42 / 255, (double)157 / 255, (double)143 / 255))),
                // sphere 1
                new Sphere(new Vec3(0.3, 0, 2), 0.5, new Lambertian(new Vec3(0.4, 0.5, 0.6))),
                // glass ball
                new Sphere(new Vec3(0, 0, 1.5), 0.5, new Dielectric(new Vec3(0.95, 0.95, 0.95), 1.53)),
                new Sphere(new Vec3(0, 0, 1.5), -0.47, new Dielectric(new Vec3(0.95, 0.95, 0.95), 1.53)),
                // metal pane
                new Triangle(
                        new Vec3(1, -0.7, 3.5),
                        new Vec3(0.8, 1.3, 2),
                        new Vec3(0.8, -0.7, 0.5),
                        new Metal(new Vec3(0.98, 0.98, 0.98), 0.006)
                ),
                new Triangle(
                        new Vec3(0.8, 1.3, 2),
                        new Vec3(1, -0.7, 3.5).add(
                                (new Vec3(0.8, 1.3, 2).lerp(new Vec3(0.8, -0.7, 0.5), 0.5))
                                        .sub(new Vec3(1, -0.7, 3.5)).mul(1.3)
                        ),
                        new Vec3(0.8, -0.7, 0.5),
                        new Metal(new Vec3(0.98, 0.98, 0.98), 0.006)
                ),
                new Triangle(
                        new Vec3(0.8 - 1.5, 1.3, 2),
                        new Vec3(1 - 1.5, -0.7, 3.5),
                        new Vec3(0.8 - 1.5, -0.7, 0.5),
                        new Metal(new Vec3(0.98, 0.98, 0.98), 0.006)
                ),
                new Triangle(
                        new Vec3(1 - 1.5, -0.7, 3.5).add(
                                (new Vec3(0.8 - 1.5, 1.3, 2).lerp(new Vec3(0.8 - 1.5, -0.7, 0.5), 0.5))
                                        .sub(new Vec3(1 - 1.5, -0.7, 3.5)).mul(1.3)
                        ),
                        new Vec3(0.8 - 1.5, 1.3, 2),
                        new Vec3(0.8 - 1.5, -0.7, 0.5),
                        new Metal(new Vec3(0.98, 0.98, 0.98), 0.006)
                ),
                // diamond
                new Triangle(
                        new Vec3(0.5, -0.5, 1.2),
                        new Vec3(0.3, -0.3, 1.2),
                        new Vec3(0.5, -0.3, 1),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.5, 1.2),
                        new Vec3(0.5, -0.3, 1),
                        new Vec3(0.7, -0.3, 1.2),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.5, 1.2),
                        new Vec3(0.7, -0.3, 1.2),
                        new Vec3(0.5, -0.3, 1.4),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.5, 1.2),
                        new Vec3(0.5, -0.3, 1.4),
                        new Vec3(0.3, -0.3, 1.2),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.1, 1.2),
                        new Vec3(0.3, -0.3, 1.2),
                        new Vec3(0.5, -0.3, 1),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.1, 1.2),
                        new Vec3(0.5, -0.3, 1),
                        new Vec3(0.7, -0.3, 1.2),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.1, 1.2),
                        new Vec3(0.7, -0.3, 1.2),
                        new Vec3(0.5, -0.3, 1.4),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                ),
                new Triangle(
                        new Vec3(0.5, -0.1, 1.2),
                        new Vec3(0.5, -0.3, 1.4),
                        new Vec3(0.3, -0.3, 1.2),
                        new Dielectric(new Vec3(0.95, 0.95, 0.95), 2.4)
                )
        ));
        int width = 1920;
        int height = 1080;
        SceneOption sceneOption = new SceneOption(width, height, camera, world);
        TracerOption tracerOption = new TracerOption(16, 1e-9, 1e9, 64, 1.0, 2);
        Scene scene = new Scene(sceneOption, tracerOption);
        long start = System.nanoTime();
        Color[][] retImg = scene.draw();
        long stop = System.nanoTime();
        System.out.println("elapsed time = " + new BigDecimal(stop - start).divide(new BigDecimal(1_000_000), 5, RoundingMode.CEILING));
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = (retImg[i][j].getR() << 16) | (retImg[i][j].getG() << 8) | retImg[i][j].getB();
                image.setRGB(j, i, rgb);
            }
        }
        File outputFile = new File("./test.png");
        ImageIO.write(image, "png", outputFile);
        System.out.println("DONE");
    }
}
