package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Find_id extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        Button Find_id_cancel_button = (Button)findViewById(R.id.button8);
        Button Find_id_ok_button = (Button)findViewById(R.id.button9);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.button8: {
                        Intent intent_cancel = new Intent(Find_id.this, community.class);
                        startActivity(intent_cancel);
                        finish();
                        break;
                    }
                    case R.id.button9: {
                        Intent intent_ok = new Intent(Find_id.this, MainActivity.class);
                        startActivity(intent_ok);
                        finish();
                        break;
                    }


                }


            }
        };
        Find_id_cancel_button.setOnClickListener(onClickListener);
        Find_id_ok_button.setOnClickListener(onClickListener);


    }

    }

