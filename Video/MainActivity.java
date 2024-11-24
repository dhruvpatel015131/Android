package com.example.demoimagevideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button button,button2;
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button=findViewById(R.id.btnPlay);
        button2=findViewById(R.id.btnVidVw);
        surfaceView=findViewById(R.id.surfaceView);
        seekBar=findViewById(R.id.seekBar);

        mediaPlayer=MediaPlayer.create(this,R.raw.nature);
        surfaceView.setKeepScreenOn(true);
        SurfaceHolder surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                mediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}

        });
        seekBar.setMax(mediaPlayer.getDuration());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                }catch (Exception e){
                    e.printStackTrace();
                    }
                }
            },0,800);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        button.setOnClickListener(v->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                button.setText(R.string.play);
            }
            else {
                mediaPlayer.start();
                button.setText(R.string.pause);
            }
        });

        button2.setOnClickListener(v->{
            Intent i=new Intent(this,MainVideoViewActivity.class);
            startActivity(i);
        });
    }
}