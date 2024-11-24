package com.example.graphicsandanimation;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button blinkBtn, rotateBtn, fadeBtn, moveBtn, slideBtn, zoomBtn, stopBtn;


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

        imageView = findViewById(R.id.imageView);
        blinkBtn = findViewById(R.id.button);
        rotateBtn = findViewById(R.id.button2);
        fadeBtn = findViewById(R.id.button3);
        moveBtn = findViewById(R.id.button4);
        slideBtn = findViewById(R.id.button5);
        zoomBtn = findViewById(R.id.button6);
        stopBtn = findViewById(R.id.button7);

        blinkBtn.setOnClickListener(v->{
            Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
            imageView.startAnimation(blink);
        });

        rotateBtn.setOnClickListener(v->{
            Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            imageView.startAnimation(rotate);
        });

        fadeBtn.setOnClickListener(v->{
            Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            imageView.startAnimation(fade);
        });
        moveBtn.setOnClickListener(v->{
            Animation move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
            imageView.startAnimation(move);
        });
        slideBtn.setOnClickListener(v->{
            Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
            imageView.startAnimation(slide);
        });

        zoomBtn.setOnClickListener(v->{
            Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
            imageView.startAnimation(zoom);
        });

        stopBtn.setOnClickListener(v->{
            imageView.clearAnimation();
        });





    }
}