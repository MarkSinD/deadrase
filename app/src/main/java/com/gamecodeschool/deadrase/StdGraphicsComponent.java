package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

import com.gamecodeschool.deadrase.ObjectParameters;
import com.gamecodeschool.deadrase.ObjectSpec;

public class StdGraphicsComponent implements GraphicsComponent {

    private Bitmap mBitmap;
    private Bitmap mBitmapRevesed;

    // Метод выподняется с инициализацией двух картинок: туда и обратно
    @Override
    public void intitialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().getIdentifier(s.getBitmapName(), "drawable",
                c.getPackageName());
        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)objectSize.x, (int)objectSize.y, false);

        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        mBitmapRevesed = Bitmap.createBitmap
                (mBitmap, 0,0,
                        mBitmap.getWidth(), mBitmap.getHeight(),
                        matrix, true);

    }

    // Помещает картинку на экран в нужных координатах
    @Override
    public void draw(Canvas canvas, Paint paint, ObjectParameters OP) {
        if(OP.getFacingRight()){
            canvas.drawBitmap(mBitmap, OP.getLocation().x, OP.getLocation().y, paint);
        }
        else{
            canvas.drawBitmap(mBitmap, OP.getLocation().x, OP.getLocation().y, paint);
        }

    }
}
