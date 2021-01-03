package com.gamecodeschool.deadrase;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameEngine  extends SurfaceView
implements Runnable, GameStarter, GameEngineBroadcaster {

    private long mFPS;
    private ArrayList<InputObserver> inputObservers = new ArrayList();
    private Thread mThread = null;
    private GameState mGameState;
    PhysicsEngine mPhysicsEngine;
    Renderer mRenderer;
    HUD mHUD;
    UIController mUIController;
    Level mLevel;



    public GameEngine(Context context, Point size) {
        super(context);

        mUIController = new UIController(this);
        mGameState = new GameState(this, context);
        mHUD = new HUD(size , context);
        mPhysicsEngine = new PhysicsEngine();
        mRenderer = new Renderer(this);
        mLevel = new Level(context, new PointF(size.x, size.y), this);

    }

    @Override
    public void run() {
        while (mGameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> objects = mLevel.getGameObjects();

            if (!mGameState.getPaused()) {
                if(mPhysicsEngine.update(mFPS,objects, mGameState)){
                    deSpawnReSpawn();
                }
            }

            mRenderer.draw(objects, mGameState, mHUD);

            long timeThisFrame = System.currentTimeMillis()
                    - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 10000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

    }

    @Override
    public void deSpawnReSpawn() {
        ArrayList<GameObject> objects = mLevel.getGameObjects();
        for( GameObject o : objects)
            o.setInactive();

        objects.get(Level.BACKGROUND_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.PLAYER_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_1).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_2).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_3).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_4).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_5).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());
        objects.get(Level.MACHINE_6).spawn(objects.get(Level.PLAYER_INDEX).getParametersObject());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (InputObserver o : inputObservers){
            o.handleInput(event, mGameState, mHUD.getControls());
        };
        return  true;
    }
    public void stopThread(){
        mGameState.stopEverything();
        try {
            mThread.join();
        }
        catch( InterruptedException e){
            Log.e("Exception", "stopThread()" + e.getMessage());
        }
    }

    public void startThread(){

        mGameState.startThread();
        mThread = new Thread(this);
        mThread.start();

    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }
}
