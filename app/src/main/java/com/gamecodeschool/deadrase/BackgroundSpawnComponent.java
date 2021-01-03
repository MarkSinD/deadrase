package com.gamecodeschool.deadrase;


public class BackgroundSpawnComponent implements SpawnComponent {

    @Override
    public void spawn(ObjectParameters mObjectParameters, ObjectParameters mPlayerParameters) {
        mObjectParameters.setLocation(0f, 0f);
    }
}
