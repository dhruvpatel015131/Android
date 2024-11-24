package com.example.demoimagevideo;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;


public class MainVideoViewActivity extends AppCompatActivity {
    VideoView videoView;
    String videoURL="https://paglasongs.com/files/download/id/588";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        //video view
        videoView=findViewById(R.id.videoView);
        Uri uri=Uri.parse(videoURL);
        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
