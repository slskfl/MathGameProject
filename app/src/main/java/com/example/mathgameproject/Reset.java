package com.example.mathgameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Reset {
    private int resetX, resetY;
    private boolean resetPressed;
    private Bitmap reset0Bitmap, reset1Bitmap;

    public Reset(Context context, int resetX, int resetY){
        this.resetX = resetX;
        this.resetY = resetY;
        resetPressed = false;
        reset0Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset00);
        reset1Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reset01);
        reset();
    }

    public void reset(){
        resetPressed = false;
    }


    public boolean isHit(int x, int y) {
        if (this.resetX < x && x < this.resetX + reset0Bitmap.getWidth() && this.resetY < y && y < this.resetY + reset0Bitmap.getHeight()) {
            return true;
        }
        return false;
    }
    public void press(boolean press) {
        resetPressed = press;
    }

    public void draw(Canvas canvas){
        if (resetPressed == true) {
            canvas.drawBitmap(reset0Bitmap, resetX, resetY, null);
        }else {
            canvas.drawBitmap(reset1Bitmap, resetX, resetY, null);
        }

    }
}
