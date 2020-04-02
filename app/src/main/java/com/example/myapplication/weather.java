package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class weather extends AppCompatActivity {

private TextView dataTime;
private ProgressBar seoulbar;
private ProgressBar busanbar;
private ProgressBar progressBar;
private ProgressBar progressBar2;
private ProgressBar progressBar3;
private ProgressBar progressBar4;
private ProgressBar progressBar5;
private ProgressBar progressBar6;
private ProgressBar progressBar7;
private ProgressBar progressBar8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        dataTime = (TextView)findViewById(R.id.dataTime);
        seoulbar = (ProgressBar)findViewById(R.id.seoulbar);
        busanbar = (ProgressBar)findViewById(R.id.busanbar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressBar4);
        progressBar5 = (ProgressBar)findViewById(R.id.progressBar5);
        progressBar6 = (ProgressBar)findViewById(R.id.progressBar6);
        progressBar7 = (ProgressBar)findViewById(R.id.progressBar7);
        progressBar8 = (ProgressBar)findViewById(R.id.progressBar8);





        new MyThread().execute();

    }

    class MyThread extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params){
            Air air = Airservice.getAir();
            Log.d("air",air.toString());
            return air;
        }
        //주체 : mainThread
        @Override
        protected void onPostExecute(Object o){
            Log.d("air","onPostExecute:"+o.toString());
            Air air = (Air)o;
            dataTime.setText(air.getDataTime()+" 미세먼지");
            seoulbar.setProgress(air.getSeoul());
            busanbar.setProgress(air.getBusan());
            progressBar.setProgress(air.getDaegu());
            progressBar2.setProgress(air.getIncheon());
            progressBar3.setProgress(air.getGwangju());
            progressBar4.setProgress(air.getUlsan());
            progressBar5.setProgress(air.getGyeonggi());
            progressBar6.setProgress(air.getGangwon());
            progressBar7.setProgress(air.getChungbuk());
            progressBar8.setProgress(air.getJeju());



        }


    }
}

