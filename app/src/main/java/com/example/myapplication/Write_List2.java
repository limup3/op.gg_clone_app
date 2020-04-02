package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Write_List2 extends AppCompatActivity {

    private EditText title_ok3;
    private EditText contents_ok3;
    private TextView category_ok3;
    private TextView id_ok3;
    private TextView time_ok3;
    private Button write_modify_ok;
    private ArrayList<Dictionary> mArrayList;
    private com.example.myapplication.CustomAdapter mAdapter;


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
        setContentView(R.layout.activity_write_list2);


        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter( mArrayList);


        String title_ok2 = "";
        String contents_ok2 = "";
        String category_ok2 = "";
        String time_ok2 = "";
        String id_ok2 = "";


        Bundle extras = getIntent().getExtras();

        title_ok2 = extras.getString("title2");
        contents_ok2 = extras.getString("contents2");
        category_ok2 = extras.getString("category2");
        time_ok2 = extras.getString("time2");
        id_ok2 = extras.getString("id2");



        title_ok3 = (EditText) findViewById(R.id.title_ok);
        title_ok3.setText(title_ok2);
        contents_ok3 = (EditText) findViewById(R.id.contents_ok);
        contents_ok3.setText(contents_ok2);
        category_ok3 = (TextView) findViewById(R.id.category_ok);
        category_ok3.setText(category_ok2);
        time_ok3 = (TextView) findViewById(R.id.time_ok);
        time_ok3.setText(time_ok2);
        id_ok3 = (TextView) findViewById(R.id.id_ok);
        id_ok3.setText(id_ok2);





        write_modify_ok = (Button) findViewById(R.id.modify_ok);
        write_modify_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(Write_List2.this,Write_List.class);
                intent2.putExtra("title2", title_ok3.getText().toString());
                intent2.putExtra("contents2", contents_ok3.getText().toString());
                intent2.putExtra("category2", category_ok3.getText().toString());
                intent2.putExtra("time2", formatDate2);
                intent2.putExtra("id2", id_ok3.getText().toString());
                setResult(123, intent2);
                finish();

            }
        });
        ;




/*        Bundle extras = getIntent().getExtras();
        String s = extras.getString("string");
        int i = extras.getInt("integer");
        double d = extras.getDouble("double");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageview.setImageBitmap(bitmap);*/



    }


}
