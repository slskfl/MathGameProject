package com.example.mathgameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;

public class Car extends GameObject {
    private MainActivity mainActivity;
    private boolean isJumping;
    private int jumpIndex;
    private int[] jumpSteps = {-6, -6, -6, -6, -4, -4, -4, -4, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 6, 6, 6, 6};
    private  Bitmap carbimap0,carbimap1,carbimap2,carbimap3,carbimap4,carbimap5,carbimap6,carbimap7,carbimap8,carbimap9,carbimap10;
    private long startTimestamp;

    public Car(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;
        carbimap0 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car0);
        carbimap1 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car1);
        carbimap2 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car2);
        carbimap3 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car3);
        carbimap4 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car4);
        carbimap5 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car5);
        carbimap6 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car6);
        carbimap7 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car5);
        carbimap8 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car4);
        carbimap9 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car3);
        carbimap9 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car2);
        carbimap10 = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ic_car1);
        setBitmap(R.drawable.ic_car0);

        setXY(280, mainActivity.surfaceHeight - getHeight());
        isJumping = false;
        startTimestamp = System.currentTimeMillis();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 0){
            setBitmap(carbimap0);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 1){
            setBitmap(carbimap1);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 2){
            setBitmap(carbimap2);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 3){
            setBitmap(carbimap3);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 4){
            setBitmap(carbimap4);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 5){
            setBitmap(carbimap5);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 6) {
            setBitmap(carbimap6);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 7) {
            setBitmap(carbimap7);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 8){
            setBitmap(carbimap8);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 9){
            setBitmap(carbimap9);
        }else if (((System.currentTimeMillis() - startTimestamp) / 1000) % 11 == 10){
            setBitmap(carbimap10);
        }

        if (isJumping) {
            moveYdp(jumpSteps[jumpIndex]);
            jumpIndex++;
            if (jumpIndex >= jumpSteps.length) {
                isJumping = false;
            }
        }
        if (mainActivity.accellerator.isPressed() == true) {
            moveXdp(5);
        }
        if (mainActivity.brake.isPressed() == true) {
            moveXdp(-5);
        }
    }

    public void jump() {
        if (isJumping == false) {
            jumpIndex = 0;
            isJumping = true;
        }
    }
}
