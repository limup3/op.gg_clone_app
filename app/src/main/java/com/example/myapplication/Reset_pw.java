package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Reset_pw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw);

        Button Reset_pw_ok_button = (Button)findViewById(R.id.button14);
        Button Reset_pw_cancel_button = (Button)findViewById(R.id.button15);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.button14: {
                        Intent intent_ok = new Intent(Reset_pw.this, community.class);
                        startActivity(intent_ok);
                        break;
                    }
                    case R.id.button15: {
//                        Intent intent_cancel = new Intent(Reset_pw.this, community.class);
//                        startActivity(intent_cancel);
                        onBackPressed();
                        break;
                    }
                }
            }
        };
        Reset_pw_ok_button.setOnClickListener(onClickListener);
        Reset_pw_cancel_button.setOnClickListener(onClickListener);
    }
}
