package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

class GameObject {
    // GameObject содержит:
    // Свойства обьекта
    private ObjectParameters mParametersObject;
    // Флаг активности
    private boolean isActive = false;
    // Таг
    private String mTag;

    // Графический компонент (метод прорисовки)
    private GraphicsComponent graphicsComponent;

    // Перемещающий компонент (метод движения, изменения координаты в пространстве)
    private MovementComponent movementComponent;

    // Возрождающий компонент (метод возрождения)
    private SpawnComponent spawnComponent;


    // Установка возроджающего компотента
    void setSpawner( SpawnComponent s){
        spawnComponent = s;
    }

    // Установка графического компонента
    void setGraphics(GraphicsComponent g, Context c, ObjectSpec spec, PointF objectSize){
        graphicsComponent = g;
        g.intitialize(c,spec, objectSize);
    }

    void setInput(InputComponent s) { s.setTransform(mParametersObject);}

    // Установка перемещающего компонента
    void setMovement(MovementComponent m) { movementComponent = m;}

    // Установка Тага
    void setTag(String tag) { mTag = tag; }

    // Установка свойств
    void setGameParameters(ObjectParameters OP){ mParametersObject = OP; }

    // Вызов прорисовочного метода из компонента графики
    void draw(Canvas canvas, Paint paint){
        graphicsComponent.draw(canvas, paint, mParametersObject);
    }

    // Вызов перемещательного метода из компонента движения
    void update(long fps, ObjectParameters PlayerParameters, ObjectParameters mBackgroundParemeters){
        if( !(movementComponent.move(fps, mParametersObject, PlayerParameters, mBackgroundParemeters))){
            isActive = false;
        }
    }

    // Вызов возрождающего метода из компонента возрождения
    boolean spawn(ObjectParameters PlayerParameters){
        if(!isActive){
            spawnComponent.spawn(mParametersObject ,PlayerParameters);
            isActive = true;
            return true;
        }
        return false;
    }

    // Геттер isActive
    boolean checkActive(){

        return isActive;
    }

    // Геттер Тага
    String getTag(){

        return mTag;
    }

    // Установка неактивности
    void setInactive(){

        isActive= false;
    }


    // ССЫЛКА - метод
    ObjectParameters getParametersObject(){

        return mParametersObject;
    }


}
