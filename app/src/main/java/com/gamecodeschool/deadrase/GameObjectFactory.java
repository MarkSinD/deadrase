package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.PointF;

public class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    GameObjectFactory(Context c, PointF mScreenSize, GameEngine gameEngine){
        this.mContext = c;
        this.mScreenSize = mScreenSize;
        mGameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec){
        //Coздадим пустой обьект и начнем его заполнять
        GameObject object = new GameObject();

        // Количество компонентов
        int mNumComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        // ЗАГРУЗКА
        object.setTag(spec.getTag());

        int mSpeedX = (int)(mScreenSize.x / spec.getSpeedX());
        int mSpeedY = (int)(mScreenSize.x / spec.getSpeedY());



        // Размеры по оси Х это getScale.x от экрана
        // Размеры по оси Y это getScale.y от экрана
        PointF objectSize = new PointF(mScreenSize.x / spec.getScale().x,
                mScreenSize.y / spec.getScale().y);

        // Место расположения обьекта находятся ха пределами экрана
        // Обновление будет происходить при вызове метода spawn
        PointF mLocation = new PointF(HIDDEN, HIDDEN);

        // Установка параметов на обьект
        // Загрузка туда скорости, размеров обьекта, локации
        object.setGameParameters(new ObjectParameters(mSpeedX, mSpeedY, objectSize.x, objectSize.y, mLocation, mScreenSize));

        // Заполнение возрождения, движения, инициализации картинки и прорисовки
        for( int i = 0; i < mNumComponents; i++){
            switch (spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent(mGameEngineReference));
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(), mContext, spec, objectSize);
                    break;
                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;
                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawnComponent());
                    break;

                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(),
                            mContext, spec, objectSize);
                    break;

                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;

                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;

                case "SportCarMovementComponent":
                    object.setMovement(new SportCarMovementComponent());
                    break;

                case "MachineHorizontalSpawnComponent" :
                    object.setSpawner(new MachineHorizontalSpawnComponent());

                default:
                    break;

            }
        }

        return object;


    }
}
