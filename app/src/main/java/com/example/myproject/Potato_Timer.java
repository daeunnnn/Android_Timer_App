package com.example.myproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Potato_Timer extends AppCompatActivity {
    //타이머 텍스트뷰
    TextView tv_potato_timer;

    //타이머 종류 후 실행시킬 gif 이미지뷰
    ImageView iv_potato_timer_finish;

    //프레임
    View frame1_potato_timer, frame2_potato_timer;

    Button btn_potato_start, btn_potato_goback;

    MediaPlayer player, button_click;

    public static String conversionTime = "000010";


    //타이머 제어 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potato);
        //타이머 텍스트뷰
        tv_potato_timer = (TextView) findViewById(R.id.tv_potato_timer);

        //타이머 진행 중 프레임
        frame1_potato_timer = (LinearLayout) findViewById(R.id.frame1_potato_timer);

        //타이머 종료 후 프레임
        frame2_potato_timer = (LinearLayout) findViewById(R.id.frame2_potato_timer);

        //타이머 종료 후 실행시킬 gif
        iv_potato_timer_finish = (ImageView) findViewById(R.id.iv_potato_timer_finish);

        //gif 실행을 위한 Glide
        Glide.with(Potato_Timer.this).load(R.drawable.finish).into(iv_potato_timer_finish);

        //타이머 시작 버튼
        btn_potato_start = (Button)findViewById(R.id.btn_potato_start);
        btn_potato_start.setOnClickListener(mClickListener);

        //홈버튼
        btn_potato_goback = (Button)findViewById(R.id.btn_potato_goback);
        btn_potato_goback.setOnClickListener(mClickListener);

        //미디어 플레이어
        player = MediaPlayer.create(Potato_Timer.this, R.raw.bell);

        //버튼 클릭 효과음
        button_click = MediaPlayer.create(Potato_Timer.this, R.raw.button_sound);

        //countDown(conversionTime); //카운트다운 시작
    }

    //스타트 버튼 이벤트
    View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_potato_start:
                    button_click.start();
                    btn_potato_start.setText("조리 중");
                    countDown(conversionTime); //카운트다운 시작
                    Log.d("타이머", "테스트스타트");
                    break;
                case R.id.btn_potato_goback:
                    button_click.start();
                    Intent gohome = new Intent(Potato_Timer.this,MainActivity.class);
                    startActivity(gohome);
                    player.release();
                    player = null;
                    finish();
                    Log.d("홈으로", "되돌아가기");
                    break;
            }
        }
    };




    //타이머 실행
    public void countDown(String time){
        long conversionTime = 0;
        //1000 단위가 1초
        //60000 단위가 1분
        //6000 * 3600 = 1시간

        String getMin = time.substring(2,4);
        String getSecond = time.substring(4,6);

        //"00"이 아니고 첫번째 자리가 0이면 제거
        if(getMin.substring(0,1) == "0"){
            getMin = getMin.substring(1,2);
        }
        if(getSecond.substring(0,1) == "0"){
            getSecond = getSecond.substring(1,2);
        }

        //변환시간
        conversionTime = Long.valueOf(getMin) * 60 * 1000 + Long.valueOf(getSecond) * 1000;

        //첫번째 인자 : 원하는 시간 (30초면 30 * 1000(주기))
        //두번째 인자 : 주기 (1000 = 1초)
        new CountDownTimer(5500, 1000){
            //특정 시간마다 뷰 변경
            public void onTick(long millisUntilFinished){
                //분단위
                long getMin = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000));
                String min = String.valueOf(getMin / (60 * 1000)); //몫

                //초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000);
                //밀리세컨드 단위
                String millis = String.valueOf((getMin % (60 * 1000)) % 1000);

                //분이 한자리면 0을 붙인다
                if(min.length() == 1){
                    min = "0" + min;
                }
                if(second.length() == 1){
                    second = "0" + second;
                }
                tv_potato_timer.setText(min + ":" + second);
            }

            //제한시간 종료시
            @Override
            public void onFinish() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        frame1_potato_timer.setVisibility(View.INVISIBLE);
                        frame2_potato_timer.setVisibility(View.VISIBLE);
                        player.start();
                        Log.d("프레임 전환완료!","완료");
                    }
                },500);
            }
        }.start();
    }

    @Override
    public void onBackPressed(){
        player.release();
        player = null;
        finish();
    }



}

