package com.gamecodeschool.deadrase;


import com.gamecodeschool.deadrase.ObjectParameters;

public interface MovementComponent {

    boolean move(long fps, ObjectParameters mObjectParameters,
                 ObjectParameters mPlayerParameters, ObjectParameters mBackgroundParemeters);
}
