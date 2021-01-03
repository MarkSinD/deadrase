package com.gamecodeschool.deadrase;

import android.graphics.PointF;
import android.graphics.RectF;

public class ObjectParameters {


    private int mXClip;
    private boolean mReversedFirst = false;
    private RectF mCollider;
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadingRight = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private float mStartSpeedX;
    private float mSpeedX;
    private float mSpeedY;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;
    public static final float acceleration = 400f;
    public static boolean accelerFlag = false;
    public static boolean stopFlag = false;


    ObjectParameters(float speedX, float speedY,
                     float objectWidth,
                     float objectHeight,
                     PointF startingLocation,
                     PointF screenSize){
        mCollider = new RectF();
        mSpeedX = speedX;
        mStartSpeedX = speedX;
        mSpeedY = speedY;


        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
    }

    public void roadsideSpeedReduction(){
        mSpeedX = mSpeedX - 8*acceleration;
        mSpeedY = mSpeedY/2;
        if(mSpeedX < 0 ){
            mSpeedX = 0;
        }
    }


    public float getSpeedXWithAccelIncr(){

        if(stopFlag){
            mSpeedX = mSpeedX - 8*acceleration;
        }

        else {
            mSpeedX = mSpeedX + acceleration;
        }

        return mSpeedX;
    }

    public void resetStartParemeters(){
        mSpeedY = 0;
        mSpeedX = mStartSpeedX;

    }
    public float getSpeedYWithAccelIncr(){
        if(headingRight()){
            mSpeedY = mSpeedY - 2*acceleration;
        }
        else if(headingLeft())
            mSpeedY = mSpeedY + 2*acceleration;

        return mSpeedY;
    }

    public float getSpeedXPl(){

        if(stopFlag){
            mSpeedX = mSpeedX - 8*acceleration;
            if (mSpeedX < 0)
                mSpeedX = 0;

        }

        return mSpeedX;
    }

    float getSpeedX(){return mSpeedX;}
    float getSpeedY(){return mSpeedY;}


    void setNewSpeedX(float newSpeedX){
        mSpeedX = newSpeedX;
    }
    void setNewSpeedY(float newSpeedY) { mSpeedY = newSpeedY; }

    // Геттер ускорения
    public boolean getAccelerating(){
        return accelerFlag;
    }

    // Сеттер флага для ускорения
    public void setAccelerating(boolean flag){
        accelerFlag = flag;
    }
    // Метод необходим только в Background. Используется как флаг для обратной картинки
    boolean getReversedFirst(){ return mReversedFirst; }

    // Разводачикает картинку
    void flipReversedFirst(){ mReversedFirst =! mReversedFirst; }

    // Дать значение сдвига между картинками в абсолютной система координат андроида
    int getXClip(){ return mXClip; }

    // Установить значение сдвига
    void setXClip(int newXClip){ mXClip = newXClip; }

    // Дать размеры экрана
    PointF getScreenSize(){ return mScreenSize; }

    // Приказ-команда ВВЕРХ. меняет флаги направлений
    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
    }

    // Приказ-команда ВНИЗ. меняет флаги направлений
    void headLeft(){
        mHeadingLeft = true;
        mHeadingRight = false;
    }

    // Приказ-команда ВПРАВО. меняет флаги направлений
    void headDown(){
        mHeadingDown = true;
        mHeadingUp = false;
        mFacingRight = true;
    }

    // Приказ-команда ВЛЕВО. меняет флаги направлений
    void headUp(){
        mHeadingUp = true;
        mHeadingDown = false;
        mFacingRight = false;
    }

    // Является геттером напраления вверх
    boolean headingRight(){
        return mHeadingRight;
    }

    // Является геттером напраления вниз
    boolean headingLeft(){
        return mHeadingLeft;
    }

    // Является геттером напраления вправо
    boolean headingDown(){
        return mHeadingDown;
    }

    // Является геттером напраления влево
    boolean headingUp(){
        return mHeadingUp;
    }

    // Коллайдер всегда меньше реальных обьектов картинки
    void updateCollider(){
        mCollider.top = (float) (mLocation.y + (mObjectHeight/2));
        mCollider.left = (float) (mLocation.x + (mObjectWidth/3.5));
        mCollider.bottom = (float) ((mCollider.top + mObjectHeight)
                        - mObjectHeight/2);
        mCollider.right = (float) ((mCollider.left + mObjectWidth)-
                                mObjectWidth/3.5);
    }


    // Геттер высоты обьекта
    float getObjectHeight(){
        return mObjectHeight;
    }


    // СТОП СИГНАЛ. Метод, меняющий флаги направлений
    void stopHorizontal(){
        mHeadingLeft = false;
        mHeadingRight = false;
    }



    // Установка локации обьекта
    void setLocation( float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }

    // ССЫЛКА - передаюзий метод
    PointF getLocation(){
        return mLocation;
    }

    // Передает Point'ом размеры обьекта
    PointF getSize(){
        return new PointF((int)mObjectWidth, (int)mObjectHeight);
    }

    // Флип для изменения направления
    void flip(){
        mFacingRight = !mFacingRight;
    }

    // Геттер направления ВПРАВО
    boolean getFacingRight(){
        return mFacingRight;
    }


    // ССЫЛКА - передающий
    RectF getCollider(){ return mCollider; }


    // ССЫЛКА - передающий
    PointF getFiringLocation(float laserLengh ){
        PointF mFiringLocation = new PointF();

        if(mFacingRight){
            mFiringLocation.x = mLocation.x + ( mObjectWidth / 8f);
        }
        else{
            mFiringLocation.x = mLocation.x + (mObjectWidth / 8f) - laserLengh;
        }
        mFiringLocation.y = mLocation.y + (mObjectHeight / 1.28f);

        return mFiringLocation;
    }


}
