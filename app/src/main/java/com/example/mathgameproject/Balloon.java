package com.example.mathgameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.RequiresApi;

import java.util.List;

public class Balloon {
    private static final int FONT_SIZE = 32;

    private MainActivity mainActivity;
    private Bitmap bitmap;
    private int x, y;
    private int number;
    private Paint paint;
    private boolean isVisible;
    private List<Bitmap> bitmapList;
    private int fireworksIndex;
    private List<Bitmap> fireworksBitmapList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Balloon(MainActivity mainActivity, List<Bitmap> bitmapList, List<Bitmap> fireworksBitmapList) {
        this.mainActivity = mainActivity;
        this.bitmapList = bitmapList;
        this.fireworksBitmapList = fireworksBitmapList;
        bitmap = bitmapList.get((int)(Math.random() * bitmapList.size()));
        isVisible = true;
        number = (int)(Math.random() * 90) + 10;
        paint = new Paint();

        DisplayMetrics displayMetrics = mainActivity.getResources().getDisplayMetrics();
        float fontSizeInPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE, displayMetrics);
        paint.setTextSize(fontSizeInPixel);
        paint.setColor(mainActivity.getResources().getColor(R.color.colorPrimaryDark, null));
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return 0;
    }

    public void draw(Canvas canvas) {
        if (isVisible == true && getCollisionRect().intersect(mainActivity.car.getCollisionRect(0.7))) {
            isVisible = false;
            fireworksIndex = 0;
            if(mainActivity.gameModeChoose.isPressedPlus){
                if (mainActivity.scoreBoard.getPlusAnswer() == number) {
                    number = (int) (Math.random() * 90) + 10;
                    mainActivity.scoreBoard.addPlusScore(20);
                    mainActivity.scoreBoard.resetAnswer();
                } else if(mainActivity.scoreBoard.getPlusAnswer() != number) {
                    mainActivity.scoreBoard.addPlusScore(-10);
                }
                if (mainActivity.scoreBoard.getPlusScore()==-70) {
                    mainActivity.scoreTOCompleted();
                }
            } else if(mainActivity.gameModeChoose.isPressedMinus){
                if (mainActivity.scoreBoard.getMinusAnswer() == number) {
                    number = (int) (Math.random() * 90) + 10;
                    mainActivity.scoreBoard.addMinusScore(20);
                    mainActivity.scoreBoard.resetAnswer();
                } else if(mainActivity.scoreBoard.getMinusAnswer() != number) {
                    mainActivity.scoreBoard.addMinusScore(-10);
                }
                if(mainActivity.scoreBoard.getMinusScore()==-70){
                    mainActivity.scoreTOCompleted();
                }
            }
        }

        if (isVisible) {
            canvas.drawBitmap(bitmap, x, y, null);
            String numberString = String.valueOf(number);
            float numberX = (bitmap.getWidth() - paint.measureText(numberString)) / 2;
            canvas.drawText(String.valueOf(number), x + numberX, y + (float) (bitmap.getHeight() * 0.70), paint);
        } else {
            if (fireworksIndex < fireworksBitmapList.size() * 5) {
                canvas.drawBitmap(fireworksBitmapList.get(fireworksIndex / 5), x, y, null);
                ++fireworksIndex;
            }
        }

        y += 4;

        if (y > mainActivity.surfaceHeight) {
            x = (int)(Math.random() * (mainActivity.surfaceWidth - bitmap.getWidth()));
            y = 0 - bitmap.getHeight();
            isVisible = true;
            bitmap = bitmapList.get((int)(Math.random() * bitmapList.size()));
        }
    }

    public int getNumber() {
        return number;
    }

    public Rect getRect() {
        return new Rect(x, y, x + bitmap.getWidth() - 1, y + bitmap.getHeight() - 1);
    }

    private static final double collisionRate = 0.6;
    public Rect getCollisionRect() {
        return new Rect((int)(x + (bitmap.getWidth() * (1 - collisionRate)) / 2), (int)(y + (bitmap.getHeight() * (1 - collisionRate)) / 2), (int)(x + (bitmap.getWidth() * collisionRate) - 1), (int)(y + (bitmap.getHeight() * collisionRate) - 1));
    }
}
