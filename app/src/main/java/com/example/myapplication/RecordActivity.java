package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RecordActivity extends AppCompatActivity {

    private com.example.myapplication.RecordAdapter adapter;
    private ImageView profileicon;
    private ImageView tier_icon;
    private ImageView free_tier_icon;
    private TextView s_name;
    private TextView s_level;
    private TextView rank_type;
    private TextView tier;
    private TextView point;
    private TextView Totally;
    private TextView free_rank_type;
    private TextView free_tier;
    private TextView free_point;
    private TextView free_Totally;
    private List<String> chams = new ArrayList<String>();
    private ArrayList<Record_dict> listdata;
    private FrameLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

//        final String API_KEY = "RGAPI-ace5435e-d461-43ed-a56b-b0e7d0f733be"; // API키
//        String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ SummonerName +"?api_key="+API_KEY; //소환사 검색

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        SharedPreferences sharedPreferences = getSharedPreferences("encryptedid", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String e_id = sharedPreferences.getString("ID","");
//        String accountId = sharedPreferences.getString("accountId","");
        Log.d("확인",e_id);
//        Log.d("확인2",accountId);
        String api_key ="RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5";


//        getData();

        mLayout = (FrameLayout)findViewById(R.id.frameLayout2);
        Button searchbutton = (Button)findViewById(R.id.bottom_search);
        Button communitybutton = (Button)findViewById(R.id.bottom_community);
        Button championbutton = (Button)findViewById(R.id.bottom_champion);
        Button rankingbutton = (Button)findViewById(R.id.bottom_ranking);
        profileicon = (ImageView)findViewById(R.id.imageView14);
        tier_icon = (ImageView)findViewById(R.id.tier_icon);
        free_tier_icon = (ImageView)findViewById(R.id.free_tier_icon);
        s_name = (TextView)findViewById(R.id.textView21);
        s_level = (TextView)findViewById(R.id.textView32);
        rank_type = (TextView)findViewById(R.id.rank_type);
        tier = (TextView)findViewById(R.id.tier);
        point = (TextView)findViewById(R.id.point);
        Totally = (TextView)findViewById(R.id.Totally);
        free_rank_type = (TextView)findViewById(R.id.free_rank_type);
        free_tier = (TextView)findViewById(R.id.free_tier);
        free_point = (TextView)findViewById(R.id.free_point);
        free_Totally = (TextView)findViewById(R.id.free_Totally);
        init();
        new HttpAsyncTask().execute("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+id+"?api_key="+api_key);
        new HttpAsyncTask2().execute("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+id+"?api_key="+api_key);

        editor.clear();

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        OkHttpClient client = new OkHttpClient();

        @SuppressLint("WrongThread")
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> chams = new ArrayList<>();
            ArrayList<String> ac_id = new ArrayList<>();
            String result = null;
            String strUrl = params[0]; // api 소환사 정보


//            SharedPreferences sharedPreferences = getSharedPreferences("encryptedid", MODE_PRIVATE);
//            String accountId = sharedPreferences.getString("accountId", "");
            String api_key = "RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5";
            Intent intent = getIntent();
            String id = intent.getStringExtra("id");

            Bitmap bitmap = null;
            InputStream in = null;

            Bitmap bitmap2 = null;
            InputStream in2 = null;

            Bitmap bitmap3 = null;
            InputStream in3 = null;


            try {
                Request request = new Request.Builder() // api 소환사 정보
                        .url(strUrl)
                        .build();

                Response response = client.newCall(request).execute(); // api 소환사 정보

Log.d("코드", String.valueOf(response.code()));

//                if(!(response.code() == 200))
//                {
//                    onCancelled();
////                   onBackPressed();
//                }

                JSONObject jsonObject = new JSONObject(response.body().string());

                String name = jsonObject.getString("name"); // 닉네임 받아오기

                String summonerLevel = jsonObject.getString("summonerLevel"); // 레벨 받아오기

                String icon = jsonObject.getString("profileIconId"); // 프로필 아이콘 받아오기

                String accountid = jsonObject.getString("accountId"); // 프로필 아이콘 받아오기

                String summonerid = jsonObject.getString("id"); // 프로필 아이콘 받아오기


//                SharedPreferences sharedPreferences2 = getSharedPreferences("encryptedid", MODE_PRIVATE);
                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
//                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//                editor2.putString("accountId",accountid);
//                editor2.apply();
                String imageUrl = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/profileicon/" + icon + ".png"; // 프로필 아이콘 URl

                String error_imageUrl = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/profileicon/29.png"; // 프로필 아이콘 없거나 문제시 대체아이콘

                chams.add(0, name); // 0
                chams.add(1, summonerLevel); // 1
                ac_id.add(summonerid);
                ac_id.add(imageUrl);
                ac_id.add(error_imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            try {
//                if(ac_id.size() == 0)
//                {
//                    onCancelled();
//                }

                String strUrl2 = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + ac_id.get(0) + "?api_key=" + api_key; // api 소환사 티어 정보
                Request request2 = new Request.Builder() // api 소환사 티어정보
                        .url(strUrl2)
                        .build();

                Response response2 = client.newCall(request2).execute(); // api 소환사 티어정보
//                if(!(response2.code() == 200))
//                {
//                    onCancelled();
////                   onBackPressed();
//                }
                JSONArray jsonArray = new JSONArray(response2.body().string());
                JSONObject solo = jsonArray.getJSONObject(0);

                String tier = solo.getString("tier"); // 티어
                String win = solo.getString("wins");
                String loss = solo.getString("losses");
                String point = solo.getString("leaguePoints");
                String rank = solo.getString("rank");

                chams.add(2, tier);
                chams.add(3, rank);
                chams.add(4, win);
                chams.add(5, loss);
                chams.add(6, point);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }



            try {
                String tierUrl = "http://z.fow.kr/img/emblem/" + chams.get(3).toLowerCase() + ".png";
//                String free_tierUrl = "http://z.fow.kr/img/emblem/"+free_tier.toLowerCase()+".png";
                String imageUrl = ac_id.get(1);

                in = new java.net.URL(imageUrl).openStream();//아이콘
                bitmap = BitmapFactory.decodeStream(in);//아이콘

//                    in2 = new java.net.URL(tierUrl).openStream();//솔랭 티어 아이콘
//                    bitmap2 = BitmapFactory.decodeStream(in2);//솔랭 티어 아이콘
//
//                    in3 = new java.net.URL(free_tierUrl).openStream();//자랭 티어 아이콘
//                    bitmap3 = BitmapFactory.decodeStream(in3);//자랭 티어 아이콘
            } catch (Exception e) {
                if (in == null) {

                    try {
                        String error_imageUrl = ac_id.get(2);
                        in = new URL(error_imageUrl).openStream();//아이콘없을떄
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    bitmap = BitmapFactory.decodeStream(in);//아이콘없을떄
                }
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (in2 != null) {
                        in2.close();
                    }
                    if (in3 != null) {
                        in3.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }



try {
    String imageUrl2 = getBase64String(bitmap);
    chams.add(7, imageUrl2); // 2
}catch (Exception e){
    e.printStackTrace();
}

//                String tierUrl2 = getBase64String(bitmap2);
//                String free_tierUrl2 = getBase64String(bitmap3);




//                chams.add(8,free_tier); // 자유랭크에 대한 리스트추가
//                chams.add(9,free_rank);
//                chams.add(10,free_win);
//                chams.add(11,free_loss);
//                chams.add(12,free_point);
//                chams.add(13,tierUrl2);
//                chams.add(14,free_tierUrl2);



            return chams;

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            finish();
//            Toast.makeText(RecordActivity.this, "아이디를 잘못입력하셨습니다.", Toast.LENGTH_SHORT).show();
        }

            @Override
        protected void onPostExecute(ArrayList s) {
            try {
                super.onPostExecute(s);



                s_name.setText((CharSequence) s.get(0));
                s_level.setText((CharSequence) s.get(1));
                Log.d("인덱스", String.valueOf(s.get(2)));
                Log.d("인덱스2", String.valueOf(s.get(3)));
                Log.d("인덱스3", String.valueOf(s.get(4)));
                tier.setText((CharSequence) s.get(2) +" "+ s.get(3));
                Totally.setText(s.get(4)+"승 / "+s.get(5)+"패");
                point.setText(s.get(6)+ "LP");
//            free_tier.setText(s.get(8)+" "+s.get(9));
//            free_Totally.setText(s.get(10)+"승 / "+s.get(11)+"패");
//            free_point.setText(s.get(12)+ "LP");


                byte[] decodedByteArray = Base64.decode(String.valueOf(s.get(7)), Base64.NO_WRAP);
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                profileicon.setImageBitmap(decodedBitmap);

//            String tier = String.valueOf(s.get(3));
//            String free_tier = String.valueOf(s.get(8));

                int tier = getResources().getIdentifier(String.valueOf(s.get(2)).toLowerCase(),"drawable",getPackageName());
                tier_icon.setImageResource(tier);
//            int free_tier = getResources().getIdentifier(String.valueOf(s.get(8)).toLowerCase(),"drawable",getPackageName());
//            free_tier_icon.setImageResource(free_tier);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------

    private class HttpAsyncTask2 extends AsyncTask<String, String, ArrayList<String>> {

        OkHttpClient client = new OkHttpClient();
        ProgressDialog asyncDialog = new ProgressDialog(RecordActivity.this);

        //onPreExecute 함수는 이름대로 excute()로 실행 시 doInBackground() 실행 전에 호출되는 함수
        //여기서 ProgressDialog 생성 및 기본 세팅하고 show()
        @Override

        protected void onPreExecute() {

            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            asyncDialog.setMessage("전적 검색 중 입니다...");



            // show dialog

            asyncDialog.show();

            super.onPreExecute();

        }


        @SuppressLint("WrongThread")
        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> ac_id = new ArrayList<>();
            ArrayList<String> match = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            ArrayList<String> url = new ArrayList<>();
            ArrayList<String> stat = new ArrayList<>();
            ArrayList<String> picture = new ArrayList<>();
            ArrayList<String> doin = new ArrayList<>();
            ArrayList<Bitmap> decode = new ArrayList<>();
            Exception error;

            for (int i = 0; i < 5; i++) {

                asyncDialog.setProgress(i * 30);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }





            SharedPreferences sharedPreferences = getSharedPreferences("encryptedid", MODE_PRIVATE);
            String accountId = sharedPreferences.getString("accountId", "");
            String api_key = "RGAPI-5b10b84f-bfd0-4123-85af-d69ee556e4d5";
            Intent intent = getIntent();
            String id = intent.getStringExtra("id");

            String strUrl = params[0]; // api 소환사 정보
            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();

                Response response = client.newCall(request).execute();
//                if(!(response.code() == 200))
//                {
//                    onCancelled();
////                   onBackPressed();
//                }
                JSONObject jsonObject = new JSONObject(response.body().string());
                String accountid = jsonObject.getString("accountId"); // 프로필 아이콘 받아오기
                ac_id.add(accountid);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            if(ac_id.size() == 0)
//            {
//                onCancelled();
//            }



            try {
                String strUrl2 = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+ac_id.get(0)+"?endIndex=20&beginIndex=0&api_key="+api_key;
                Request request2 = new Request.Builder()
                        .url(strUrl2)
                        .build();


                Response response2 = client.newCall(request2).execute();
//                if(!(response2.code() == 200))
//                {
//                    onCancelled();
////                   onBackPressed();
//                }
                JSONObject parse = new JSONObject(response2.body().string());
//                JSONObject parse2 = parse.getJSONObject("matches");
                JSONArray matches = parse.getJSONArray("matches");
                for (int i = 0 ; i< matches.length(); i++)
                {
                    JSONObject jsonObject2 = matches.getJSONObject(i);
                    String gameid = jsonObject2.getString("gameId");
                    match.add(gameid);
                }

                Log.d("확인3", String.valueOf(matches));
                Log.d("확인4", String.valueOf(match));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            for(int i = 0 ; i<9; i++) {

//            publishProgress("완료", String.valueOf(i));
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }



                try {
                    String matchurl = "https://kr.api.riotgames.com/lol/match/v4/matches/" + match.get(i) + "?api_key=" + api_key;
                    Request request2 = new Request.Builder()
                            .url(matchurl)
                            .build();
                    Response response2 = client.newCall(request2).execute();


                    JSONObject total = new JSONObject(response2.body().string());
                    JSONArray participantIdentities = total.getJSONArray("participantIdentities");
                    for (int j = 0 ; j< participantIdentities.length(); j++)
                    {
                        JSONObject jsonObject = participantIdentities.getJSONObject(j);
                        JSONObject player = jsonObject.getJSONObject("player");
                        String summonerName = player.getString("summonerName");
                        name.add(summonerName);
                    }

                    int no = name.indexOf(id);
                    name.clear();

                JSONArray participants = total.getJSONArray("participants");
                for(int k = 0; k<participants.length(); k++)
                {
                    JSONObject jsonObject = participants.getJSONObject(k);
                    if(k == no)
                    {
                        String spell1id = jsonObject.getString("spell1Id"); // 첫번째 스펠 (점화 점멸 ..)
                        String spell2id = jsonObject.getString("spell2Id"); // 두번째 스펠 (힐 유체화 ..)
                        String championId = jsonObject.getString("championId"); // 챔피언아이디 파싱
                        JSONObject stats = jsonObject.getJSONObject("stats"); // 통계 파싱
                        String item0 = stats.getString("item0"); //아이템0번쨰
                        String item1 = stats.getString("item1");
                        String item2 = stats.getString("item2");
                        String item3 = stats.getString("item3");
                        String item4 = stats.getString("item4");
                        String item5 = stats.getString("item5");
                        String item6 = stats.getString("item6");
                        String perk0 = stats.getString("perk0");
                        String perkSubStyle = stats.getString("perkSubStyle");//두번쨰특성
                        String kills = stats.getString("kills"); // 킬
                        String deaths = stats.getString("deaths"); // 데스
                        String assists = stats.getString("assists"); // 어시스트
                        String win = stats.getString("win"); // 승리여부 ( true/false)
                        String result = "";
                        if(win == "true")
                        {
                            result = "승리";
                        }
                        if(win == "false")
                        {
                            result = "패배";
                        }

                        float kills2 = Float.parseFloat(kills);
                        float deaths2 = Float.parseFloat(deaths);
                        float assists2 = Float.parseFloat(assists);
                        float kda = (kills2+assists2)/deaths2;

                        String kda2 = String.format("%.2f",kda);
                        Log.d("확인5",kda2);

                        stat.add(kills);
                        stat.add(deaths);
                        stat.add(assists);
                        stat.add(result);
                        stat.add(kda2);



                        String spell1_url = "http://z.fow.kr/spell/"+spell1id+".png";
                        String spell2_url = "http://z.fow.kr/spell/"+spell2id+".png";
                        String chamsid_url = "http://z.fow.kr/champ/"+championId+"_64.png";
                        String item0_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item0+".png";
                        String item1_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item1+".png";
                        String item2_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item2+".png";
                        String item3_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item3+".png";
                        String item4_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item4+".png";
                        String item5_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item5+".png";
                        String item6_url = "http://ddragon.leagueoflegends.com/cdn/9.4.1/img/item/"+item6+".png";
                        String perk0_url = "http://opgg-static.akamaized.net/images/lol/perk/"+perk0+".png";
                        String perkSubStyle_url = "http://opgg-static.akamaized.net/images/lol/perkStyle/"+perkSubStyle+".png";

                        url.add(0,spell1_url);
                        url.add(1,spell2_url);
                        url.add(2,chamsid_url);
                        url.add(3,item0_url);
                        url.add(4,item1_url);
                        url.add(5,item2_url);
                        url.add(6,item3_url);
                        url.add(7,item4_url);
                        url.add(8,item5_url);
                        url.add(9,item6_url);
                        url.add(10,perk0_url);
                        url.add(11,perkSubStyle_url);
                    }
                }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }


            Bitmap bitmap = null;
            InputStream in = null;
            String error_imageUrl = "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/29.png"; // 프로필 아이콘 없거나 문제시 대체아이콘
            for(int p = 0 ; p<url.size() ; p++)
            {

            try {
                in = new URL(url.get(p)).openStream();//아이콘
                bitmap = BitmapFactory.decodeStream(in);//아이콘

            } catch (Exception e) {
                if (in == null) {
                    try {
                        in = new URL(error_imageUrl).openStream();//아이콘없을떄
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    bitmap = BitmapFactory.decodeStream(in);//아이콘없을떄
                }
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

                 picture.add(getBase64String(bitmap));
                byte[] decodedByteArray = Base64.decode(String.valueOf(picture.get(p)), Base64.NO_WRAP);
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                decode.add(decodedBitmap);
        }
            try {
    Record_dict data = new Record_dict(stat.get(3),stat.get(0) + " / " +stat.get(1)+" / "+stat.get(2),stat.get(4)+ " 평점",decode.get(2),decode.get(0),decode.get(1),decode.get(10),decode.get(11),decode.get(3),
            decode.get(4),decode.get(5),decode.get(6),decode.get(7),decode.get(8),decode.get(9));
    adapter.addItem(data);
            }catch (Exception e)
            {
                e.printStackTrace();
            }



//            data.setWin(stat.get(3)); // 승리여부
//            data.setKda(stat.get(0) + " / " +stat.get(1)+" / "+stat.get(2));
//            data.setKill_per(stat.get(4)+ " 평점");
//            data.setSpell1(decode.get(0));
//            data.setSpell2(decode.get(1));
//            data.setSummer_icon(decode.get(2));
//            data.setItem1(decode.get(3));
//            data.setItem2(decode.get(4));
//            data.setItem3(decode.get(5));
//            data.setItem4(decode.get(6));
//            data.setItem5(decode.get(7));
//            data.setItem6(decode.get(8));
//            data.setItem7(decode.get(9));
//            data.setChar1(decode.get(10));
//            data.setChar2(decode.get(11));




//                Log.d("테스트",String.valueOf(adapter.addItem(data)));

//                listdata.add(data);
                publishProgress();

            stat.clear();
            url.clear();
            picture.clear();
            decode.clear();

            Log.d("확인6", String.valueOf(SystemClock.uptimeMillis()));//현재 시간 측정

            }
            return match;
        }

        //onProgressUpdate() 함수는 publishProgress() 함수로 넘겨준 데이터들을 받아옴
        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate();
            adapter.notifyDataSetChanged();
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();


//            Toast.makeText(RecordActivity.this, "아이디를 잘못입력하셨습니다.", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            asyncDialog.dismiss();
            if(s.size() == 0)
            {
                Toast.makeText(RecordActivity.this, "아이디를 잘못입력하셨습니다.", Toast.LENGTH_LONG).show();
            }
//            adapter.notifyDataSetChanged();
        }
    }




//    private class DrawUrlImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView imageView14;
//
//        public DrawUrlImageTask(ImageView imageView14) {
//            this.imageView14 = imageView14;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String url = urls[0];
//            Bitmap bitmap = null;
//            InputStream in = null;
//
//            try {
//                in = new java.net.URL(url).openStream();
//                bitmap = BitmapFactory.decodeStream(in);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    in.close();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return bitmap;
//        }
//
//        protected void onPostExecute(Bitmap bitmap) {
//            imageView14.setImageBitmap(bitmap);
//        }
//    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView7);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


//        listdata = new ArrayList<>();
        adapter = new RecordAdapter();

        recyclerView.setAdapter(adapter);
    }


    public String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

