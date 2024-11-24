package com.example.audio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageButton play,pause;
    SeekBar seekBar;
    Thread updateSeek;
    MediaPlayer mediaPlayer;
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

        play=findViewById(R.id.btnPlay);
        pause=findViewById(R.id.btnPause);
        seekBar=findViewById(R.id.seekBar);
        mediaPlayer=MediaPlayer.create(this,R.raw.sample1);
        mediaPlayer.start();

        seekBar.setMax(mediaPlayer.getDuration());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        },0,800);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });


        play.setOnClickListener(v->{mediaPlayer.start();});
        pause.setOnClickListener(v->{mediaPlayer.pause();});
    }

    private void clearMediaPlayer(){
        mediaPlayer.stop();
        mediaPlayer.release();
        updateSeek.interrupt();
        mediaPlayer=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMediaPlayer();
    }
}