package com.gamecodeschool.deadrase;

import android.graphics.PointF;

import com.gamecodeschool.deadrase.ObjectParameters;

import java.util.Random;

public class MachineHorizontalSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(ObjectParameters mObjectParameters, ObjectParameters mPlayerParameters) {
        PointF ss = mObjectParameters.getScreenSize();
        Random random = new Random();
        boolean left = random.nextBoolean();

        float distance = random.nextInt(100) + mObjectParameters.getScreenSize().x;
        float spawnHeight = (1+ (random.nextInt(6))) * ss.y/8;

        if( mObjectParameters.getSpeedX() > mPlayerParameters.getSpeedX()){
            mObjectParameters.setLocation(distance, spawnHeight);
            mObjectParameters.headRight();
        }
         else {
            mObjectParameters.setLocation(-distance, spawnHeight);
            mObjectParameters.headRight();
        }
    }
}
