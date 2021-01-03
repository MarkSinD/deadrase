package com.gamecodeschool.deadrase;


import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver {
    private ObjectParameters mObjectParameters;



    // Пропустил

    PlayerInputComponent(GameEngine ger){
        ger.addObserver(this);

    }
    @Override
    public void setTransform(ObjectParameters op) {
        mObjectParameters = op;

    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<Controls> controls) {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        //Log.e("X ", "= " + x);
        //Log.e("Y ", "= " + y);
        //Log.e( "HUD.RIGHT" , " y = " + controls.get(HUD.RIGHT).TOP + ". x = " + controls.get(HUD.RIGHT).RIGHT);
        //Log.e( "HUD.RIGHT" , " y = " + controls.get(HUD.RIGHT).BOTTOM + ". x = " + controls.get(HUD.RIGHT).LEFT);

        //Log.e( "HUD.LEFT" , " y = " + controls.get(HUD.LEFT).TOP + ". x = " + controls.get(HUD.LEFT).RIGHT);
        //Log.e( "HUD.LEFT" , " y = " + controls.get(HUD.LEFT).BOTTOM + ". x = " + controls.get(HUD.LEFT).LEFT);


        //Log.e( "controls.get(HUD.RIGHT)" , " REZ = " + controls.get(HUD.RIGHT).contains(x, y));


        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_UP:
                if(controls.get(HUD.STOP).containsRECT(x,y)){
                    mObjectParameters.stopFlag = false;
                }

                if (controls.get(HUD.RIGHT).containsRECT(x, y)
                        || controls.get(HUD.LEFT).containsRECT(x, y)) {

                    // Player has released either up or down
                    mObjectParameters.stopHorizontal();
                }

                break;

            case MotionEvent.ACTION_POINTER_UP:


                if(controls.get(HUD.STOP).containsRECT(x,y)){
                    mObjectParameters.stopFlag = false;
                }

                if (controls.get(HUD.RIGHT).containsRECT(x, y)
                        || controls.get(HUD.LEFT).containsRECT(x, y)) {
                    // Player has released either up or down
                    mObjectParameters.accelerFlag = false;

                    if(controls.get(HUD.LEFT).containsRECT(x, y)){
                        mObjectParameters.headRight();
                    }
                    else if(controls.get(HUD.RIGHT).containsRECT(x, y)){
                        mObjectParameters.headLeft();
                    }

                }
                break;


            case MotionEvent.ACTION_DOWN:

                if(controls.get(HUD.STOP).containsRECT(x,y)){
                    mObjectParameters.stopFlag = true;
                }

                else if(controls.get(HUD.RIGHT).containsRECT(x, y)) {
                    // Player has pressed up
                    mObjectParameters.headRight();
                }
                else if (controls.get(HUD.LEFT).containsRECT(x, y)) {
                    // Player has pressed down
                    mObjectParameters.headLeft();
                }

                break;


            case MotionEvent.ACTION_POINTER_DOWN:

                if(controls.get(HUD.STOP).containsRECT(x,y)){
                    mObjectParameters.stopFlag = true;
                }


                else if(mObjectParameters.headingRight() && controls.get(HUD.LEFT).containsRECT(x, y)) {


                    mObjectParameters.accelerFlag = true;
                    mObjectParameters.stopHorizontal();
                }

                else if(mObjectParameters.headingLeft() && controls.get(HUD.RIGHT).containsRECT(x, y)) {
                    mObjectParameters.accelerFlag = true;
                    mObjectParameters.stopHorizontal();
                }

                else if (controls.get(HUD.RIGHT).containsRECT(x, y)) {

                    mObjectParameters.headRight();
                }
                else if (controls.get(HUD.LEFT).containsRECT(x, y)) {
                    // Player has pressed down
                    mObjectParameters.headLeft();
                }

                break;
        }

        Log.e("X ", "= " + x);
        Log.e("Y ", "= " + y);
        Log.e( "HUD.STOP" , " x = " + controls.get(HUD.STOP).LEFT + ". y = " + controls.get(HUD.STOP).TOP);
        Log.e( "HUD.STOP" , " x = " + controls.get(HUD.STOP).RIGHT + ". y = " + controls.get(HUD.STOP).BOTTOM);

        Log.e( "controls.get(HUD.STOP)" , " REZ = " + controls.get(HUD.STOP).containsRECT(x, y));
        Log.e("ОСТАНОВКА!!!", ObjectParameters.stopFlag + " ");
        Log.e("ОСТАНОВКА!!!", ObjectParameters.stopFlag + " ");





    }
}
