package com.tuannh.toyr.rt.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TracerOption {
    public static final TracerOption DEFAULT = new TracerOption(12, 1e-7, 1e7, 64, 1.0, 2);

    private final int traceDepth;
    private final double tMin;
    private final double tMax;
    private final int sampleSize;
    private final double alpha;
    private final int threadPoolSize;
}
