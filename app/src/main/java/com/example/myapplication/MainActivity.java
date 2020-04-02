package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class MainActivity extends AppCompatActivity {


    private BackPressCloseHandler backPressCloseHandler;
    private EditText id;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        핸들러(두번클릭시 종료)
        backPressCloseHandler = new BackPressCloseHandler(this);

         id = (EditText)findViewById(R.id.editText5);
        Button searchbutton = (Button)findViewById(R.id.bottom_search);
        Button communitybutton = (Button)findViewById(R.id.bottom_community);
        Button championbutton = (Button)findViewById(R.id.bottom_champion);
        Button rankingbutton = (Button)findViewById(R.id.bottom_ranking);
        ImageButton search_button = (ImageButton)findViewById(R.id.imageButton16);
        ImageButton map = (ImageButton)findViewById(R.id.imageButton20);
        ImageButton weather = (ImageButton)findViewById(R.id.imageButton21);
        imageView = (ImageView)findViewById(R.id.imageView);

        LottieAnimationView animationView = findViewById(R.id.lottieAnimView);
        setUpAnimation(animationView);



//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String api_key ="RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5";
////                new HttpAsyncTask().execute("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+id.getText().toString()+"?api_key="+api_key);
//            }
//        });



        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.imageButton16:
                        Intent intent = new Intent(MainActivity.this,RecordActivity.class);
//                        SharedPreferences sharedPreferences2 = getSharedPreferences("encryptedid", MODE_PRIVATE);
//                        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//                        editor2.clear();
                        intent.putExtra("id",id.getText().toString());
                        startActivity(intent);
                        break;

                    case R.id.imageButton20:
                        Intent intent_map = new Intent(MainActivity.this,map.class);
                        startActivity(intent_map);
                        break;

                    case R.id.imageButton21:
                        Intent intent_weather = new Intent(MainActivity.this,weather.class);
                        startActivity(intent_weather);
                        break;

                    case R.id.bottom_community:
                        Intent intent_community = new Intent(MainActivity.this,community.class);
                        startActivity(intent_community);
//                        SharedPreferences sharedPreferences = getSharedPreferences("community",MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.clear();
//                        editor.apply();
//                        SharedPreferences sharedPreferences2 = getSharedPreferences("write_number",MODE_PRIVATE);
//                        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//                        editor2.clear();
//                        editor2.apply();
//                        SharedPreferences sharedPreferences4 = getSharedPreferences("Login_data", MODE_PRIVATE);
//                        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
//                        editor4.clear();
//                        editor4.apply();
                        SharedPreferences sharedPreferences4 = getSharedPreferences("spfScore", MODE_PRIVATE);
                        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
                        editor4.clear();
                        editor4.apply();
                        finish();
                        break;

                    case R.id.bottom_champion:
                        Intent intent_champion = new Intent(MainActivity.this, Champion.class);
                        startActivity(intent_champion);
                        finish();
                        break;

                    case R.id.bottom_ranking:
                        Intent intent_ranking = new Intent(MainActivity.this, Ranking.class);
                        startActivity(intent_ranking);
                        finish();
                        break;
                }


            }
        };

        communitybutton.setOnClickListener(onClickListener);
        search_button.setOnClickListener(onClickListener);
        championbutton.setOnClickListener(onClickListener);
        rankingbutton.setOnClickListener(onClickListener);
        map.setOnClickListener(onClickListener);
        weather.setOnClickListener(onClickListener);


    }

//    private class HttpAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {
//
//        OkHttpClient client = new OkHttpClient();
//
//        @Override
//        protected ArrayList<String> doInBackground(String... params) {
//            ArrayList<String> chams = new ArrayList<>();
//            String result = null;
//            String strUrl = params[0];
//
//            try {
//                Request request = new Request.Builder()
//                        .url(strUrl)
//                        .build();
//
//                Response response = client.newCall(request).execute();
//
//                JSONObject jsonObject = new JSONObject(response.body().string());
//
//
//
//                String encryptedid = jsonObject.getString("id");
//                String accountId = jsonObject.getString("accountId");
//                Intent intent = new Intent(MainActivity.this,RecordActivity.class);
//
//                SharedPreferences sharedPreferences = getSharedPreferences("encryptedid", MODE_PRIVATE);
//                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("ID", encryptedid);
//                editor.putString("accountId",accountId);
//                editor.apply();
//
//
//                chams.add(accountId);
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return chams;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList s) {
//            super.onPostExecute(s);
//
//
//        }
//    }



    private void setUpAnimation(LottieAnimationView animationView) {
        // 재생할 애니메이션 넣어준다.
        animationView.setAnimation("10979-reaper.json");
        // 반복횟수를 무한히 주고 싶을 땐 LottieDrawable.INFINITE or 원하는 횟수
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        // 시작
        animationView.playAnimation();
    }


    //핸들러 두번클릭시 종료
    @Override
    public void onBackPressed()
    { //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}
