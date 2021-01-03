package com.gamecodeschool.deadrase;

import android.graphics.PointF;

public class CrossoverCarSpec  extends ObjectSpec {
    private static final String tag = "Machine";
    private static final String bitmapName = "machine05";
    private static final float speedX = 0.25f;
    private static final float speedY = 100000000f;
    private static final PointF relativeScale = new PointF(8.5f, 8f);

    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "SportCarMovementComponent",
            "MachineHorizontalSpawnComponent"};

    CrossoverCarSpec() {
        super(tag, bitmapName, speedX, speedY, relativeScale, components);
    }
}
