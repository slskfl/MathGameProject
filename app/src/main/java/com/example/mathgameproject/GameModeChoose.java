package com.example.mathgameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class GameModeChoose extends GameObject {
    private Bitmap gamemodeBitmap;
    private Rect plusRect, minusRect;
    public int plusmode1X, plusmode2X, plusmode3X, plusmodeY;
    public MainActivity mainActivity;
    public boolean isPressedPlus,isPressedMinus;
    public float x, y ;

    public GameModeChoose(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);

        this.mainActivity = mainActivity;
        isPressedPlus = false;
        isPressedMinus = false;
        x=455;
        y=255;

        gamemodeBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.gamemode);
        setBitmap(gamemodeBitmap);
        alignCenter();
        plusmode1X =0;
        plusmode2X = mainActivity.surfaceWidth/2;
        plusmode3X = mainActivity.surfaceWidth;

        plusRect = getRect(455, 255 , 375, 350);
        minusRect = getRect(915, 255,375, 350);
    }
    public boolean isHitPlus(int x, int y) {
        if (gamemodeBitmap!=null && this.x < x && x <this.x + gamemodeBitmap.getWidth()/2 && plusmodeY < y && y < plusmodeY + gamemodeBitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public boolean isHitMinus(int x, int y) {
        if (gamemodeBitmap!=null && this.x + gamemodeBitmap.getWidth()/2 < x && x < this.x + gamemodeBitmap.getWidth() && plusmodeY < y && y < plusmodeY + gamemodeBitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public void Pulspress(boolean press) {
        isPressedPlus = press;
    }
    public void Minuspress(boolean press) {
        isPressedMinus = press;
    }
    public boolean isPressedPlus() {
        return isPressedPlus;
    }
    public boolean isPressedMinus() {
        return isPressedMinus;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        setBitmap(gamemodeBitmap);
    }


}