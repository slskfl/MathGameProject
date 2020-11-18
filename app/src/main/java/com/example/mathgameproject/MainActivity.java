package com.example.mathgameproject;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnTouchListener {

    private GamePlayThread gamePlayThread;
    private SurfaceHolder surfaceHolder;
    private boolean isRunning;
    private int resetCount;
    private int scoreCount;

    public GameMode gameMode;
    public int surfaceWidth, surfaceHeight;
    public Car car;
    public ScoreBoard scoreBoard;
    public Balloons balloons;
    public Obstacle obstacle;
    public GameStart gameStart;
    public GameCompleted gameCompleted;
    public Pedal accellerator, brake;
    public Jump jump;
    public GameModeChoose gameModeChoose;
    public Oil oil;
    public Reset reset;
    public  Music music;

    private static MediaPlayer backgroundmusic;
    SoundPool soundPool = null;
    int accellratormusic, brakemusic, jumpmusic, stonemusic, watermusic;
    int waitCouter = 1000;
    int waitLimit =0;
    int throttle = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView mainSurfaceViwe = findViewById(R.id.main_surface);
        mainSurfaceViwe.setOnTouchListener(this);
        surfaceHolder = mainSurfaceViwe.getHolder();
        surfaceHolder.addCallback(this);

        backgroundmusic = MediaPlayer.create(this, R.raw.backgound);
        backgroundmusic.setLooping(false);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        accellratormusic = soundPool.load(this, R.raw.accellatormusic,1);
        brakemusic = soundPool.load(this, R.raw.brakemusic,1);
        jumpmusic = soundPool.load(this, R.raw.jumpmusic,1);
    }

    public void stoneMusic(){
        soundPool.play(stonemusic,1f,1f,0,0,2f);
    }

    public void waterMusic(){
        soundPool.play(watermusic,1f,1f,0,0,2f);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        surfaceWidth = width;
        surfaceHeight = height;
        gameMode = GameMode.Ready;
        gamePlayThread = new GamePlayThread();
        isRunning = true;
        gamePlayThread.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gamePlayThread.press(true, (int)motionEvent.getX(), (int)motionEvent.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                gamePlayThread.press(false, (int)motionEvent.getX(), (int)motionEvent.getY());
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
        backgroundmusic.stop();
        soundPool.release();
    }

    public void scoreTOCompleted(){
        gameMode = GameMode.Completed;
    }

  public void hitObstacle() {
        scoreBoard.loseOil(10);
    }

    public boolean plusModeisHit(){
        return true;
    }

    //게임모드 뺄셈 터치
    public boolean minusModeisHit() {
        return true;
    }


    private class GamePlayThread extends Thread {
        public void press(boolean press, int x, int y) {
            switch (gameMode) {
                case Ready:
                    if (press == true && gameStart.isHit(x, y) == true) {
                        gameMode = GameMode.GameMode;
                        scoreBoard.plusreset();
                        balloons.reset();
                        reset.reset();
                        resetCount = 1;
                    }else if (music.isHit(x, y) == true){
                        if(music.musicOffpressed == true){
                            music.OffToOnpress(true);
                            backgroundmusic.pause();
                        }
                        else if(music.musicOnpressed == true)  {
                            music.OnToOffpress(true);
                            backgroundmusic.start();
                        }
                    }
                    break;
                case GameMode:
                    if(press == true ) {
                        if (gameModeChoose.isHitPlus(x, y) == true) {

                            gameModeChoose.Pulspress(true);
                            scoreBoard.Plusmode(true);
                            scoreBoard.resetAnswer();
                            scoreBoard.plusreset();
                        }
                        else if(gameModeChoose.isHitMinus(x,y) == true){
                            //Log.e("slskfl","pointM " + String.valueOf(x ) + " "+ String.valueOf(y));
                            gameModeChoose.Minuspress(true);
                            scoreBoard.Minusmode(true);
                            scoreBoard.resetAnswer();
                            scoreBoard.minusreset();
                        }
                        gameMode = GameMode.Play;
                        scoreCount = 0;
                        balloons.reset();
                    } else if (music.isHit(x, y) == true){
                        if(music.musicOffpressed == true){
                            music.OffToOnpress(true);
                            backgroundmusic.pause();
                        }
                        else if(music.musicOnpressed == true)  {
                            music.OnToOffpress(true);
                            backgroundmusic.start();
                        }
                    }
                    break;
                case Play:
                    if (press == true) {
                        if (accellerator.isHit(x, y) == true) {
                            accellerator.press(true);
                            while (soundPool.play(accellratormusic,1f,1f,0,0,1f)==0 && waitCouter < waitLimit);

                        } else if (brake.isHit(x, y) == true) {
                            brake.press(true);
                            while (soundPool.play(brakemusic,1f,1f,0,0,2f) == 0 && waitCouter < waitLimit);

                        } else if (jump.isHit(x, y) == true) {
                            car.jump();
                            while (soundPool.play(jumpmusic,1f,1f,0,0,0.5f)== 0 && waitCouter < waitLimit);
                        } else if (reset.isHit(x,y) == true){
                            reset.press(true);
                            if (resetCount == 1){
                                scoreBoard.resetAnswer();
                                balloons.reset();
                                resetCount--;
                            }
                        }else if (music.isHit(x, y) == true){
                            if(music.musicOffpressed == true){
                                music.OffToOnpress(true);
                                backgroundmusic.pause();
                                soundPool.pause(accellratormusic);
                                soundPool.pause(brakemusic);
                                soundPool.pause(jumpmusic);
                            }
                            else if(music.musicOnpressed == true)  {
                                music.OnToOffpress(true);
                                backgroundmusic.start();
                                soundPool.resume(accellratormusic);
                                soundPool.resume(brakemusic);
                                soundPool.resume(jumpmusic);
                            }
                        }
                    } else {
                        accellerator.press(false);
                        brake.press(false);
                    }
                    break;
                case Completed:
                    if (press == true) {
                        if (gameCompleted.isHitBack(x, y) == true) {
                            gameMode = GameMode.GameMode;
                            scoreCount = 0;
                            scoreBoard.plusreset();
                            balloons.reset();
                            reset.reset();
                            resetCount = 1;
                        } else if (gameCompleted.isHitHome(x, y) == true) {
                            gameMode = GameMode.Ready;
                        }else if (music.isHit(x, y) == true){
                            if(music.musicOffpressed == true){
                                music.OffToOnpress(true);
                                backgroundmusic.pause();
                            }
                            else if(music.musicOnpressed == true)  {
                                music.OnToOffpress(true);
                                backgroundmusic.start();

                            }
                        }
                    }
                    break;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void run() {
            super.run();

            Paint skyPaint = new Paint();
            skyPaint.setColor(getResources().getColor(R.color.colorSky, null));

            Mountain mountain = new Mountain(MainActivity.this);
            gameStart = new GameStart(MainActivity.this);
            gameCompleted = new GameCompleted(MainActivity.this);
            accellerator = new Pedal(MainActivity.this, 20, 600, R.drawable.accellerator, R.drawable.accellerator_pressed);
            brake = new Pedal(MainActivity.this, 150, 700, R.drawable.brake, R.drawable.brake_pressed);
            jump = new Jump(MainActivity.this, 1600, 700);
            car = new Car(MainActivity.this);
            scoreBoard = new ScoreBoard(MainActivity.this,0,0);
            balloons = new Balloons(MainActivity.this, 6);
            gameModeChoose = new GameModeChoose(MainActivity.this);
            obstacle = new Obstacle(MainActivity.this);
            oil = new Oil(MainActivity.this);
            reset = new Reset(MainActivity.this, 1600, 500);
            music = new Music(MainActivity.this, 1600, 0);

            scoreBoard.resetAnswer();
            backgroundmusic.setLooping(true);
            backgroundmusic.start();

            while (isRunning) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    canvas.drawRect(0, 0, surfaceWidth - 1, surfaceHeight - 1, skyPaint);
                    mountain.draw(canvas);
                    music.draw(canvas);

                    switch (gameMode) {
                        case Ready:
                            gameStart.draw(canvas);
                            break;
                       case GameMode:
                            gameModeChoose.draw(canvas);
                            break;
                        case Play:
                            car.draw(canvas);
                            balloons.draw(canvas);
                            oil.draw(canvas);
                            accellerator.draw(canvas);
                            brake.draw(canvas);
                            jump.draw(canvas);
                            obstacle.draw(canvas);
                            scoreBoard.draw(canvas);
                            reset.draw(canvas);
                            break;
                        case Completed:
                            gameCompleted.draw(canvas);
                            backgroundmusic.release();
                            break;
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private enum GameMode {
        Ready, Play, Completed, GameMode
    }
}