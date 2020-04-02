package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Write extends AppCompatActivity {

    private Spinner spinner;
    private EditText title;
    private EditText contents;
    private Button complete;
    private Button reset;
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private community mRecyclerView;
    private Gson gson;
    private int count = -1;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    String formatDate2 = formatDate.substring(11,16);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

//        imageview = (ImageView)findViewById(R.id.imageview8);
//        imageview.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
                    //이미지

//            }
//        });

 //       final ArrayList<Dictionary> datas = new ArrayList<Dictionary>();

//        mAdapter = new CustomAdapter( this, mArrayList);

//        mRecyclerView.setAdapter(mAdapter);

        //id refernece for wizet
        spinner = (Spinner)findViewById(R.id.spinner);
        title = (EditText) findViewById(R.id.editText12);
        contents = (EditText) findViewById(R.id.editText13);
        complete = (Button) findViewById(R.id.button14);
        reset = (Button) findViewById(R.id.reset);


/*        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        final String text = sf.getString("text","");
        String text2 = sf.getString("text","");

        title.setText(text);
        contents.setText(text2);*/


//기본 SharedPreferences 환경과 관련된 객체를 얻어옵니다.

        // SharedPreferences 수정을 위한 Editor 객체를 얻어옵니다.








        String[] list2 = new String[6];
        list2[0] = "유머";
        list2[1] = "자유";
        list2[2] = "노하우";
        list2[3] = "뉴스";
        list2[4] = "사고";
        list2[5] = "버그신고";

        //using ArrayAdapter
        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list2);
        spinner.setAdapter(spinnerAdapter);

        View view = LayoutInflater.from(Write.this)
                .inflate(R.layout.community_list, null, false);


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                // 제목 입력 확인
                if( title.getText().toString().length() == 0 ) {
                    Toast.makeText(Write.this, "제목을 입력하세요!", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }

                // 내용 입력 확인
                if( contents.getText().toString().length() == 0 ) {
                    Toast.makeText(Write.this, "내용를 입력하세요!", Toast.LENGTH_SHORT).show();
                    contents.requestFocus();
                    return;
                }

/*                Intent intent = new Intent();
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("contents", contents.getText().toString());
                intent.putExtra("category",spinner.getSelectedItem().toString());
                setResult(RESULT_OK,intent);*/

                if(title.getText().toString().length() != 0 && contents.getText().toString().length() != 0 ) {
                    Intent intent_ok = new Intent(Write.this, community.class);
                    String title2 = title.getText().toString();
                    String contents2 = contents.getText().toString();
                    String cate = spinner.getSelectedItem().toString();

                    count++;
                    intent_ok.putExtra("date",now);
                    intent_ok.putExtra("title", title.getText().toString());
                    intent_ok.putExtra("contents", contents.getText().toString());
                    intent_ok.putExtra("write_number",count);
                    intent_ok.putExtra("category",cate);
                    setResult(RESULT_OK,intent_ok);
                    finish();

//                    ArrayList<String> list = new ArrayList<String>();
//                    list.add(0,title2);
//                    list.add(1,"글");
//                    list.add(2,cate);
//                    list.add(3,formatDate2);
//                    list.add(4,"아디");
//                    list.add(5,contents2);
//                    setStringArrayPref(write_list,list);
//
//                    count++;
//                    -------------------------------------------------------------------------------------------
//
//                    Dictionary dictionary = new Dictionary(title2,count,cate,formatDate2,"아디",conotents2);
////                    Dictionary dictionary = new Dictionary();
////                    dictionary.setCategory(cate);
////                    dictionary.setContents(conotents2);
////                    dictionary.setId("아디");
////                    dictionary.setTime("시간");
////                    dictionary.setTitle(title2);
//  //                  dictionary.setWrite_number(1);
//                    Log.d("Write_class", String.valueOf(title2));
//                    Log.d("Write_class", String.valueOf(count));
//                    Log.d("Write_class", String.valueOf(formatDate2));
//                    Log.d("Write_class", String.valueOf(conotents2));
//                    Log.d("Write_class", String.valueOf(dictionary));
//
//                    mAdapter = new CustomAdapter(mArrayList);
//                    Log.d("Write_class", String.valueOf(mArrayList));
//                    mArrayList.add(dictionary);
//
//                    // Gson 인스턴스 생성
//                    gson = new GsonBuilder().create();
//                    Type listType = new TypeToken<ArrayList<Dictionary>>() {}.getType();
//
//                    // JSON 으로 변환
//                    String json = gson.toJson(mArrayList, listType);
//
//                    SharedPreferences sp = getSharedPreferences("community", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("wirte_number", json); // JSON으로 변환한 객체를 저장한다.
//                    editor.commit(); //완료한다.
//
//                    mAdapter.notifyDataSetChanged();

                }
            }
        });




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(null);
                contents.setText(null);
                Toast.makeText(Write.this, "작성하신 글을 초기화하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        //event listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Write.this,"카테고리 : "+spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                Log.d("cateposition",String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

/*    @Override
    protected void onStop() {
        super.onStop();

        // Activity가 종료되기 전에 저장한다.
        //SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);

        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = title.getText().toString(); // 사용자가 입력한 저장할 데이터
        String text2 = contents.getText().toString(); // 사용자가 입력한 저장할 데이터
        editor.putString("text",text); // key, value를 이용하여 저장하는 형태
        editor.putString("text",text2); // key, value를 이용하여 저장하는 형태
        //다양한 형태의 변수값을 저장할 수 있다.
        //editor.putString();
        //editor.putBoolean();
        //editor.putFloat();
        //editor.putLong();
        //editor.putInt();
        //editor.putStringSet();

        //최종 커밋
        editor.commit();


    }*/

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
///*        int position = getIntent().getIntExtra(imageview.POSTION, 0);*/
//
//
//
//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri selectedImageUri = data.getData();
//            imageview.setImageURI(selectedImageUri);
//
//        }
//
//    }

//    private void setStringCommunitypref(String key, ArrayList<String> values) {
//
//        SharedPreferences sharedPreferences = getSharedPreferences("community",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        JSONArray a = new JSONArray();
//        for (int i = 0; i < values.size(); i++) {
//            a.put(values.get(i));
//        }
//        if (!values.isEmpty()) {
//            editor.putString(key, a.toString());
//        } else {
//            editor.putString(key, null);
//        }
//        editor.apply();
//    }
//private void setStringArrayPref(String key, ArrayList<String> values) {
//    SharedPreferences prefs = getSharedPreferences("community", MODE_PRIVATE);
//    SharedPreferences.Editor editor = prefs.edit();
//    JSONArray a = new JSONArray();
//    for (int i = 0; i < values.size(); i++) {
//        a.put(values.get(i));
//    }
//    if (!values.isEmpty()) {
//        editor.putString("write_number", a.toString());
//    } else {
//        editor.putString("write_number", null);
//    }
//    editor.apply();
//}
}