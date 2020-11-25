package com.example.mathgameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Mountain  extends GameObject  {
   /* private Bitmap mountainBitmap;
    private int mountain1X, mountain2X, mountainY;*/
   private Bitmap mountainBitmap, seaBitmap;
    private int mountain1X, mountain2X, mountainY;
    private int sea1X, sea2X, seaY;
    private long startTimestamp;
    private MainActivity mainActivity;

    public Mountain(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;

        mountainBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.mountain);
        mountain1X = 0;
        mountain2X = mountainBitmap.getWidth();
        mountainY = mainActivity.surfaceHeight - mountainBitmap.getHeight();

        seaBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.seabackground);
        sea1X = 0;
        sea2X = seaBitmap.getWidth();
        seaY = mainActivity.surfaceHeight - seaBitmap.getHeight();

        startTimestamp = System.currentTimeMillis();
        /*mountainBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.mountain);
        mountain1X = 0;
        mountain2X = mountainBitmap.getWidth();
        mountainY = mainActivity.surfaceHeight - mountainBitmap.getHeight();*/
    }

    public void draw(Canvas canvas) {
       /* canvas.drawBitmap(mountainBitmap, mountain1X, mountainY, null);
        canvas.drawBitmap(mountainBitmap, mountain2X, mountainY, null);
        mountain1X -= 10;
        mountain2X -= 10;
        if (mountain1X + mountainBitmap.getWidth() < 0) {
            mountain1X = mountain2X + mountainBitmap.getWidth();
        }
        if (mountain2X + mountainBitmap.getWidth() < 0) {
            mountain2X = mountain1X + mountainBitmap.getWidth();
        }*/
        if (((System.currentTimeMillis() - startTimestamp) / 10000) % 2 == 0) {
            canvas.drawBitmap(mountainBitmap, mountain1X, mountainY, null);
            canvas.drawBitmap(mountainBitmap, mountain2X, mountainY, null);
            //setBitmap(mountainBitmap);
            mountain1X -= 10;
            mountain2X -= 10;
            if (mountain1X + mountainBitmap.getWidth() < 0) {
                mountain1X = mountain2X + mountainBitmap.getWidth();
            }
            if (mountain2X + mountainBitmap.getWidth() < 0) {
                mountain2X = mountain1X + mountainBitmap.getWidth();
            }
        } else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 2 == 1){

            canvas.drawBitmap(seaBitmap, sea1X, seaY, null);
            canvas.drawBitmap(seaBitmap, sea2X, seaY, null);
            // setBitmap(seaBitmap);
            sea1X -= 10;
            sea2X -= 10;
            if (sea1X + seaBitmap.getWidth() < 0) {
                sea1X = sea2X + seaBitmap.getWidth();
            }
            if (sea2X + seaBitmap.getWidth() < 0) {
                sea2X = sea1X + seaBitmap.getWidth();
            }
        }
    }
}
