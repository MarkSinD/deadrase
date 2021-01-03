package com.gamecodeschool.deadrase;

import android.view.MotionEvent;

import java.util.ArrayList;

public interface InputObserver {
    void handleInput(MotionEvent event, GameState gs, ArrayList<Controls> controls);

}
