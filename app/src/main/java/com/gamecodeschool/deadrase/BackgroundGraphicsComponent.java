package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;


public class BackgroundGraphicsComponent implements GraphicsComponent {

    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;

    @Override
    public void intitialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().
                getIdentifier(s.getBitmapName() , "drawable", c.getPackageName());

        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        mBitmap = Bitmap.createScaledBitmap(mBitmap,
                (int)objectSize.x, (int)objectSize.y, false);
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        mBitmapReversed = Bitmap.createBitmap(mBitmap, 0,0,
                mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, ObjectParameters OP) {

        int xClip = OP.getXClip();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int startY = 0;
        int endY = (int)OP.getScreenSize().y+20;

        Rect fromRect1 = new Rect(0,0, width - xClip, height);
        Rect toRect1 = new Rect(xClip, startY, width, endY);

        Rect fromRect2 = new Rect(width - xClip, 0, width, height);
        Rect toRect2 = new Rect(0, startY, xClip, endY);


        if(!OP.getReversedFirst()){
            canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(mBitmapReversed, fromRect2, toRect2, paint);
        }

        else{
            canvas.drawBitmap(mBitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(mBitmapReversed, fromRect1, toRect1, paint);
        }

    }
}
