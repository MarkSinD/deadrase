package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Controls> controls;
    static int LEFT = 0;
    static int RIGHT = 1;
    static int STOP = 2;
    static int PAUSE = 3;

    Context mContext;
    HUD(Point screenSize, Context context){
        mScreenHeight = screenSize.y;
        mScreenWidth = screenSize.x;
        mTextFormatting = screenSize.x / 50;

        mContext = context;
       prepareControls();
    }

    private void prepareControls(){



        Controls left = new Controls(new Point(mScreenWidth, mScreenHeight), LEFT, mContext);
        Controls right = new Controls(new Point(mScreenWidth, mScreenHeight), RIGHT, mContext);
        Controls stop = new Controls(new Point(mScreenWidth, mScreenHeight), STOP, mContext);
        Controls pause = new Controls(new Point(mScreenWidth, mScreenHeight), PAUSE, mContext);

        controls = new ArrayList<>();
        controls.add(LEFT, left);
        controls.add(RIGHT, right);
        controls.add(STOP, stop);
        controls.add(PAUSE, pause);

    }

    void draw(Canvas c, Paint p, GameState gs){
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);
        c.drawText("HI : " + gs.getHighScore(), mTextFormatting, mTextFormatting, p );
        c.drawText("Score : " + gs.getScore(), mTextFormatting, mTextFormatting, p);
        c.drawText("Lives : " + gs.getNumCars(), mTextFormatting, mTextFormatting, p);

        if(gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PRESS PLAY", mScreenWidth/3, mScreenHeight/2, p);
        }
        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PAUSED", mScreenWidth/3, mScreenHeight/2, p);
        }
        drawControls(c,p);

    }

    private void drawControls(Canvas c, Paint p){


        for( Controls r : controls ){
            if(r == controls.get(LEFT)){
                p.setColor(Color.argb(0, 55, 55, 55));
                c.drawRect(r.LEFT, r.TOP, r.RIGHT, r.BOTTOM, p);
            }
            else if(r == controls.get(RIGHT)){
                p.setColor(Color.argb(0, 55, 55, 55));
                c.drawRect(r.LEFT, r.TOP, r.RIGHT, r.BOTTOM, p);
            }
            else if(r == controls.get(STOP)){
                p.setColor(Color.argb(0, 255, 228, 196));
                c.drawRect(r.LEFT, r.TOP, r.RIGHT, r.BOTTOM, p);


                p.setAlpha(200);
                c.drawRect( r.LEFT, r.TOP, r.RIGHT,r.BOTTOM, p);
                c.drawBitmap(r.getBitmapButton(),
                        r.getTopXCoordinateButton(), r.getTopYCoordinateButton(), p);
            }
            else if(r == controls.get(PAUSE)){
                p.setAlpha(120);
                c.drawCircle(r.mScreenWidth, r.mScreenWidth, r.radius, p);
                c.drawBitmap(r.getBitmapButton(),
                        r.getTopXCoordinateButton(), r.getTopYCoordinateButton(), p);
            }


        }

        p.setColor(Color.argb(255,32,12,120));
    }

    ArrayList<Controls> getControls(){
        return controls;
    }


}
