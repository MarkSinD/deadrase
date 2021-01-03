package com.gamecodeschool.deadrase;

import android.util.Log;


public class PlayerSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(ObjectParameters mObjectParameters, ObjectParameters mPlayerParameters) {
        mObjectParameters.setLocation(mObjectParameters.getScreenSize().x/2,
                mObjectParameters.getScreenSize().y/2);
        Log.e("PlayerSpawnComponent", "Начальное положение заданно");
    }
}
