package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class Champion extends AppCompatActivity {

    private com.example.myapplication.ChampionAdapter adapter;
    private BackPressCloseHandler backPressCloseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion);

//        핸들러(두번클릭시 종료)
        backPressCloseHandler = new BackPressCloseHandler(this);

        init();

        getData();

        Button searchbutton = (Button)findViewById(R.id.bottom_search);
        Button communitybutton = (Button)findViewById(R.id.bottom_community);
        Button rankingbutton = (Button)findViewById(R.id.bottom_ranking);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동

                    case R.id.bottom_search:
                        Intent intent_search = new Intent(Champion.this,MainActivity.class);
                        startActivity(intent_search);
                        finish();
                        break;

                    case R.id.bottom_community:
                        Intent intent_community = new Intent(Champion.this,community.class);
                        startActivity(intent_community);
                        finish();
                        break;


                    case R.id.bottom_ranking:
                        Intent intent_ranking = new Intent(Champion.this, Ranking.class);
                        startActivity(intent_ranking);
                        finish();
                        break;
                }


            }
        };

        searchbutton.setOnClickListener(onClickListener);
        communitybutton.setOnClickListener(onClickListener);
        rankingbutton.setOnClickListener(onClickListener);
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ChampionAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void getData() {
        // 임의의 데이터입니다.


        List<Integer> list_chams1 = Arrays.asList(

                R.drawable.garen,
                R.drawable.cham1,
                R.drawable.cham4,
                R.drawable.balcoz,
                R.drawable.riven,
                R.drawable.garlio,
                R.drawable.cham11,
                R.drawable.cham3,
                R.drawable.garen
        );

        List<Integer> list_chams2 = Arrays.asList(

                R.drawable.garlio,
                R.drawable.cham2,
                R.drawable.cham11,
                R.drawable.braum,
                R.drawable.risan,
                R.drawable.cham3,
                R.drawable.cham11,
                R.drawable.cham1,
                R.drawable.cham6
        );

        List<Integer> list_chams3 = Arrays.asList(
                R.drawable.gula,
                R.drawable.cham3,
                R.drawable.cham5,
                R.drawable.bye,
                R.drawable.cham6,
                R.drawable.cham1,
                R.drawable.gula,
                R.drawable.cham5,
                R.drawable.cham11
        );
        for (int i = 0; i < list_chams1.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Champion_dict data = new Champion_dict();
            data.setChams1(list_chams1.get(i));
            data.setChams2(list_chams2.get(i));
            data.setChams3(list_chams3.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    //핸들러 두번클릭시 종료
    @Override
    public void onBackPressed()
    { //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }


    }



