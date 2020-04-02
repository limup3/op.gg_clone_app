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

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;


public class memberinfo extends AppCompatActivity {

    private String id_check;
    private String key_name;
    private String key_id;
    private String key_pw;

    private EditText member_phone;
    private EditText member_email;

    private ArrayList<String> modify;
    private static final String modify_info = "modify_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberinfo);




        Button logout = (Button)findViewById(R.id.button22); // 로그아웃
        Button Withdrawal = (Button)findViewById(R.id.button4); //회원탈퇴
        Button pw_modify = (Button)findViewById(R.id.button3); // 비밀번호 변경
        Button info_modify = (Button)findViewById(R.id.button2); // 회원정보 수정
        TextView member_name = (TextView)findViewById(R.id.textView42); // 멤버이름
        TextView member_id = (TextView)findViewById(R.id.textView43); // 멤버아이디
        member_phone = (EditText)findViewById(R.id.editText3); // 멤버 전화번호
        member_email = (EditText)findViewById(R.id.editText4); // 멤버 이메일






        SharedPreferences sharedPreferences3 = getSharedPreferences("Login_data", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        id_check = sharedPreferences3.getString("ID","");


        SharedPreferences sharedPreferences = getSharedPreferences("Sign_infomation",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String id = sharedPreferences.getString(id_check,"");
        Log.d("아이디값 확인",String.valueOf(id));

        Map<String,?> keys = sharedPreferences.getAll();
        ArrayList<String> test = new ArrayList<String>();

//        for(Map.Entry<String,?> entry : keys.entrySet()){
//            Log.d("map values",entry.getKey() + ": " +
//                    entry.getValue().toString());
//            String Key2 = entry.getKey();
//            String Value2 = entry.getValue().toString();
//            String Value3 = entry.getKey() + ": " + entry.getValue().toString();
//            Log.d("가치값 확인2",Key2);
////            Log.d("가치값 확인", String.valueOf(Value2.contains("구구")));
////            Log.d("키값확인", String.valueOf(Value3.contains("&quot;01032442692&quot;,&quot;ll@ll.com&quot;")));
////            if(Value3.contains("01032442692"+","+"ll@ll.com") == true)
////            {
////                Log.d("키값확인2", String.valueOf(Value3.contains(entry.getKey())));
////
////            }
//
//            test.add(Key2);
//        }



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
        Log.d("url값추출1",String.valueOf(urls.get(0)));
        Log.d("url값추출2",String.valueOf(urls.get(1)));
        Log.d("url값추출3",String.valueOf(urls.get(2)));
        Log.d("url값추출4",String.valueOf(urls.get(3)));

         key_name =urls.get(0);
         key_id =urls.get(1);
         key_pw =urls.get(2);


        member_name.setText(urls.get(0));
        member_id.setText(urls.get(1));
        member_phone.setText(urls.get(3));
        member_email.setText(urls.get(4));



    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //첫번째 버튼 행동

                case R.id.button22: // 로그아웃
                    Intent intent_search = new Intent(memberinfo.this,community.class);
                    startActivity(intent_search);
                    Toast.makeText(memberinfo.this, "로그아웃 되셨습니다.", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences3 = getSharedPreferences("Login_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = sharedPreferences3.edit();
                    editor3.clear();
                    editor3.apply();
                    finish();
                    break;

                case R.id.button4: // 회원탈퇴
                    Intent intent_community = new Intent(memberinfo.this,community.class);
                    startActivity(intent_community);
                    Toast.makeText(memberinfo.this, "회원탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
                    editor.remove(id_check);
                    editor.apply();
                    SharedPreferences sharedPreferences4 = getSharedPreferences("Login_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = sharedPreferences4.edit();
                    editor4.clear();
                    editor4.apply();
                    finish();
                    break;

                case R.id.button3: // 비밀번호 변경
                    Intent Reset_pw = new Intent(memberinfo.this, Reset_pw_modify.class);
                    startActivity(Reset_pw);
                    finish();
                    break;

                case R.id.button2: // 회원정보 수정
                    Intent intent = new Intent(memberinfo.this, community.class);
                    startActivity(intent);
                    Toast.makeText(memberinfo.this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    String member_phone2 = member_phone.getText().toString(); // 사용자가 입력한 전화번호값 저장
                    String member_email2 = member_email.getText().toString(); // 사용자가 입력한 이메일값 저장
                    modify = new ArrayList<String>();
                    modify.add(0,key_name);
                    modify.add(1,key_id);
                    modify.add(2,key_pw);
                    modify.add(3,member_phone2);
                    modify.add(4,member_email2);
                    setStringArrayPref(modify_info, modify);

                    finish();
                    break;
            }


        }
    };

        logout.setOnClickListener(onClickListener);
        Withdrawal.setOnClickListener(onClickListener);
        pw_modify.setOnClickListener(onClickListener);
        info_modify.setOnClickListener(onClickListener);
//        communitybutton.setOnClickListener(onClickListener);
//        championbutton.setOnClickListener(onClickListener);
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
