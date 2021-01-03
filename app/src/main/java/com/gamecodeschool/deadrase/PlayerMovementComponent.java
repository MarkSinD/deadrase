package com.gamecodeschool.deadrase;

import android.graphics.PointF;

public class PlayerMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, ObjectParameters mObjectParameters,
                        ObjectParameters mPlayerParameters, ObjectParameters mBackgroundParemeters) {

        float screenY = mObjectParameters.getScreenSize().y;
        float screenX = mObjectParameters.getScreenSize().x;
        PointF location = mObjectParameters.getLocation();

        float speedY = mObjectParameters.getSpeedYWithAccelIncr();
        float height = mObjectParameters.getObjectHeight();


        location.y += speedY / fps;

        if(location.y > 6.5f*screenY/8) {
            location.y = 6.5f * screenY / 8;
            mBackgroundParemeters.roadsideSpeedReduction();
        }

        else if(location.y < 0) {
            location.y = 0;
            mBackgroundParemeters.roadsideSpeedReduction();
        }

        mObjectParameters.updateCollider();

        return true;
    }
}
