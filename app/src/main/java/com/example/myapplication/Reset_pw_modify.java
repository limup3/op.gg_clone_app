package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Reset_pw_modify extends AppCompatActivity {

    private String id_check;
    private String key_name;
    private String key_id;
    private String key_phone;
    private String key_email;


    private ArrayList<String> modify;
    private static final String modify_pw = "modify_pw";
    private EditText passwd;
    private EditText passwd_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw_modify);

        Button Reset_pw_ok_button = (Button)findViewById(R.id.button12);
        Button Reset_pw_cancel_button = (Button)findViewById(R.id.button13);
        passwd = (EditText)findViewById(R.id.editText11);
        passwd_ok = (EditText)findViewById(R.id.editText10);

        SharedPreferences sharedPreferences3 = getSharedPreferences("Login_data", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        id_check = sharedPreferences3.getString("ID","");


        SharedPreferences sharedPreferences = getSharedPreferences("Sign_infomation",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String id = sharedPreferences.getString(id_check,"");
        Log.d("아이디값 확인",String.valueOf(id));

        final ArrayList<String> urls = new ArrayList<String>();
        if (id != null) {
            try {
                JSONArray a = new JSONArray(id);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                    Log.d("urls확인",String.valueOf(urls));
                    Log.d("url값",url);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        key_name =urls.get(0);
        key_id =urls.get(1);
        key_phone =urls.get(3);
        key_email =urls.get(4);

        passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String password = passwd.getText().toString();
                String confirm = passwd_ok.getText().toString();

                if( password.equals(confirm) ) {
                    passwd.setBackgroundColor(Color.GREEN);
                    passwd_ok.setBackgroundColor(Color.GREEN);


                } else {
                    passwd.setBackgroundColor(Color.RED);
                    passwd_ok.setBackgroundColor(Color.RED);


                }
            }
            public void afterTextChanged(Editable s) {

            }
        });



        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.button12: {
                        Intent intent_ok = new Intent(Reset_pw_modify.this, community.class);


                        // 비밀번호 입력 확인
                        if( passwd.getText().toString().length() == 0 ) {
                            Toast.makeText(Reset_pw_modify.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                            passwd.requestFocus();
                            return;
                        }

                        // 비밀번호 확인 입력 확인
                        if( passwd_ok.getText().toString().length() == 0 ) {
                            Toast.makeText(Reset_pw_modify.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                            passwd_ok.requestFocus();
                            return;
                        }

                        // 비밀번호 일치 확인
                        if( !passwd_ok.getText().toString().equals(passwd.getText().toString()) ) {
                            Toast.makeText(Reset_pw_modify.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                            passwd.setText("");
                            passwd_ok.setText("");
                            passwd.requestFocus();
                            return;
                        }


                        Toast.makeText(Reset_pw_modify.this, "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        modify = new ArrayList<String>();
                        modify.add(0,key_name);
                        modify.add(1,key_id);
                        modify.add(2,passwd.getText().toString());
                        modify.add(3,key_phone);
                        modify.add(4,key_email);
                        setStringArrayPref(modify_pw, modify);
                        startActivity(intent_ok);
                        finish();
                        break;
                    }
                    case R.id.button13: {
//                        Intent intent_cancel = new Intent(Reset_pw.this, community.class);
//                        startActivity(intent_cancel);
                        onBackPressed();
                        break;
                    }
                }
            }
        };
        Reset_pw_ok_button.setOnClickListener(onClickListener);
        Reset_pw_cancel_button.setOnClickListener(onClickListener);
    }

    private void setStringArrayPref(String key, ArrayList<String> values) {

        // Activity가 종료되기 전에 저장한다.
        //SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences sharedPreferences = getSharedPreferences("Sign_infomation", MODE_PRIVATE);

        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        JSONArray member_info = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            member_info.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(id_check, member_info.toString());
        } else {
            editor.putString(id_check, null);
        }
        editor.apply();
    }

}


