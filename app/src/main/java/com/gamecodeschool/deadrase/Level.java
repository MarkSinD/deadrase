package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

public class Level {
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int MACHINE_1 = 2;
    public static final int MACHINE_2 = 3;
    public static final int MACHINE_3 = 4;
    public static final int MACHINE_4 = 5;
    public static final int MACHINE_5 = 6;
    public static final int MACHINE_6 = 7
            ;
    public static ArrayList<GameObject> objects;

    public Level(Context context, PointF mScreenSize, GameEngine ge){
        objects = new ArrayList<>();

        GameObjectFactory factory = new GameObjectFactory(
                context, mScreenSize, ge);

        buildGameObjects(factory);

    }

    ArrayList<GameObject> buildGameObjects( GameObjectFactory factory){
        objects.clear();
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));
        objects.add(PLAYER_INDEX, factory.create(new PlayerSpec()));
        objects.add(MACHINE_1, factory.create(new SportCarSpec()));
        objects.add(MACHINE_2, factory.create(new BlackCarSpec()));
        objects.add(MACHINE_3, factory.create(new CabrioletCarSpec()));
        objects.add(MACHINE_4, factory.create(new CrossoverCarSpec()));
        objects.add(MACHINE_5, factory.create(new MersedesCarSpec()));
        objects.add(MACHINE_6, factory.create(new RedCarSpec()));

        Log.e("buildGameObjects" , "Создал фон");
        return objects;
    }

    public static ArrayList<GameObject> getGameObjects(){ return objects; }
}
