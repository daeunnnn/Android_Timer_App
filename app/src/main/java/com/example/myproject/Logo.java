package com.example.myproject;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Logo extends AppCompatActivity {

    MediaPlayer player;

    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        //미디어 플레이어
        player = MediaPlayer.create(Logo.this, R.raw.last);

        Glide.with(Logo.this).load(R.drawable.logo).into(ivLogo);



        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Logo.this, MainActivity.class);

                startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.

                finish();
            }
        }, 7000); //1초 후 인트로 실행
    }
}