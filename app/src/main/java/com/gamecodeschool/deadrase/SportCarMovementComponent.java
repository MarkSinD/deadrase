package com.gamecodeschool.deadrase;

import android.graphics.PointF;


import java.util.Random;

public class SportCarMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, ObjectParameters mObjectParameters, ObjectParameters mPlayerParameters,
                        ObjectParameters mBackgroundParemeters) {



        float screenX = mObjectParameters.getScreenSize().x;
        float screenY = mObjectParameters.getScreenSize().y;


        PointF loc = mObjectParameters.getLocation();
        float machineSpeedX = mObjectParameters.getSpeedX();
        float machineSpeedY = mObjectParameters.getSpeedY();
        float backgroundSpeed = mBackgroundParemeters.getSpeedX();





        loc.x += (backgroundSpeed - machineSpeedX) / fps;


        if(Math.abs(loc.x) > Math.abs(1.5f * screenX)){

            mObjectParameters.resetStartParemeters();
            Random random = new Random();
            float distance = random.nextInt(100) + mObjectParameters.getScreenSize().x;
            float spawnHeight = (1+ (random.nextInt(6))) * screenY/8;


            if( mObjectParameters.getSpeedX() > mBackgroundParemeters.getSpeedX()){
                mObjectParameters.setLocation(distance, spawnHeight);
                mObjectParameters.headRight();

            }
            else {
                mObjectParameters.setLocation(-distance, spawnHeight);
                mObjectParameters.headRight();
            }

        }



        loc.y += machineSpeedY / fps;


        if( loc.y > 6.5f*screenY / 8 ){
            loc.y = 6.5f*screenY / 8;
            mObjectParameters.roadsideSpeedReduction();
        }
        else if(loc.y < screenY/20){
            loc.y = screenY/20;
            mObjectParameters.roadsideSpeedReduction();
        }

        mObjectParameters.updateCollider();

        return true;
    }
}
