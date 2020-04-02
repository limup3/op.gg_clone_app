package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class game_result extends AppCompatActivity {

    TextView sub_result ;
    ImageButton sub_retry;
    ImageButton sub_home;
    SharedPreferences spf = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);


        spf = getSharedPreferences("spfScore",MODE_PRIVATE); // 키값이 또 있으면 덮어쓰겠다

        sub_result = findViewById(R.id.sub_result);
        sub_retry = findViewById(R.id.sub_retry);
        sub_home = findViewById(R.id.sub_home);

        int score = getIntent().getIntExtra("score", -1);
        sub_result.setText("터트린 개수 :"+String.valueOf(score));
        ImageView icon = (ImageView) findViewById(R.id.gif_image2);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(icon);
        Glide.with(this).load(R.drawable.teemo4).into(gifImage);


        if(spf.getInt("spfscore",0) < score){ //내점수가 저번 점수보다 크면
            spf.edit().putInt("spfscore",score).commit(); //반영의 commit(). 현재상태 저장
            Glide.with(this).load(R.drawable.teemo3).into(gifImage);
            sub_result.setText("신기록달성\n"+score);
        }




        sub_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_result.this, game.class);
                startActivity(intent);
                finish();
            }
        });


        sub_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}