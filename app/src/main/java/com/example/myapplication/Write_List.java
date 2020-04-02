package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Write_List extends AppCompatActivity {

    private TextView title_ok2;
    private TextView contents_ok2;
    private TextView category_ok2;
    private TextView id_ok2;
    private TextView time_ok2;
    private TextView image_ok;
    private Button write_list;
    private Button write_modify;
    private Button write_remove;
    private EditText editTextID;
    private ArrayList<Dictionary> mArrayList;
    private ArrayList<Comment_dict> cArrayList;
    private ArrayList<Comment_dict> arrayList;
//    private ArrayList<ArrayList<Comment_dict>> cArrayList2;
//    private ArrayList<ArrayList<ArrayList<Comment_dict>>> cArrayList3;


    private CommentAdapter mAdapter;
    private int comment_count = -1;
    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    String formatDate2 = formatDate.substring(11,16);

    String id_ok;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_list);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView3);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);



        String title_ok = "";
        String contents_ok = "";
        String category_ok = "";
        String time_ok = "";
        id_ok = "";
        final int write_number;
        final int position_ok;

        Bundle extras = getIntent().getExtras();

        title_ok = extras.getString("title");
        contents_ok = extras.getString("contents");
        category_ok = extras.getString("category");
        time_ok = extras.getString("time");
        id_ok = extras.getString("id");
        position_ok = extras.getInt("position");
        Log.d("write_listposition",String.valueOf(position_ok));
        write_number = extras.getInt("write_number");

//        Dictionary dict = mArrayList.get(position_ok);
        Log.d("태그", String.valueOf(position_ok));
//        Log.d("태그2", String.valueOf(mArrayList.get(position_ok)));



        title_ok2 = (TextView) findViewById(R.id.textView15);
        title_ok2.setText(title_ok);
        contents_ok2 = (TextView) findViewById(R.id.textView24);
        contents_ok2.setText(contents_ok);
        category_ok2 = (TextView) findViewById(R.id.textView18);
        category_ok2.setText(category_ok);
        time_ok2 = (TextView) findViewById(R.id.textView22);
        time_ok2.setText(time_ok);
        id_ok2 = (TextView) findViewById(R.id.textView23);
        id_ok2.setText(id_ok);

        String Position_number = String.valueOf(position_ok);

        cArrayList = new ArrayList<Comment_dict>();
        mArrayList = new ArrayList<Dictionary>();

//        cArrayList2 = new ArrayList<ArrayList<Comment_dict>>();
//        cArrayList3 = new ArrayList<ArrayList<ArrayList<Comment_dict>>>();

        //mAdapter = new CustomAdapter( mArrayList);
        mAdapter = new CommentAdapter( this, cArrayList);

        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
//        Bundle b = getIntent().getExtras();
//        ArrayList<Dictionary> comList = (ArrayList<Dictionary>) b.getParcelableArrayList("array");
//        Dictionary data = getIntent().getParcelableExtra("obj");
//
//        ArrayList<Dictionary> comList = (ArrayList<Dictionary>) getIntent().getSerializableExtra("array");
////
//        Log.d("어레1", String.valueOf(comList));
//        Log.d("어레1", String.valueOf(data));
//
//        comList.add(data);


//
//        Log.d("어레1", String.valueOf(comList));
//
//        for( int i = 0 ; i<comList.size(); i++)
//        {
//            Log.d("어레2", String.valueOf(comList.get(i)));
//        }




        Button buttonInsert = (Button)findViewById(R.id.button23);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
            @Override
            public void onClick(View v) {


                EditText editTextID = (EditText) findViewById(R.id.editText);
                String contents = editTextID.getText().toString();
                editTextID.setText(null);



                SharedPreferences sharedPreferences2 = getSharedPreferences("Login_data", MODE_PRIVATE);
                String login_id = sharedPreferences2.getString("ID","");
                //아이디값 넘기기

                Comment_dict dict = new Comment_dict("",formatDate2,login_id,contents);// 객체생성할때 글번호,시간,로그인아이디,내용 생성

                ArrayList<Comment_dict> arrayList = new ArrayList<Comment_dict>();

//                Dictionary data2 = new Dictionary("" ,"","","",login_id,"",cArrayList);

                SharedPreferences sp = getSharedPreferences("comment", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();


                    cArrayList.add(dict); //댓글에 대한 정보 어레이리스트
//                comList.get(position_ok).setC_dict(cArrayList);

//                    mArrayList.get(position_ok).setC_dict(cArrayList);
//                    cArrayList.add(dict); //댓글에 대한 정보 어레이리스트
//                    Log.d("어레1", String.valueOf(cArrayList.add(dict)));
//                    mArrayList.add(data2); // 게시글에 대한 정보 어레이리스트
////                   Log.d("어레2", String.valueOf(mArrayList.add(data2)));
//                    mArrayList.get(position_ok).setC_dict(cArrayList); //게시글 어레이리스트의 포지션값에 댓글에 대한 정보 어레이리스트 셋팅
//                     Log.d("어레3", String.valueOf(mArrayList));
                Intent intent = new Intent(Write_List.this,community.class);
                intent.putExtra("comment",editTextID.getText().toString());
                intent.putExtra("array",cArrayList);
                intent.putExtra("position",position_ok);



                //Comment_dict 클래스의 객체생성, 댯글을 단 유저 정보를 모아놓은 어레이리스트
//                cArrayList2.add(cArrayList);
//                Log.d("어레이1", String.valueOf(cArrayList));
//                Log.d("어레이2", String.valueOf(cArrayList2));

//                cArrayList3.add(cArrayList2);

//
//                JSONObject data1 = new JSONObject();
//                try {
//                    data1.put("name", dict);
//                    Log.d("")
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                JSONArray data_list = new JSONArray();
//                data_list.put(data1);
//
//                JSONObject itemList = new JSONObject();
//                try {
//                    itemList.put("itemlist", data_list);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Comment_dict>>() {}.getType(); // 어레이리스트 타입으로 볁환
                String json = gson.toJson(cArrayList,listType);

                String Position_number = String.valueOf(position_ok);

                editor.putString(Position_number, json); // JSON으로 변환한 객체를 저장한다.
                editor.apply(); //완료한다


                mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영

            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("comment",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        Map<String,?> keys = sharedPreferences.getAll();
        ArrayList<String> test = new ArrayList<String>();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            String Key2 = entry.getKey();
            String Value2 = entry.getValue().toString();
            Log.d("map value",Key2);

            test.add(Key2); //키값을 테스트 어레이에 저장
        }

        for(int i= 0; i<test.size(); i++ ) // 키값 사이즈
        {
            Log.d("position.size",String.valueOf(test.size()));

            Gson gson = new Gson();
            String json2 = sharedPreferences.getString(test.get(i),"");

//            Type listType = new TypeToken<ArrayList<ArrayList<Comment_dict>>>() {}.getType();
//
//            ArrayList<ArrayList<Comment_dict>> dict2 = gson.fromJson(json2,listType);
//
            Type listType = new TypeToken<ArrayList<Comment_dict>>() {}.getType();
//
            ArrayList<Comment_dict> dict2 = gson.fromJson(json2,listType);
//
//            Comment_dict dict2 = gson.fromJson(json2, Comment_dict.class);
//            Log.d("어레이", String.valueOf(dict2));
//            cArrayList = new ArrayList<>();
//            cArrayList2 = new ArrayList<ArrayList<Comment_dict>>();
//            cArrayList3.add(dict2);
//            cArrayList2.add(cArrayList);
//            cArrayList.add(dict2);

            ArrayList<Comment_dict> arrayList = new ArrayList<Comment_dict>();

//            Dictionary data2 = new Dictionary("" ,"","","","","",arrayList);

                cArrayList.add(dict2.get(i));
//                mArrayList.add(data2);
//                mArrayList.get(position_ok).setC_dict(cArrayList);


              Log.d("데이터전송", String.valueOf(dict2.get(i)));
//            Log.d("데이터전송1", String.valueOf(dict2.get(0)));
//            Log.d("데이터전송2", String.valueOf(dict2.get(1)));
//            Log.d("데이터전송3", String.valueOf(dict2.get(2)));

//            cArrayList.add(dict2);


            Log.d("어레이1", String.valueOf(cArrayList));
//            Log.d("어레이2", String.valueOf(cArrayList2));
//            Log.d("어레이3", String.valueOf(cArrayList3));
            Log.d("dict2", String.valueOf(dict2));
            Log.d("json2",String.valueOf(json2));
            editor.apply();
            mAdapter.notifyDataSetChanged();

        }


//        Gson gson = new Gson();
//        String json2 = sharedPreferences.getString("comment","");
//
//        Type listType = new TypeToken<ArrayList<Comment_dict>>() {}.getType();
////
//        ArrayList<Comment_dict> dict2 = gson.fromJson(json2,listType);
//
//        try {
//            cArrayList.add(dict2.get(0));
//            cArrayList.add(dict2.get(1));
//            cArrayList.add(dict2.get(2));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//        mAdapter.notifyDataSetChanged();

/*        Intent intent = getIntent();
        String title2 = intent.getStringExtra("title");
        String contents2 = intent.getStringExtra("contents");
        String category2 = intent.getStringExtra("category");
        String geturi=intent.getParcelableExtra("uri");

        ImageView imageview = (ImageView)findViewById(R.id.imageView);

        title_ok = (TextView) findViewById(R.id.textView15);
        title_ok.setText(title2);
        contents_ok = (TextView) findViewById(R.id.textView24);
        contents_ok.setText(contents2);
        category_ok = (TextView) findViewById(R.id.textView18);
        category_ok.setText(category2);
*/
        write_list = (Button) findViewById(R.id.button16);
        write_modify = (Button) findViewById(R.id.button17);
        write_remove = (Button) findViewById(R.id.button15);

        write_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(Write_List.this,community.class);
                intent.putExtra("title2", title_ok2.getText().toString());
                intent.putExtra("contents2", contents_ok2.getText().toString());
                intent.putExtra("category2", category_ok2.getText().toString());
                intent.putExtra("time2",formatDate2);
                intent.putExtra("id2", id_ok2.getText().toString());
                intent.putExtra("position",position_ok);
                intent.putExtra("write_number",write_number);
//                intent.putExtra("comment",editTextID.getText().toString());
                Log.d("write_listposition2",String.valueOf(position_ok));


            setResult(11,intent);
            finish();

            }
        });


        write_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Write_List.this,community.class);
                intent.putExtra("position",position_ok);
                intent.putExtra("write_number",write_number);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        write_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences2 = getSharedPreferences("Login_data", MODE_PRIVATE);
                String login_id = sharedPreferences2.getString("ID","");
                Log.d("아이디확인",login_id);
                Log.d("아이디확인2",id_ok);
                Log.d("아이디확인3",id_ok2.getText().toString());
                if(login_id.equals(id_ok))
                {
                    Intent intent_set = new Intent(Write_List.this,Write_List2.class);
                    intent_set.putExtra("title2", title_ok2.getText().toString());
                    intent_set.putExtra("contents2", contents_ok2.getText().toString());
                    intent_set.putExtra("category2", category_ok2.getText().toString());
                    intent_set.putExtra("time2", time_ok2.getText().toString());
                    intent_set.putExtra("id2", id_ok2.getText().toString());
                    intent_set.putExtra("position",position_ok);
                    intent_set.putExtra("write_number",write_number);
                    Log.d("write_listposition3",String.valueOf(position_ok));
                    startActivityForResult(intent_set,3);
                }
                else if(!login_id.equals(id_ok))
                {
                    Toast.makeText(Write_List.this, "본인이 작성한 글만 수정할수있습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        });



/*        Bundle extras = getIntent().getExtras();
        String s = extras.getString("string");
        int i = extras.getInt("integer");
        double d = extras.getDouble("double");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageview.setImageBitmap(bitmap);*/



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // setResult를 통해 받아온 요청번호, 상태, 데이터


        if(requestCode == 3 && resultCode == 123 ) {
            Toast.makeText(Write_List.this, "성공", Toast.LENGTH_SHORT).show();
            title_ok2.setText(data.getStringExtra("title2"));
            contents_ok2.setText(data.getStringExtra("contents2"));
            category_ok2.setText(data.getStringExtra("category2"));
            time_ok2.setText(data.getStringExtra("time2"));
            id_ok2.setText(data.getStringExtra("id2"));

        }
    }


}
