package com.github.tuannh982.toyr.rt.option;

import com.github.tuannh982.toyr.rt.Camera;
import com.github.tuannh982.toyr.rt.hittable.Hittable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SceneOption {
    private final int width;
    private final int height;
    private final Camera camera;
    private final Hittable world;
}
