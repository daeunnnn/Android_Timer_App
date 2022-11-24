package com.example.myproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Egg_Choice extends AppCompatActivity {

    ImageView img_egg_full, img_egg_half;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egg_choice);


        img_egg_full = (ImageView)findViewById(R.id.img_egg_full);
        img_egg_full.setOnClickListener(mClick);

        img_egg_half = (ImageView) findViewById(R.id.img_egg_half);
        img_egg_half.setOnClickListener(mClick);

        //미디어 플레이어
        player = MediaPlayer.create(Egg_Choice.this, R.raw.button_sound);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_egg_full:
                     player.start();
                     Intent ex1 = new Intent(Egg_Choice.this,Egg_Full_Timer.class);
                     startActivity(ex1);
                     Log.d("프레임 전환완료!","완료");
                    break;
                case R.id.img_egg_half:
                    player.start();
                    Intent ex2 = new Intent(Egg_Choice.this,Egg_Half_Timer.class);
                    startActivity(ex2);
                    Log.d("프레임 전환완료!","완료");
                    break;
            }
        }
    };

}