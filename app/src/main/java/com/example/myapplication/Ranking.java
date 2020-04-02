package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Ranking extends AppCompatActivity {

    private com.example.myapplication.RankingAdapter adapter;
    private BackPressCloseHandler backPressCloseHandler;
    private int i = 0;
    private  ImageButton minigame;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
//        핸들러(두번클릭시 종료)
        backPressCloseHandler = new BackPressCloseHandler(this);

        init();



//        https://kr.api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/CHALLENGER/I?page=1&api_key=RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5

        minigame = (ImageButton) findViewById(R.id.minigame);
        Button searchbutton = (Button)findViewById(R.id.bottom_search);
        Button communitybutton = (Button)findViewById(R.id.bottom_community);
        Button championbutton = (Button)findViewById(R.id.bottom_champion);
        Button rankingbutton = (Button)findViewById(R.id.bottom_ranking);

        new HttpAsyncTask().execute("https://kr.api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/CHALLENGER/I?page=1&api_key=RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5");

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동

                    case R.id.bottom_search:
                        Intent intent_search = new Intent(Ranking.this,MainActivity.class);
                        startActivity(intent_search);
                        finish();
                        break;

                    case R.id.bottom_community:
                        Intent intent_community = new Intent(Ranking.this,community.class);
                        startActivity(intent_community);
                        finish();
                        break;

                    case R.id.bottom_champion:
                        Intent intent_champion = new Intent(Ranking.this, Champion.class);
                        startActivity(intent_champion);
                        finish();
                        break;

                    case R.id.minigame:
                        Intent intent_minigame = new Intent(Ranking.this, game.class);
                        startActivity(intent_minigame);
                        finish();
                        break;
                }


            }
        };

        searchbutton.setOnClickListener(onClickListener);
        communitybutton.setOnClickListener(onClickListener);
        championbutton.setOnClickListener(onClickListener);
        minigame.setOnClickListener(onClickListener);
    }

        private class HttpAsyncTask extends AsyncTask<String, String, ArrayList<String>> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> chams = new ArrayList<>();
            String result = null;
            String strUrl = params[0];

            try {




                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();

                Response response = client.newCall(request).execute();

                JSONArray jsonArray = new JSONArray(response.body().string());
                for(int i = 0 ; i<jsonArray.length(); i++)
                {

                    List<Integer> listscore_icon = Arrays.asList(R.drawable.sc_ic,R.drawable.challenger);

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String summonerName = jsonObject.getString("summonerName");
                    String leaguePoints = jsonObject.getString("leaguePoints");
                    String wins = jsonObject.getString("wins");
                    String losses = jsonObject.getString("losses");

                    chams.add(summonerName);
                    chams.add(leaguePoints);
                    chams.add(wins);
                    chams.add(losses);

                    Ranking_dict data = new Ranking_dict(chams.get(0),String.valueOf(i+1),chams.get(1),chams.get(2)+"승 / "+chams.get(3)+"패",listscore_icon.get(1),listscore_icon.get(0));

                    publishProgress();
                    adapter.addItem(data);
                    chams.clear();
                }





            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return chams;
        }
            @Override
            protected void onProgressUpdate(String... progress) {
                super.onProgressUpdate();
                adapter.notifyDataSetChanged();
            }
        @Override
        protected void onPostExecute(ArrayList s) {
            super.onPostExecute(s);


        }
    }




    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RankingAdapter();
        recyclerView.setAdapter(adapter);
    }

//    private void getData() {
//        // 임의의 데이터입니다.
//        List<String> listSummoner = Arrays.asList("ccccco", "오리지널찰떡쿠키", "Gen G Ruler", "SKT T1 clid", "률드컵", "kanavi", "SKT T1 Gumayusl", "adad0","faker","ruru","riding","zozo","kaka"
//                );
//        List<String> listScore = Arrays.asList(
//                "1,470 LP",
//                "1,407 LP",
//                "1,430 LP",
//                "1,384 LP",
//                "1,367 LP",
//                "1,318 LP",
//                "1,292 LP",
//                "1,286 LP",
//                "1,280 LP",
//                "1,277 LP",
//                "1,272 LP",
//                "1,265 LP",
//                "1,260 LP"
//
//        );
//
//
//        List<String> listNumber = Arrays.asList(
//
//
//                "1",
//                "2",
//                "3",
//                "4",
//                "5",
//                "6",
//                "7",
//                "8",
//                "9",
//                "10",
//                "11",
//                "12",
//                "13"
//        );
//
//        List<Integer> listscore_icon = Arrays.asList(
//
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic,
//                R.drawable.sc_ic
//        );
//        List<Integer> listicon = Arrays.asList(
//                R.drawable.su_ic,
//                R.drawable.su_ic2,
//                R.drawable.su_ic3,
//                R.drawable.su_ic4,
//                R.drawable.su_ic5,
//                R.drawable.su_ic6,
//                R.drawable.su_ic7,
//                R.drawable.su_ic8,
//                R.drawable.su_ic5,
//                R.drawable.su_ic3,
//                R.drawable.su_ic6,
//                R.drawable.su_ic,
//                R.drawable.su_ic2
//        );
//        for (int i = 0; i < listSummoner.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            Ranking_dict data = new Ranking_dict();
//            data.setSummoner(listSummoner.get(i));
//            data.setRnak_number(listNumber.get(i));
//            data.setScore(listScore.get(i));
//            data.setIcon(listicon.get(i));
//            data.setScore_icon(listscore_icon.get(i));
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//    }

    //핸들러 두번클릭시 종료
    @Override
    public void onBackPressed()
    { //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();
    }

    private void updateThread() {

        int mod = i % 3;

        switch (mod) {
            case 0:
                i++;
                minigame.setImageResource(R.drawable.teemo1);
                break;
            case 1:
                i++;
                minigame.setImageResource(R.drawable.teemo1);
                break;
            case 2:
                i++;
                minigame.setImageResource(R.drawable.teemo2);
                break;

        }
    }
}



