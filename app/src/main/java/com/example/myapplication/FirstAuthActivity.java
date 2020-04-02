//package com.example.myapplication;
//
//import android.app.AppComponentFactory;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.example.myapplication.ui.login.LoginActivity;
//
//public class FirstAuthActivity extends AppComponentFactory {
//
//    private Intent intent;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first_auth);
//
//        if(SaveSharedPreference.getUserName(FirstAuthActivity.this).length() == 0) {
//            // call Login Activity
//            intent = new Intent(FirstAuthActivity.this, LoginActivity.class);
//            startActivity(intent);
//            this.finish();
//        } else {
//            // Call Next Activity
//            intent = new Intent(FirstAuthActivity.this, HomeActivity.class);
//            intent.putExtra("STD_NUM", SaveSharedPreference.getUserName(this).toString());
//            startActivity(intent);
//            this.finish();
//        }
//    }
//}