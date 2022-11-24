package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button btn_potato, btn_egg;
    Button btn_noodles, btn_pasta, btn_sweetPotato;
    MediaPlayer button_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //감자
        btn_potato = (Button)findViewById(R.id.btn_potato);
        btn_potato.setOnClickListener(mClick);

        //삶은 달걀
        btn_egg = (Button)findViewById(R.id.btn_egg);
        btn_egg.setOnClickListener(mClick);

        //소면
        btn_noodles = (Button)findViewById(R.id.btn_noodles);
        btn_noodles.setOnClickListener(mClick);

        //파스타
        btn_pasta = (Button)findViewById(R.id.btn_pasta);
        btn_pasta.setOnClickListener(mClick);

        //고구마
        btn_sweetPotato = (Button)findViewById(R.id.btn_sweetPotato);
        btn_sweetPotato.setOnClickListener(mClick);

        //버튼 클릭 효과음
        button_click = MediaPlayer.create(MainActivity.this, R.raw.button_sound);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_egg:
                    button_click.start();
                    Intent egg = new Intent(MainActivity.this,Egg_Choice.class);
                    startActivity(egg);
                    break;
                case R.id.btn_potato:
                    button_click.start();
                    Intent potato = new Intent(MainActivity.this,Potato_Timer.class);
                    startActivity(potato);
                    break;
                case R.id.btn_noodles:
                    button_click.start();
                    Intent noodles = new Intent(MainActivity.this,Noodles_Timer.class);
                    startActivity(noodles);
                    break;
                case R.id.btn_pasta:
                    button_click.start();
                    Intent pasta = new Intent(MainActivity.this,Pasta_Timer.class);
                    startActivity(pasta);
                    break;
                case R.id.btn_sweetPotato:
                    button_click.start();
                    Intent sweetPotato = new Intent(MainActivity.this,SweetPotato_Timer.class);
                    startActivity(sweetPotato);
                    break;
            }
        }
    };
}