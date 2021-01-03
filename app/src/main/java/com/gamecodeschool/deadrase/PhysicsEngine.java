package com.gamecodeschool.deadrase;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.ArrayList;

public class PhysicsEngine {

    boolean update( long fps, ArrayList<GameObject> objects,
                   GameState gs){
        for(GameObject object : objects){
            if(object.checkActive()){
                object.update(fps, objects.get(Level.PLAYER_INDEX).getParametersObject(),
                        objects.get(Level.BACKGROUND_INDEX).getParametersObject());
            }
        }

        return detectCollisions(gs, objects);
    }



    private void barrier(GameObject go1, GameObject go2){
        float carTop = go2.getParametersObject().getCollider().top;
        float carLeft = go2.getParametersObject().getCollider().left;
        float carRight = go2.getParametersObject().getCollider().right;
        float carBottom = go2.getParametersObject().getCollider().bottom;


        float playerTop = go1.getParametersObject().getCollider().top;
        float playerLeft = go1.getParametersObject().getCollider().left;
        float playerRight = go1.getParametersObject().getCollider().right;
        float playerBottom = go1.getParametersObject().getCollider().bottom;


        PointF playerLoc = go1.getParametersObject().getLocation();
        PointF playerCar = go2.getParametersObject().getLocation();


        if( carBottom - playerTop < 40  && playerTop > carTop && playerBottom > carTop && playerBottom > carBottom) {

            //playerLoc.y = go2.getParametersObject().getCollider().bottom;
            playerLoc.y = playerLoc.y + (carBottom - playerTop) + 5;
        }

        else if(playerBottom - carTop < 40 && carBottom > playerBottom && carBottom > playerBottom && playerTop < carBottom ){
            playerLoc.y = go2.getParametersObject().getCollider().top - go1.getParametersObject().getSize().y;

        }

        else if( carLeft < playerRight && playerLeft < carLeft && playerLeft < carRight && carBottom > playerBottom){
            playerCar.x = go1.getParametersObject().getCollider().right;
        }

        else if( playerLeft < carRight && carRight < playerRight && playerRight > carLeft && carLeft < playerLeft){
            playerCar.x = go1.getParametersObject().getCollider().left - go2.getParametersObject().getSize().x;
        }
    }


    private void absolutelyElasticBlow2(GameObject go1, GameObject go2){
        float startSpeedXPl = go1.getParametersObject().getSpeedX();
        float startSpeedXMa = go2.getParametersObject().getSpeedX();
        float endSpeedXPl;
        float endSpeedXMa;
        float m1 = 1000f;
        float m2 = 2000f;
        float vx1 = startSpeedXPl;
        float vx2 = startSpeedXMa;
        endSpeedXPl = (2*m2*vx2 + vx1*(m1-m2))/(m1+m2);
        endSpeedXMa = (2*m1*vx1 + vx2*(m2-m1))/(m1+m2);

        go1.getParametersObject().setNewSpeedX(endSpeedXPl);

        go2.getParametersObject().setNewSpeedX(endSpeedXMa);

        float startSpeedYPl = go1.getParametersObject().getSpeedY();
        float startSpeedYMa = go2.getParametersObject().getSpeedY();
        float endSpeedYPl;
        float endSpeedYMa;
        float vy1 = startSpeedYPl;
        float vy2 = startSpeedYMa;
        endSpeedYPl = (2*m2*vy2 + vy1*(m1-m2))/(m1+m2);
        endSpeedYMa = (2*m1*vy1 + vy2*(m2-m1))/(m1+m2);

        go1.getParametersObject().setNewSpeedY(endSpeedYPl);
        go2.getParametersObject().setNewSpeedY(endSpeedYMa);
    }
    private void absolutelyElasticBlow1(GameObject go1, GameObject go2, GameObject back){
        float startSpeedXPl = back.getParametersObject().getSpeedX();
        float startSpeedXMa = go2.getParametersObject().getSpeedX();
        float endSpeedXPl;
        float endSpeedXMa;
        float m1 = 1000f;
        float m2 = 2000f;
        float vx1 = startSpeedXPl;
        float vx2 = startSpeedXMa;
        endSpeedXPl = (2*m2*vx2 + vx1*(m1-m2))/(m1+m2);
        endSpeedXMa = (2*m1*vx1 + vx2*(m2-m1))/(m1+m2);

        back.getParametersObject().setNewSpeedX(endSpeedXPl);

        go2.getParametersObject().setNewSpeedX(endSpeedXMa);

        float startSpeedYPl = go1.getParametersObject().getSpeedY();
        float startSpeedYMa = go2.getParametersObject().getSpeedY();
        float endSpeedYPl;
        float endSpeedYMa;
        float vy1 = startSpeedYPl;
        float vy2 = startSpeedYMa;
        endSpeedYPl = (2*m2*vy2 + vy1*(m1-m2))/(m1+m2);
        endSpeedYMa = (2*m1*vy1 + vy2*(m2-m1))/(m1+m2);

        go1.getParametersObject().setNewSpeedY(endSpeedYPl);
        go2.getParametersObject().setNewSpeedY(endSpeedYMa);
    }



    private boolean detectCollisions( GameState mGameState, ArrayList<GameObject> objects){
        boolean playerHIT = false;


        // Игрок преобретает свойство активности при возраждении isActive = true
        // И теряет это свойство в двух случаях:
        // 1) При перерождении игрока, вызавает метод deSpawnReSpawn(),
        // который перезапускает все обьекты уровня
        // 2) Когда метод move() возвращает false
        //________________________
        // Итак, что здесь реализовано? Если обьекты активны,
        // давайте просто проверим их на столкновения
        for( GameObject  go1: objects){
            if (go1.checkActive() ){
                for( GameObject go2 : objects){
                    if(go2.checkActive()){
                        if(RectF.intersects(go1.getParametersObject().getCollider(),
                                go2.getParametersObject().getCollider())){
                            switch (go1.getTag() + " with " + go2.getTag()) {
                                case "Player with Machine":



                                    barrier(go1, go2);
                                    absolutelyElasticBlow1(go1, go2, objects.get(Level.BACKGROUND_INDEX));


                                    break;
                                case "Machine with Machine":
                                    barrier(go1, go2);
                                    absolutelyElasticBlow2(go1, go2);
                                    break;
                            }
                        }
                    }
                }
            }
        }
       return false;
    }


}
