package com.example.mathgameproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Mountain  extends GameObject  {
    private Bitmap mountainBitmap, background1Bitmap, background2Bitmap, background3Bitmap;
    private int mountain1X, mountain2X, mountain3X, mountain4X, mountainY;
    private int tree1X, tree2X, tree3X, tree4X, tree5X, treeY;
    private int desert1X, desert2X, desert3X, desert4X, desert5X,desertY;
    private int winter1X, winter2X, winter3X, winter4X, winter5X, winterY;
    private long startTimestamp;
    private MainActivity mainActivity;

    public Mountain(MainActivity mainActivity) {
        super(mainActivity, mainActivity.surfaceWidth, mainActivity.surfaceHeight);
        this.mainActivity = mainActivity;

        mountainBitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.mountain);
        mountain1X = 0;
        mountain2X = mountainBitmap.getWidth();
        mountain3X = mountainBitmap.getWidth() + mountainBitmap.getWidth();
        mountain4X = mountainBitmap.getWidth() + mountainBitmap.getWidth() + mountainBitmap.getWidth();
        mountainY = mainActivity.surfaceHeight - mountainBitmap.getHeight();

        background1Bitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.bcakground1);
        tree1X = 0;
        tree2X = background1Bitmap.getWidth();
        tree3X = background1Bitmap.getWidth() + background1Bitmap.getWidth();
        tree4X = background1Bitmap.getWidth() + background1Bitmap.getWidth() + background1Bitmap.getWidth();
        tree5X = background1Bitmap.getWidth() + background1Bitmap.getWidth() + background1Bitmap.getWidth() + background1Bitmap.getWidth();
        treeY = mainActivity.surfaceHeight - background1Bitmap.getHeight();

        background2Bitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.bcakground2);
        desert1X = 0;
        desert2X = background2Bitmap.getWidth();
        desert3X = background2Bitmap.getWidth() + background2Bitmap.getWidth();
        desert4X = background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth();
        desert5X = background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth();
        desertY = mainActivity.surfaceHeight - background2Bitmap.getHeight();

        background3Bitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.bcakground3);
        winter1X = 0;
        winter2X = background2Bitmap.getWidth();
        winter3X = background2Bitmap.getWidth() + background2Bitmap.getWidth();
        winter4X = background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth();
        winter5X = background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth() + background2Bitmap.getWidth();
        winterY = mainActivity.surfaceHeight - background3Bitmap.getHeight();

        startTimestamp = System.currentTimeMillis();
    }

    public void draw(Canvas canvas) {

        if (((System.currentTimeMillis() - startTimestamp) / 10000) % 4 == 0) {
            canvas.drawBitmap(mountainBitmap, mountain1X, mountainY, null);
            canvas.drawBitmap(mountainBitmap, mountain2X, mountainY, null);
            canvas.drawBitmap(mountainBitmap, mountain3X, mountainY, null);
            canvas.drawBitmap(mountainBitmap, mountain4X, mountainY, null);
            mountain1X -= 10;
            mountain2X -= 10;
            mountain3X -= 10;
            mountain4X -= 10;
            if (mountain1X + mountainBitmap.getWidth() < 0) {
                mountain1X = mountain2X + mountainBitmap.getWidth();
            }
            if (mountain2X + mountainBitmap.getWidth() < 0) {
                mountain2X = mountain3X + mountainBitmap.getWidth();
            }
            if (mountain3X + mountainBitmap.getWidth() < 0) {
                mountain3X = mountain4X + mountainBitmap.getWidth();
            }
            if (mountain4X + mountainBitmap.getWidth() < 0) {
                mountain4X = mountain1X + mountainBitmap.getWidth();
            }
        } else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 4 == 1){

            canvas.drawBitmap(background1Bitmap, tree1X, treeY, null);
            canvas.drawBitmap(background1Bitmap, tree2X, treeY, null);
            canvas.drawBitmap(background1Bitmap, tree3X, treeY, null);
            canvas.drawBitmap(background1Bitmap, tree4X, treeY, null);
            canvas.drawBitmap(background1Bitmap, tree5X, treeY, null);
            // setBitmap(seaBitmap);
            tree1X -= 10;
            tree2X -= 10;
            tree3X -= 10;
            tree4X -= 10;
            tree5X -= 10;
            if (tree1X + background1Bitmap.getWidth() < 0) {
                tree1X = tree2X + background1Bitmap.getWidth();
            }
            if (tree2X + background1Bitmap.getWidth() < 0) {
                tree2X = tree3X + background1Bitmap.getWidth();
            }
            if (tree3X + background1Bitmap.getWidth() < 0) {
                tree3X = tree4X + background1Bitmap.getWidth();
            }
            if (tree4X + background1Bitmap.getWidth() < 0) {
                tree4X = tree5X + background1Bitmap.getWidth();
            }
            if (tree5X + background1Bitmap.getWidth() < 0) {
                tree5X = tree1X + background1Bitmap.getWidth();
            }
        } else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 4 == 2){

            canvas.drawBitmap(background2Bitmap, desert1X, desertY, null);
            canvas.drawBitmap(background2Bitmap, desert2X, desertY, null);
            canvas.drawBitmap(background2Bitmap, desert3X, desertY, null);
            canvas.drawBitmap(background2Bitmap, desert4X, desertY, null);
            canvas.drawBitmap(background2Bitmap, desert5X, desertY, null);
            // setBitmap(seaBitmap);
            desert1X -= 10;
            desert2X -= 10;
            desert3X -= 10;
            desert4X -= 10;
            desert5X -= 10;
            if (desert1X + background2Bitmap.getWidth() < 0) {
                desert1X = desert2X + background2Bitmap.getWidth();
            }
            if (desert2X + background2Bitmap.getWidth() < 0) {
                desert2X = desert3X + background2Bitmap.getWidth();
            }
            if (desert3X + background2Bitmap.getWidth() < 0) {
                desert3X = desert4X + background2Bitmap.getWidth();
            }
            if (desert4X + background2Bitmap.getWidth() < 0) {
                desert4X = desert5X + background2Bitmap.getWidth();
            }
            if (desert5X + background2Bitmap.getWidth() < 0) {
                desert5X = desert1X + background2Bitmap.getWidth();
            }
        } else if (((System.currentTimeMillis() - startTimestamp) / 10000) % 4 == 3){

            canvas.drawBitmap(background3Bitmap, winter1X, winterY, null);
            canvas.drawBitmap(background3Bitmap, winter2X, winterY, null);
            canvas.drawBitmap(background3Bitmap, winter3X, winterY, null);
            canvas.drawBitmap(background3Bitmap, winter4X, winterY, null);
            canvas.drawBitmap(background3Bitmap, winter5X, winterY, null);
            // setBitmap(seaBitmap);
            winter1X -= 10;
            winter2X -= 10;
            winter3X -= 10;
            winter4X -= 10;
            winter5X -= 10;

            if (winter1X + background3Bitmap.getWidth() < 0) {
                winter1X = winter2X + background3Bitmap.getWidth();
            }
            if (winter2X + background3Bitmap.getWidth() < 0) {
                winter2X = winter3X + background3Bitmap.getWidth();
            }
            if (winter3X + background3Bitmap.getWidth() < 0) {
                winter3X = winter4X + background3Bitmap.getWidth();
            }
            if (winter4X + background3Bitmap.getWidth() < 0) {
                winter4X = winter5X + background3Bitmap.getWidth();
            }
            if (winter5X + background3Bitmap.getWidth() < 0) {
                winter5X = winter1X + background3Bitmap.getWidth();
            }
        }

    }
}
