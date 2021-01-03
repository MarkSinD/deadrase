package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;

public class Controls {

    // Xн
    public int LEFT;
    // Yн
    public int TOP;

    // Xк
    public int RIGHT;
    // Yк
    public int BOTTOM;
    public int CENTER_X;
    public int CENTER_Y;


    int mScreenWidth;
    int mScreenHeight;
    int mButtonPadding;
    int ID;
    int radius;
    Rect mRect;

    private Bitmap mButton;

    public Controls(Point ScreenSize, int index, Context context){
        mScreenWidth = ScreenSize.x;
        mScreenHeight = ScreenSize.y;
        mButtonPadding = ScreenSize.x / 14;
        ID = index;
        radius = mScreenWidth / 20;

        switch(index){

                // LEFT
            case 0:
                LEFT = 0;
                TOP = mScreenHeight/2;
                RIGHT = mScreenWidth;
                BOTTOM = mScreenHeight;
                mRect = new Rect(LEFT, TOP, RIGHT, BOTTOM);

                break;

                // RIGHT
            case 1:
                LEFT = 0;
                TOP = 0;
                RIGHT = mScreenWidth;
                BOTTOM = mScreenHeight/2;

                mRect = new Rect(LEFT, TOP, RIGHT,BOTTOM);

                break;

                // STOP
            case 2:


                LEFT = mScreenWidth - mScreenWidth/7;
                TOP = 0;
                RIGHT = mScreenWidth;
                BOTTOM = mScreenHeight/5;

                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.p);
                mButton = Bitmap.createScaledBitmap(mButton, mScreenWidth/4, mScreenHeight/4, false);

                CENTER_X = mScreenWidth - mScreenWidth/4;
                CENTER_Y = 0;
                mRect = new Rect(LEFT, TOP, RIGHT,BOTTOM);


                break;

            case 3:
                mButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.play);
                mButton = Bitmap.createScaledBitmap(mButton, 2*radius, 2*radius, false);
                CENTER_X = 0;
                CENTER_Y = 0;
                break;


        }


    }

    public Bitmap getBitmapButton(){
        return mButton;
    }

    public int getTopXCoordinateButton(){
        return CENTER_X;
    }

    public int getTopYCoordinateButton(){
        return CENTER_Y;
    }

    public boolean containsRECT(int x, int y){
        return (mRect.left <= x && mRect.right >= x && mRect.top <= y  && mRect.bottom >= y );
    }
    public boolean containsCIR(int x, int y){
            return ((x - (CENTER_X + radius)) * (x - (CENTER_X + radius)) + (y - (CENTER_Y + radius)) * (y - (CENTER_Y + radius)) <= radius * radius);

    }
}
