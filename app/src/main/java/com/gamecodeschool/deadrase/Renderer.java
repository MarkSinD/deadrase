package com.gamecodeschool.deadrase;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class Renderer {
    // Рендер переводится как визуализатор
    // Содержит в себе :
    // Класс отвечающий за методы рисования
    private Canvas mCanvas;

    // Ссылка на того, кто дает возможность порисовать
    private SurfaceHolder mSurfaceHolder;

    // Кисть
    private Paint mPaint;



    Renderer( SurfaceView sh){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
    }


    void draw(ArrayList<GameObject> objects, GameState gs, HUD hud){
        // Спрашиваем разрешения у хозяина экрана, доступен ли он
        if(mSurfaceHolder.getSurface().isValid()){


            // Тогда отпираем ворота перед mCanvas
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0));


            if (gs.getDrawing()) {
                // Draw all the game objects here
                for (GameObject object : objects) {
                    if(object.checkActive()) {
                        object.draw(mCanvas, mPaint);
                    }
                }
            }
            // Нарисовать кнопки
            hud.draw(mCanvas,mPaint,gs);


            // Забрать возможность рисовать
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
