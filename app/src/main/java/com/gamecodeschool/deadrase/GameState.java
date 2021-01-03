package com.gamecodeschool.deadrase;

import android.content.Context;
import android.content.SharedPreferences;


final class GameState {
    private static volatile boolean mThreadRunnuing = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;

    private static GameStarter gameStarter;

    private int mScore;
    private int mHighScore;
    private int mNumCars;

    private SharedPreferences.Editor mEditor;

    GameState(GameStarter gs, Context context){
         gameStarter = gs;
         SharedPreferences prefs;
         prefs = context.getSharedPreferences("HiScore",
                 Context.MODE_PRIVATE);
         mEditor = prefs.edit();
         mHighScore = prefs.getInt("hi_score", 0);
    }

    private void endGame(){
        mGameOver = true;
        mPaused = true;
        if(mScore > mHighScore){
            mHighScore = mScore;
            mEditor.putInt("hi_score", mHighScore);
            mEditor.commit();
        }
    }
    void startNewGame(){
        mScore = 0;
        mNumCars = 100;
        stopDrawing();
        gameStarter.deSpawnReSpawn();
        resume();
        startDrawing();
    }

    void stopEverything(){
        mPaused = true;
        mGameOver = true;
        mThreadRunnuing = false;
    }

    void resume(){
        mGameOver = false;
        mPaused = false;
    }

    int getScore(){return mScore; }
    int getHighScore() { return mHighScore; }
    int getNumCars() { return mNumCars; }

    void pause(){ mPaused = true; }

    boolean getThreadRunning(){ return mThreadRunnuing; }
    void startThread(){mThreadRunnuing =true; }
    private void stopDrawing(){ mDrawing = false; }
    private void startDrawing (){ mDrawing = true; }
    boolean getDrawing(){ return mDrawing; }
    boolean getPaused() { return mPaused; }
    boolean getGameOver() {return mGameOver; }

}
