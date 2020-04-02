package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class community2 extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    TextView txt_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community2);

//        핸들러(두번클릭시 종료)
//        backPressCloseHandler = new BackPressCloseHandler(this);



        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

/*
        txt_next = (TextView)findViewById(R.id.textView4);
        txt_next.setText(id);
*/

        Toast.makeText(community2.this, id+"님께서 로그인되셨습니다.", Toast.LENGTH_SHORT).show();


        Button writebutton = (Button) findViewById(R.id.button6);
        Button logoutbutton = (Button) findViewById(R.id.logout);
        Button searchbutton = (Button) findViewById(R.id.bottom_search);
        Button championbutton = (Button) findViewById(R.id.bottom_champion);
        Button rankingbutton = (Button) findViewById(R.id.bottom_ranking);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //첫번째 버튼 행동
                    case R.id.button6:
                        Intent intent_write = new Intent(community2.this, Write.class);
                        startActivity(intent_write);

                        break;

                    case R.id.logout:
                        Intent intent_login = new Intent(community2.this, community.class);
                        startActivity(intent_login);
                        Toast.makeText(community2.this, "로그아웃 되셨습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        break;

                    case R.id.bottom_search:
                        Intent intent_search = new Intent(community2.this, MainActivity.class);
                        startActivity(intent_search);
                        finish();
                        break;

                    case R.id.bottom_champion:
                        Intent intent_champion = new Intent(community2.this, Champion.class);
                        startActivity(intent_champion);
                        finish();
                        break;

                    case R.id.bottom_ranking:
                        Intent intent_ranking = new Intent(community2.this, Ranking.class);
                        startActivity(intent_ranking);
                        finish();
                        break;
                }


            }
        };
        writebutton.setOnClickListener(onClickListener);
        logoutbutton.setOnClickListener(onClickListener);
        searchbutton.setOnClickListener(onClickListener);
        championbutton.setOnClickListener(onClickListener);
        rankingbutton.setOnClickListener(onClickListener);


    }

    //핸들러 두번클릭시 종료
    /*@Override
    public void onBackPressed()
    { //super.onBackPressed();
         backPressCloseHandler.onBackPressed();
    }*/


    }
