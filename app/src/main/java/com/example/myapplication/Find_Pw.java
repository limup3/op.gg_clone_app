package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Find_Pw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        Button Find_pw_ok_button = (Button)findViewById(R.id.button10);
        Button Find_pw_cancel_button = (Button)findViewById(R.id.button11);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.button10: {
                        Intent intent_ok = new Intent(Find_Pw.this, Reset_pw.class);
                        startActivity(intent_ok);
                        finish();
                        break;
                    }
                    case R.id.button11: {
                        Intent intent_cancel = new Intent(Find_Pw.this, community.class);
                        startActivity(intent_cancel);
                        finish();
                        break;
                    }



                }


            }
        };
        Find_pw_ok_button.setOnClickListener(onClickListener);
        Find_pw_cancel_button.setOnClickListener(onClickListener);
    }
}
