package com.gamecodeschool.deadrase;

import android.graphics.PointF;

abstract class ObjectSpec {

    // Таг используется при обраюотке пересечений обьектов
    private String mTag;

    // Имя Картинки
    private String mBitmapName;


    // Размеры картинки
    private PointF mSizeScale;

    // Компоненты
    private String[] mComponents;

    // Скорость по оси X
    private float mSpeedX;

    // Скорость по оси Y
    private float mSpeedY;

    ObjectSpec(String tag, String bitmapName, float speedX, float speedY, PointF relativeScale, String[] components){
        mTag = tag;
        mBitmapName = bitmapName;
        mSpeedX = speedX;
        mSpeedY = speedY;
        mSizeScale = relativeScale;
        mComponents = components;
    }

    // ССЫЛКА - метод. Возвращает таг
    String getTag(){ return mTag; }

    // ССЫЛКА - метод. Возвращает имя изобращения
    String getBitmapName(){ return mBitmapName; }

    // ССЫЛКА - метод. Возвращает скорость по X
    float getSpeedX(){return mSpeedX;}

    // ССЫЛКА - метод. Вызвращает скорость Y
    float getSpeedY(){return mSpeedY;}

    // ССЫЛКА - метод. Возвращает размер
    PointF getScale(){ return mSizeScale; }

    // ССЫЛКА - метод. Возвращает компоненты
    String[] getComponents(){return mComponents;}
}
