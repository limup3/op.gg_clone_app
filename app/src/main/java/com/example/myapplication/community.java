package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class community extends AppCompatActivity {




    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    String formatDate2 = formatDate.substring(2,16);


    private BackPressCloseHandler backPressCloseHandler;
    private ArrayList<Dictionary> mArrayList;
    private com.example.myapplication.CustomAdapter mAdapter;
    private static int count= 0;
    private Gson gson;
    private TextView Show_Time_TextView;
    private ImageView imageView;
    private ImageButton mImageButtonFlashOnOff;
    private boolean mFlashOn;

    private CameraManager mCameraManager;
    private String mCameraId;
    private int i = 0;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };



//    private static class TIME_MAXIMUM {
//        public static final int SEC = 60;
//        public static final int MIN = 60;
//        public static final int HOUR = 24;
//        public static final int DAY = 30;
//        public static final int MONTH = 12;
//    } Time클래스의 시간계산
//    protected static final String write_list = "write_list";
//    private String strContact;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        //광고이미지
        imageView = (ImageView) findViewById(R.id.imageView3);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(getApplicationContext(), "There is no camera flash.\n The app will finish!", Toast.LENGTH_LONG).show();

            delayedFinish();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        }

        mImageButtonFlashOnOff = findViewById(R.id.ibFlashOnOff);
        mImageButtonFlashOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashlight();
                mImageButtonFlashOnOff.setImageResource(mFlashOn ? R.drawable.light2 : R.drawable.light);
            }
        });




//        핸들러(두번클릭시 종료)
                backPressCloseHandler = new BackPressCloseHandler(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(community.this);

        View view = LayoutInflater.from(community.this)
                .inflate(R.layout.community_list, null, false);
        builder.setView(view);


        ImageButton writebutton = (ImageButton) findViewById(R.id.imageButton17);
        ImageButton loginbutton = (ImageButton) findViewById(R.id.imageButton18);
//        Button writebutton = (Button) findViewById(R.id.button6);
//        Button loginbutton = (Button) findViewById(R.id.button3);
        Button searchbutton = (Button) findViewById(R.id.bottom_search);
        Button championbutton = (Button) findViewById(R.id.bottom_champion);
        Button rankingbutton = (Button) findViewById(R.id.bottom_ranking);
//        final EditText search = findViewById(R.id.editText4);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);



        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter( mArrayList);
//        mAdapter = new CustomAdapter( this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

//
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                String text = search.getText().toString().toLowerCase(Locale.getDefault());
//                mAdapter.filter(text);
//
//            }
//        });  // 검색기능추가







//        if (json2 != null)
//        {
//            try
//            {
//                JSONObject jsonObject = new JSONObject(json2);
//
//
//                mArrayList.clear();
//                for (int i = 0; i < array.length(); i++)
//                {
//
//
////                    String url = array.optString(i);
////                    mArrayList.add(url);
//                }
//            }
//            catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//        }

//        SharedPreferences sharedPreferences = getSharedPreferences("comment",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//
//
//        Map<String,?> keys = sharedPreferences.getAll();
//        ArrayList<String> test = new ArrayList<String>();
//
//        for(Map.Entry<String,?> entry : keys.entrySet()){
//            Log.d("map values",entry.getKey() + ": " +
//                    entry.getValue().toString());
//            String Key2 = entry.getKey();
//            String Value2 = entry.getValue().toString();
//            Log.d("map value",Key2);
//
//            test.add(Key2);
//        }
//
//        for(int i= 0; i<test.size(); i++ )
//        {
//            Log.d("position.size",String.valueOf(test.size()));
//
//            Gson gson = new Gson();
//            String json2 = sharedPreferences.getString(test.get(i),"");
//
////            Type listType = new TypeToken<ArrayList<ArrayList<Comment_dict>>>() {}.getType();
////
////            ArrayList<ArrayList<Comment_dict>> dict2 = gson.fromJson(json2,listType);
////
//            Type listType = new TypeToken<ArrayList<Comment_dict>>() {}.getType();
////
//            ArrayList<Comment_dict> dict2 = gson.fromJson(json2,listType);
////
////            Comment_dict dict2 = gson.fromJson(json2, Comment_dict.class);
////            Log.d("어레이", String.valueOf(dict2));
////            cArrayList = new ArrayList<>();
////            cArrayList2 = new ArrayList<ArrayList<Comment_dict>>();
////            cArrayList3.add(dict2);
////            cArrayList2.add(cArrayList);
////            cArrayList.add(dict2);
//            cArrayList.add(dict2.get(i));
//            Log.d("데이터전송", String.valueOf(dict2.get(i)));
////            Log.d("데이터전송1", String.valueOf(dict2.get(0)));
////            Log.d("데이터전송2", String.valueOf(dict2.get(1)));
////            Log.d("데이터전송3", String.valueOf(dict2.get(2)));
//
////            cArrayList.add(dict2);
//
//
//            Log.d("어레이1", String.valueOf(cArrayList));
//            Log.d("어레이2", String.valueOf(cArrayList2));
//            Log.d("어레이3", String.valueOf(cArrayList3));
//            Log.d("dict2", String.valueOf(dict2));
//            Log.d("json2",String.valueOf(json2));
//            editor.apply();
//            mAdapter.notifyDataSetChanged(); // 댓글불러오기
//
//
//
//검색과 댓글 불러오는 주석


        SharedPreferences sp2 = getSharedPreferences("write_number", MODE_PRIVATE);
        String number = sp2.getString("number","");
        Log.d("숫자",number);
        Log.d("숫자2", String.valueOf(count = Integer.parseInt(number)));

            if(count == 0)
            {
                count = Integer.parseInt(number);
            }

        //글번호 저장하는부분 카운트가 0이 되면 쉐어드에 저장된 글번호로 교체




        SharedPreferences sharedPreferences = getSharedPreferences("community",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        Map<String,?> keys = sharedPreferences.getAll();
        ArrayList<String> test = new ArrayList<String>();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            String Key2 = entry.getKey();
            String Value2 = entry.getValue().toString();
            Log.d("map value",Key2);

            test.add(Key2);
        }

        for(int i= 0; i<test.size(); i++ )
        {
            Log.d("position.size",String.valueOf(test.size()));

            Gson gson = new Gson();
            String json2 = sharedPreferences.getString(test.get(i),"");
            Dictionary data2 = gson.fromJson(json2, Dictionary.class);
            mArrayList.add(data2);

            Collections.sort(mArrayList);
            editor.apply();
            mAdapter.notifyDataSetChanged();

        }
        Log.d("어레이리스트",String.valueOf(mArrayList.size()));

//        Gson gson = new Gson();
//
//        String json2 = sharedPreferences.getString("write_number","");
//
//        Dictionary data2 = gson.fromJson(json2, Dictionary.class);
//
//        mArrayList.add(data2);

        //gson을 사용하는 이유 ?
        //저장할때 생성자의 객체를 배열 형태로 저장하고
        //불러올때 객체로 된 배열 형태를 Dictionary 형태로 변환하여 어레이리스트에 삽입하기위해
        //생성자를 만들어줘도 같은 널 오브젝트 레퍼런스 에러가 뜬다.





        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Dictionary dict = mArrayList.get(position);

                Intent intent = new Intent(getBaseContext(), Write_List.class);
                intent.putExtra("title", dict.getTitle());
                intent.putExtra("time", dict.getTime());
                intent.putExtra("id", dict.getId());
                intent.putExtra("category", dict.getCategory());
                intent.putExtra("contents", dict.getContents());
                intent.putExtra("position",position);
                Log.d("포지션값",String.valueOf(position));
                intent.putExtra("write_number",dict.getWrite_number());
                startActivityForResult(intent,2);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


/*        Intent intent = getIntent();
        String title2 = intent.getStringExtra("title");
        String contents2 = intent.getStringExtra("contents");
        String category2 = intent.getStringExtra("category");
        count++;

        Dictionary data = new Dictionary(title2 , ""+count,category2,formatDate2,"아디");
        mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

        mAdapter.notifyDataSetChanged();*/



         writebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });












        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //첫번째 버튼 행동


                    case R.id.imageButton17:

                        SharedPreferences sharedPreferences = getSharedPreferences("Login_data", MODE_PRIVATE);
                        String logincheck = sharedPreferences.getString("ID","");

                        if(logincheck!="")
                        {
                            Intent intent_write = new Intent(community.this, Write.class);
                            startActivityForResult(intent_write,1);
                        }

                        else if(logincheck=="")
                        {
                            Intent intent_write = new Intent(community.this, Login.class);
                            startActivity(intent_write);
                        }

                        break;

                    case R.id.imageButton18:
                        SharedPreferences sharedPreferences2 = getSharedPreferences("Login_data", MODE_PRIVATE);
                        String logincheck2 = sharedPreferences2.getString("ID","");

                        if(logincheck2!="")
                        {
                            Intent intent_login = new Intent(community.this, memberinfo.class);
                            startActivity(intent_login);
                        }

                        else if(logincheck2=="")
                        {
                            Intent intent_login = new Intent(community.this, Login.class);
                            startActivity(intent_login);
                        }


                        break;

                    case R.id.bottom_search:
                        Intent intent_search = new Intent(community.this, MainActivity.class);
                        startActivity(intent_search);
                        finish();
                        break;

                    case R.id.bottom_champion:
                        Intent intent_champion = new Intent(community.this, Champion.class);
                        startActivity(intent_champion);
                        finish();
                        break;

                    case R.id.bottom_ranking:
                        Intent intent_ranking = new Intent(community.this, Ranking.class);
                        startActivity(intent_ranking);
                        finish();
                        break;
                }


            }
        };
        writebutton.setOnClickListener(onClickListener);
        loginbutton.setOnClickListener(onClickListener);
        searchbutton.setOnClickListener(onClickListener);
        championbutton.setOnClickListener(onClickListener);
        rankingbutton.setOnClickListener(onClickListener);




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // setResult를 통해 받아온 요청번호, 상태, 데이터
        Log.i("RESULT", requestCode + "");
        Log.i("RESULT", resultCode + "");
        Log.i("RESULT", data + "");


        if(requestCode == 2 && resultCode == 11) { // 게시판 수정

            int position3 = data.getIntExtra("position",1);

            String title2 = data.getStringExtra("title2");
            //  Log.d("title2",String.valueOf(title2));
            String category2 = data.getStringExtra("category2");
            //   Log.d("category","category값");

            String contents2 = data.getStringExtra("contents2");
            String time2 = data.getStringExtra("time2");
            String id2 = data.getStringExtra("id2");
            int write_number = data.getIntExtra("write_number",1);
            String date2 = data.getStringExtra("date");

            String modify_number = String.valueOf(position3);

//            SharedPreferences sp2 = getSharedPreferences("write_remove", MODE_PRIVATE);
//            String remove_number = sp2.getString("remove_number","");
//
//            int position4 = position3+Integer.parseInt(remove_number);
//            String modify_number2 = String.valueOf(position4);

            SharedPreferences sharedPreferences2 = getSharedPreferences("Login_data", MODE_PRIVATE);
            String login_id = sharedPreferences2.getString("ID","");

            ArrayList arrayList = new ArrayList<Comment_dict>();



//            int a = 0;
//            a = count;
//            Log.d("숫자체크",String.valueOf(count));
//            Log.d("숫자체크2", String.valueOf(a));


            Dictionary data2 = new Dictionary(title2,write_number,category2,formatDate2,login_id,contents2,arrayList);

//            Dictionary data2 = new Dictionary();
            try {

                SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                Map<String,?> keys = sp.getAll();
                ArrayList<String> test = new ArrayList<String>();

                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Log.d("map values",entry.getKey() + ": " +
                            entry.getValue().toString());
                    String Key2 = entry.getKey();
                    Log.d("map value",Key2);

                    test.add(Key2);
                }

                for(int i= 0; i<test.size(); i++ )
                {
                    Gson gson = new Gson();
                    String json = gson.toJson(data2);

                    editor.putString(String.valueOf(write_number), json);
                    Log.d("position.size2",String.valueOf(test.size()));
                    Log.d("position4",String.valueOf(position3));
                    Log.d("position5",String.valueOf(position3-1));
                    editor.apply();
                    mArrayList.set(position3,data2); // RecyclerView의 마지막 줄에 삽입
                    mAdapter.notifyDataSetChanged();

                }

            } catch(Exception e){
                e.printStackTrace();

            }




        }


        if(requestCode == 1 && resultCode == RESULT_OK) { // 게시판 추가
            Toast.makeText(community.this, "글작성을 완료했습니다!", Toast.LENGTH_SHORT).show();



            String title2 = data.getStringExtra("title");
          //  Log.d("title2",String.valueOf(title2));
            String category2 = data.getStringExtra("category");
         //   Log.d("category","category값");

            String contents2 = data.getStringExtra("contents");
//            int write_number = data.getIntExtra("write_number",1);
            long date2 = data.getLongExtra("date",1);//게시판 작성완료시 누르는 시간



//                SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
//                String strContact = sp.getString("write_number", "");

//                Type listType = new TypeToken<ArrayList<Dictionary>>() {}.getType();
//                // 변환
//                ArrayList<Dictionary> mArrayList = gson.fromJson(strContact, listType);
//
////                 mArrayList.size();
//
////                Dictionary dictionary = new Dictionary();
////                mArrayList.add(dictionary);
////                mArrayList.size();

//            if(count == -1 )
//            {
//                try {
//
//                    SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sp.edit();
//                    Map<String,?> keys = sp.getAll();
//                    ArrayList<String> test = new ArrayList<String>();
//
//                    for(Map.Entry<String,?> entry : keys.entrySet()){
//                        Log.d("map values",entry.getKey() + ": " +
//                                entry.getValue().toString());
//                        String Key2 = entry.getKey();
//                        Log.d("map value",Key2);
//
//                        test.add(Key2);
//                    }
//
//                    int a = test.size();
//                    count = a;
//
//                } catch(Exception e){
//                    e.printStackTrace();
//
//                }
//                }


            count++;
            SharedPreferences sp2 = getSharedPreferences("write_number", MODE_PRIVATE);



            SharedPreferences.Editor editor2 = sp2.edit();
            editor2.putString("number",String.valueOf(count));
            editor2.apply();

            SharedPreferences sharedPreferences2 = getSharedPreferences("Login_data", MODE_PRIVATE);
            String login_id = sharedPreferences2.getString("ID","");

            ArrayList arrayList = new ArrayList<Comment_dict>();


            Intent intent = getIntent();
            String comment_contents = intent.getStringExtra("comment");
            int position4 = intent.getIntExtra("position",1);
//            Comment_dict dict = new Comment_dict("",formatDate2,login_id,comment_contents);// 객체생성할때 글번호,시간,로그인아이디,내용 생성

//            ArrayList<Comment_dict> comList = (ArrayList<Comment_dict>) getIntent().getSerializableExtra("array");
//
//            comList.add(dict);


            Dictionary data2 = new Dictionary(title2 ,count,category2,formatDate2,login_id,contents2,arrayList);

//            ArrayList<Dictionary> mArrayList = new ArrayList<Dictionary>();


            SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();



            Gson gson = new Gson();
//            Type listType = new TypeToken<ArrayList<Dictionary>>() {}.getType();
            String json = gson.toJson(data2);

            String Write_number = String.valueOf(count);
            Log.d("숫자값",Write_number);
            editor.putString(Write_number, json); // JSON으로 변환한 객체를 저장한다.
            editor.apply(); //완료한다

            mArrayList.add(data2); // RecyclerView의 마지막 줄에 삽입
            for(Dictionary dictionary : mArrayList)
            {
                Log.d("으레1", String.valueOf(mArrayList));
            }
            Collections.sort(mArrayList);
//            Collections.sort(mArrayList, new Comparator<Dictionary>() {
//                @Override
//                public int compare(Dictionary o1, Dictionary o2) {
//                    return o1.getWrite_number() > o2.getWrite_number() ? 1
//                            : o1.getWrite_number() > o2.getWrite_number() ? -1 : 0;
//                }
//            });

            for(Dictionary dictionary : mArrayList)
            {
                Log.d("으레2", String.valueOf(mArrayList));
            }

//            mArrayList.get(position4).setC_dict(comList);

            Intent intent2 = new Intent(community.this,Write_List.class);
            Bundle b = new Bundle();
            intent2.putExtra("bundle",b);
            intent2.putExtra("array",mArrayList);
//            intent2.putExtra("obj",data2);



            mAdapter.notifyDataSetChanged();

//            count++;
//            Dictionary data2 = new Dictionary(title2 ,""+count,category2,date2,"아디",contents2);
//
//            Gson gson = new Gson();
////            Type listType = new TypeToken<ArrayList<Dictionary>>() {}.getType();
//            String json = gson.toJson(data2);
//
//            SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//
//            String Write_number = String.valueOf(count);
//
//            editor.putString(Write_number, json); // JSON으로 변환한 객체를 저장한다.
//            editor.apply(); //완료한다
//
//            mArrayList.add(0, data2); // RecyclerView의 마지막 줄에 삽입
//
//
//
//            mAdapter.notifyDataSetChanged(); // 1015 8:37분 수정전








            AlertDialog.Builder builder = new AlertDialog.Builder(community.this);

        }

        if(requestCode == 2 && resultCode == RESULT_OK) { // 게시판 삭제

            int position2 = data.getIntExtra("position",1);
            int write_number = data.getIntExtra("write_number",1);
            Log.d("글번호찍기", String.valueOf(write_number));


            try {

                SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                Map<String,?> keys = sp.getAll();
                ArrayList<String> test = new ArrayList<String>();

                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Log.d("map values",entry.getKey() + ": " +
                            entry.getValue().toString());
                    String Key2 = entry.getKey();
                    Log.d("map value",Key2);

                    test.add(Key2);
                }

                for(int i= 0; i<test.size(); i++ )
                {

                    editor.remove(String.valueOf(write_number));
                    Log.d("글번호찍기2", String.valueOf(write_number));
                    editor.apply();

                }



                mArrayList.remove(position2);
                Log.d("글번호찍기3", String.valueOf(position2));


            } catch(Exception e){
                e.printStackTrace();

            }

            mAdapter.notifyDataSetChanged();
        }


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private community.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final community.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    //핸들러 두번클릭시 종료
    @Override
    public void onBackPressed()
    { //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

//    public void ShowTimeMethod() {
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//                Show_Time_TextView = (TextView)findViewById(R.id.textView44);
//
//                Show_Time_TextView.setText(Time.formatTimeString(now));
//            }
//        };
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {}
//                    handler.sendEmptyMessage(1);    //핸들러를 호출한다. 즉, 시간을 최신화 해준다.
//                }
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();
//    } // 현재시간을 나타내는 스레드


@Override
protected void onStart() { // 광고 이미지 핸들러
    super.onStart();

    Thread myThread = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    handler.sendMessage(handler.obtainMessage());
                    Thread.sleep(3000);
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
                imageView.setImageResource(R.drawable.ad1);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cgagu.com/shop/shopdetail.html?branduid=30972"));
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                i++;
                imageView.setImageResource(R.drawable.ad2);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cgagu.com/m/product_detail.html?brand_uid=25424"));
                        startActivity(intent);
                    }
                });
                break;
            case 2:
                i++;
                imageView.setImageResource(R.drawable.ad3);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://program.tving.com/ocn/ocnthrillerhouse"));
                        startActivity(intent);
                    }
                });
                break;

        }

    }


    private void delayedFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3500);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void flashlight() {
        if (mCameraId == null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    for (String id : mCameraManager.getCameraIdList()) {
                        CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                        Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                        Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                        if (flashAvailable != null && flashAvailable
                                && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                            mCameraId = id;
                            break;
                        }
                    }
                }
            } catch (CameraAccessException e) {
                mCameraId = null;
                e.printStackTrace();
                return;
            }
        }

        mFlashOn = !mFlashOn;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, mFlashOn);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
