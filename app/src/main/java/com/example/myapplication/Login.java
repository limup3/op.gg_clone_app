package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.example.myapplication.Sign.Sign_info;


public class Login extends AppCompatActivity {

    private EditText editText_id;
    private EditText editText_pw;
    public com.example.myapplication.Sign Sign_id;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
//        final SharedPreferences sf = getSharedPreferences("Sign_info",MODE_PRIVATE);
//        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
//        final String Login_id = sf.getString(Sign.Sign_id,"");










//        SharedPreferences sharedPreferences = getSharedPreferences("Sign_info",MODE_PRIVATE);
//        final String json = sharedPreferences.getString(Sign.Sign_id,null);
//        Log.d("json",String.valueOf(Sign.Sign_id));

        Button login_ok_button  = (Button)findViewById(R.id.login_ok_button );
        Button login_ok_button2  = (Button)findViewById(R.id.login_ok_button2 );
        Button login_find_id_button  = (Button)findViewById(R.id.login_find_id_button );
        Button login_find_pw_button  = (Button)findViewById(R.id.login_find_pw_button );
        editText_id = findViewById(R.id.sign_id);
        editText_pw = findViewById(R.id.password);



        login_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ArrayList<String> list = getStringArrayPref(Sign_info);
                    if( !list.isEmpty() ) {

                        if(editText_pw.getText().toString().equals(list.get(2)))
                        {
                            Toast.makeText(Login.this, "로그인성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (Login.this, community.class);
                            intent.putExtra("id", editText_id.getText().toString());
                            startActivity(intent);
                            SharedPreferences sharedPreferences = getSharedPreferences("Login_data", MODE_PRIVATE);
                            //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("ID", editText_id.getText().toString().trim());
                            editor.putString("PWD", editText_pw.getText().toString().trim());
                            editor.apply();
                            finish();

                        }
//                    else if(!editText_id.getText().toString().equals(list.get(2)) && editText_pw.getText().toString().equals(list.get(3)))
//                    {
//                        Toast.makeText(Login.this, "로그인실패.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if(!editText_id.getText().toString().equals(list.get(2)))
//                    {
//                        Toast.makeText(Login.this, "아이디가 틀리거나 존재하지않습니다.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if(!editText_pw.getText().toString().equals(list.get(3)))
//                    {
//                        Toast.makeText(Login.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
//                    }
//
                        else
                        {
                            Toast.makeText(Login.this, "로그인실패.", Toast.LENGTH_SHORT).show();
                        }

                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }




            }
        });



        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
//                    첫번째 버튼 행동
//                    case R.id.login_ok_button:
///*                        //  Log.d("title2",String.valueOf(title2));
//                        Log.d("json",String.valueOf(json));
//                        // SINGLE_TOP : 이미 만들어진게 있으면 그걸 쓰고, 없으면 만들어서 써라
//
//                        if(editText_id.getText().toString() == Sign.Sign_id)
//                        {
//                            if(editText_pw.getText().toString() == json)*/
////                        ArrayList<String> list = getStringArrayPref(Sign.Sign_id);
//                        Type listType = new TypeToken<ArrayList<Member_dict>>() {}.getType();
//                        // 변환
//                        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
//                        String strContact = sp.getString("contacts", "");
//                        List<Member_dict> datas = gson.fromJson(strContact, listType);
//                        for( Member_dict data : datas) {
//                            Log.d("PRINT", data.getId());
//                            Log.d("PRINT", data.getName());
//                        }
//                        for( Member_dict data : datas)
//                        {   Log.d("PRINT", data.getId());
//                            Log.d("PRINT", data.getName());
//
//
//
//                            if(editText_id.getText().toString().equals(data.getId()) && editText_pw.getText().toString().equals(data.getPw()))
//                            {
//                                Toast.makeText(Login.this, "로그인성공.", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent (Login.this, community2.class);
//                                intent.putExtra("id", editText_id.getText().toString());
//                                startActivity(intent);
//                                break;
//                            }
//                            if(!editText_id.getText().toString().equals(data.getId()) && editText_pw.getText().toString().equals(data.getPw()))
//                            {
//                                Toast.makeText(Login.this, "로그인실패.", Toast.LENGTH_SHORT).show();
//                            }
//
//                            if(!editText_id.getText().toString().equals(data.getId()))
//                            {
//                                Toast.makeText(Login.this, "아이디가 틀리거나 존재하지않습니다.", Toast.LENGTH_SHORT).show();
//                            }
//
//                            if(!editText_pw.getText().toString().equals(data.getPw()))
//                            {
//                                Toast.makeText(Login.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
//                            }
//
//                            else
//                            {
//                                Toast.makeText(Login.this, "다시입력하세요.", Toast.LENGTH_SHORT).show();
//                            }
////                        }
//                        }


                    case R.id.login_ok_button2:

                        Intent intent_ok2 = new Intent(Login.this,Sign.class);
                        // SINGLE_TOP : 이미 만들어진게 있으면 그걸 쓰고, 없으면 만들어서 써라
                        //intent_ok2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        // 동시에 사용 가능
                        // intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // intent를 보내면서 다음 액티비티로부터 데이터를 받기 위해 식별번호(1000)을 준다.
                        startActivityForResult(intent_ok2,1000);
                        break;

                    case R.id.login_find_id_button:
                        Intent intent_id = new Intent(Login.this, Find_id.class);
                        startActivity(intent_id);
                        break;

                    case R.id.login_find_pw_button:
                        Intent intent_pw = new Intent(Login.this, Find_Pw.class);
                        startActivity(intent_pw);
                        break;
                }


            }
        };

        login_ok_button2.setOnClickListener(onClickListener);
        login_find_id_button.setOnClickListener(onClickListener);
        login_find_pw_button.setOnClickListener(onClickListener);
    }

    public void onClick(View view)
    {
        // 사용자가 입력한 아이디를 가져온다

        /*String id = editId.getText().toString();*/

        // 아이디를 인텐트에 넣어 두번째 액티비티에 보낸다.



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // setResult를 통해 받아온 요청번호, 상태, 데이터
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");

        if(requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(Login.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
            editText_id.setText(data.getStringExtra("id"));
        }
    }

    private ArrayList<String> getStringArrayPref(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("Sign_infomation",MODE_PRIVATE);
        String key_id = editText_id.getText().toString();
        String json = sharedPreferences.getString(key_id, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
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
        return urls;

    }


}
