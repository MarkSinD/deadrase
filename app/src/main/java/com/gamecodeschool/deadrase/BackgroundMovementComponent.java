package com.gamecodeschool.deadrase;

import android.util.Log;


public class BackgroundMovementComponent  implements MovementComponent {
    @Override
    public boolean move(long fps, ObjectParameters mObjectParameters, ObjectParameters mPlayerParameters,
                        ObjectParameters mBackgroundParemeters) {
        int currentXClip = mObjectParameters.getXClip();

        Log.e("ОСТАНОВКА!!!", ObjectParameters.stopFlag + " ");




        if(mObjectParameters.getAccelerating())
            currentXClip += mObjectParameters.getSpeedXWithAccelIncr()/fps;
        else
            currentXClip += mObjectParameters.getSpeedXPl()/fps;


        mObjectParameters.setXClip(currentXClip);

        if(currentXClip >= mObjectParameters.getSize().x){
            mObjectParameters.setXClip(0);
            mObjectParameters.flipReversedFirst();
        }
        else if( currentXClip <= 0){
            mObjectParameters.setXClip((int)mObjectParameters.getSize().x);
            mObjectParameters.flipReversedFirst();
        }


        return true;
    }
}
