package com.gamecodeschool.deadrase;

import android.graphics.PointF;

public class PlayerSpec extends ObjectSpec {

    private static final String tag = "Player";
    private static final String bitmapName = "player";
    private static final float speedX = 1f;
    private static final float speedY = 100000000f;
    private static final PointF relativeScale = new PointF(7.5f,5f);


    private static final String[] components = new String[]{
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"
    };

    PlayerSpec() {
        super(tag, bitmapName, speedX, speedY, relativeScale, components);
    }
}
