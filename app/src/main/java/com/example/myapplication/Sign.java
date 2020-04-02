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

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;


public class Sign extends AppCompatActivity {

    private Gson gson;

    private EditText editText_id;
    private EditText editText_pw;
    private EditText editText_pwconfirm;
    private EditText editText_name;
    private EditText editText_email;
    private EditText editText_phone;
    private Button Sign_ok_button;
    private Button Sign_cancel_button;
    private Button id_check_button;
    public String Sign_id;
    public ArrayList<String> memberlist;
    protected static final String Sign_info = "Sign_info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        memberlist = new ArrayList<String>();




         editText_id = (EditText) findViewById(R.id.sign_id);
         editText_pw = (EditText) findViewById(R.id.sign_pw);
         editText_pwconfirm = (EditText) findViewById(R.id.sign_pwconfirm);
         editText_name = (EditText) findViewById(R.id.sign_name);
         editText_email = (EditText) findViewById(R.id.sign_email);
         editText_phone = (EditText) findViewById(R.id.sign_phone);
         Sign_ok_button = (Button)findViewById(R.id.button5);
         Sign_cancel_button = (Button)findViewById(R.id.button7);
         id_check_button = (Button)findViewById(R.id.button24);







        editText_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


           String password = editText_pw.getText().toString();
            String confirm = editText_pwconfirm.getText().toString();

                if( confirm.equals(password) ) {
                    editText_pwconfirm.setBackgroundColor(Color.GREEN);
                    editText_pw.setBackgroundColor(Color.GREEN);

            } else {
                    editText_pwconfirm.setBackgroundColor(Color.RED);
                    editText_pw.setBackgroundColor(Color.RED);

            }
        }
        public void afterTextChanged(Editable s) {

        }
    });

        SharedPreferences sharedPreferences = getSharedPreferences("Sign_infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Map<String,?> keys = sharedPreferences.getAll();
        final ArrayList<String> test = new ArrayList<String>();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            String Key2 = entry.getKey();
            String Value2 = entry.getValue().toString();
            Log.d("map value",Key2);

            test.add(Key2);
        }

        Log.d("중복체크", String.valueOf(test.contains(editText_id.getText().toString())));
        id_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(test.contains(editText_id.getText().toString()) == true)
                {
                    Toast.makeText(Sign.this, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();

                }
//                else
//                {
//                    Toast.makeText(Sign.this, "중복된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
//                }

                else if(test.contains(editText_id.getText().toString()) == false)
                {
                    Toast.makeText(Sign.this, "중복된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                }
//                for(int i= 0; i<test.size(); i++ )
//                {
//
//                    Log.d("중복체크2",test.get(i));
//
//
//                    if(editText_id.getText().toString().equals(test.get(i)))
//                    {
//                        Toast.makeText(Sign.this, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                    if(editText_id.getText().toString().equals(test.get(i)))
//                    {
//                        Toast.makeText(Sign.this, "중복된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
            }
        });






        Sign_ok_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 이름 입력 확인
                if( editText_name.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_name.requestFocus();
                    return;
                }

                // 아이디 입력 확인
                if( editText_id.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "id를 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_id.requestFocus();
                    return;
                }

                if(test.contains(editText_id.getText().toString()) == true)
                {
                    Toast.makeText(Sign.this, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
                    editText_id.requestFocus();
                    return;
                }

                // 이메일 입력 확인
                if( editText_email.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_email.requestFocus();
                    return;
                }

                // 연락처 입력 확인
                if( editText_phone.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "연락처를 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_phone.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( editText_pw.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_pw.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( editText_pwconfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(Sign.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    editText_pwconfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !editText_pw.getText().toString().equals(editText_pwconfirm.getText().toString()) ) {
                    Toast.makeText(Sign.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    editText_pw.setText("");
                    editText_pwconfirm.setText("");
                    editText_pw.requestFocus();
                    return;
                }

                Intent result = new Intent();
                result.putExtra("id", editText_id.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();

                String Sign_name = editText_name.getText().toString(); // 사용자가 입력한 이름값 저장
            Sign_id = editText_id.getText().toString(); // 사용자가 입력한 ID값 저장
        String Sign_pw = editText_pw.getText().toString(); // 사용자가 입력한 비밀번호값 저장
        String Sign_phone = editText_phone.getText().toString(); // 사용자가 입력한 전화번호값 저장
        String Sign_email = editText_email.getText().toString(); // 사용자가 입력한 이메일값 저장


//                Member_dict member_dict = new Member_dict();
//                member_dict.setId(editText_id.getText().toString());
//                member_dict.setPw(editText_pw.getText().toString());
//                member_dict.setName(editText_name.getText().toString());
//                member_dict.setEmail(editText_email.getText().toString());
//                member_dict.setPhone(editText_phone.getText().toString());
//
//
//
//                List<Member_dict> datas = new ArrayList<Member_dict>();
//                datas.add(0,member_dict);
//
//
//
//                // Gson 인스턴스 생성
//                gson = new GsonBuilder().create();
//                // JSON 으로 변환
//                String strContact = gson.toJson(member_dict, Member_dict.class);
//
//                SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("contact", strContact); // JSON으로 변환한 객체를 저장한다.
//                editor.commit(); //완료한다.


                memberlist.add(0,Sign_name);
                memberlist.add(1,Sign_id);
                memberlist.add(2,Sign_pw);
                memberlist.add(3,Sign_phone);
                memberlist.add(4,Sign_email);
                setStringArrayPref(Sign_info, memberlist);
                Log.d("put json", "Put json");



            }
        });



 /*               switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.button5: {
                        Intent intent_ok = new Intent(Sign.this, MainActivity.class);
                        startActivity(intent_ok);
                        break;
                    }
                    case R.id.button7: {
                        Intent intent_cancel = new Intent(Sign.this, community.class);
                        startActivity(intent_cancel);
                        break;
                    }



                }*/

        Sign_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        /*Sign_ok_button.setOnClickListener(onClickListener);
        Sign_cancel_button.setOnClickListener(onClickListener);*/



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
            editor.putString(Sign_id, member_info.toString());
        } else {
            editor.putString(Sign_id, null);
        }
        editor.apply();
    }



//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        // Activity가 종료되기 전에 저장한다.
//        //SharedPreferences를 sFile이름, 기본모드로 설정
//        SharedPreferences sharedPreferences = getSharedPreferences("Sign_info",MODE_PRIVATE);
//
//        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//
//
//        String Sign_name = editText_name.getText().toString(); // 사용자가 입력한 이름값 저장
//        Sign_id = editText_id.getText().toString(); // 사용자가 입력한 ID값 저장
//        String Sign_pw = editText_pw.getText().toString(); // 사용자가 입력한 비밀번호값 저장
//        String Sign_phone = editText_phone.getText().toString(); // 사용자가 입력한 전화번호값 저장
//        String Sign_email = editText_email.getText().toString(); // 사용자가 입력한 이메일값 저장
//
//        mArrayList.add(Sign_name);
//        mArrayList.add(Sign_id);
//        mArrayList.add(Sign_pw);
//        mArrayList.add(Sign_phone);
//        mArrayList.add(Sign_email);
//
//        JSONArray member_info = new JSONArray();
//        for ( int i = 0 ; i< mArrayList.size(); i++)
//        {
//            member_info.put(mArrayList.get(i));
//        }
//        if (!mArrayList.isEmpty()) {
//            editor.putString(Sign_id, member_info.toString());
//        } else {
//            editor.putString(Sign_id, null);
//        }
//        editor.apply();
//
////        editor.putString(Sign_id,Sign_name); // key, value를 이용하여 저장하는 형태
////        editor.putString(Sign_id,Sign_id); // key, value를 이용하여 저장하는 형태
////          editor.putString(Sign_id,Sign_pw); // key, value를 이용하여 저장하는 형태
////        editor.putString(Sign_id,Sign_phone); // key, value를 이용하여 저장하는 형태
////        editor.putString(Sign_id,Sign_email); // key, value를 이용하여 저장하는 형태
//        //다양한 형태의 변수값을 저장할 수 있다.
//        //editor.putString();
//        //editor.putBoolean();
//        //editor.putFloat();
//        //editor.putLong();
//        //editor.putInt();
//        //editor.putStringSet();
//
//
//        //최종 커밋
//      //  editor.commit();
//
//
//    }


}
