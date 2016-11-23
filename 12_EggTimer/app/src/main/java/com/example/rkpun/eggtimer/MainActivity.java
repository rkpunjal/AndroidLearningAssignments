package com.example.rkpun.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String GO = "Go!";
    public static final String STOP = "STOP";

    public static int SEEK_MAX_VALUE = 60*15;
    public static int SEEK_DEFAULT_VALUE = 60*7;

    private enum TimerState {TIMER_STOPPED, TIMER_RUNNING};

    private Button goStopButton = null;
    private SeekBar timerSeekbar = null;
    private TextView timeTextView = null;
    private CountDownTimer countDownTimer = null;
    private int setTime = 0;

    private TimerState currentState = null;
    private MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeApp();
    }

    private void initializeApp(){

        currentState = TimerState.TIMER_STOPPED;

        goStopButton = (Button)findViewById(R.id.goStopButton);
        goStopButton.setText(GO);

        timeTextView = (TextView)findViewById(R.id.timeTextView);

        timerSeekbar = (SeekBar)findViewById(R.id.timeSeekbar);
        timerSeekbar.setMax(SEEK_MAX_VALUE);
        timerSeekbar.setProgress(SEEK_DEFAULT_VALUE);
        showTime(SEEK_DEFAULT_VALUE);
        setTime = SEEK_DEFAULT_VALUE;


        mPlayer = MediaPlayer.create(this, R.raw.alaram);
        mPlayer.setLooping(true);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 1;
            int previous=1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;

                if(progress != previous){
                    previous = progress;
                    setTime = progressValue;
                    showTime(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

    }


    public void onGoStopButtonClick(View view){
        if(currentState == TimerState.TIMER_STOPPED){
            startTimer();
        }else{
            stopTimer();
        }
    }

    private void startTimer(){

        timerSeekbar.setEnabled(false);
        goStopButton.setText(STOP);
        currentState = TimerState.TIMER_RUNNING;

        countDownTimer = new CountDownTimer(setTime*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                showTime((int)(millisUntilFinished/1000));
            }

            public void onFinish() {
                showTime(0);
                playAlaramSound();
            }
        }.start();

    }

    private void stopTimer(){

        timerSeekbar.setEnabled(true);
        goStopButton.setText(GO);
        currentState = TimerState.TIMER_STOPPED;
        showTime(SEEK_DEFAULT_VALUE);
        timerSeekbar.setProgress(SEEK_DEFAULT_VALUE);

        countDownTimer.cancel();

        if(mPlayer!=null && mPlayer.isPlaying()){
            mPlayer.stop();
        }
    }

    private void playAlaramSound(){
        mPlayer.start();
    }


    private void showTime(int seconds){
        String timeString = getPrintableTime(seconds);
        timeTextView.setText(timeString);
    }


    private String getPrintableTime(int seconds){
        if(seconds<1){
            return "00:00";
        }
        int sec = seconds % 60;
        int min = (seconds / 60)%60;

        return String.format("%02d:%02d", min, sec);
    }

}
