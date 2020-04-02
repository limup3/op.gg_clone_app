package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game extends AppCompatActivity {

    TextView time ;
    TextView count;
    ImageButton start;

    ImageView[] img_array = new ImageView[9];
    int[] imageID = {R.id.game_icon1, R.id.game_icon2, R.id.game_icon3, R.id.game_icon4, R.id.game_icon5, R.id.game_icon6, R.id.game_icon7, R.id.game_icon8, R.id.game_icon9};

    final String TAG_ON = "on"; //태그용
    final String TAG_OFF = "off";
    int score = 0;

//    ImageView img1;
//    ImageView img2;
//    ImageView img3;
//    ImageView img4;
//    ImageView img5;
//    ImageView img6;
//    ImageView img7;
//    ImageView img8;
//    ImageView img9;

    Thread thread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        time=findViewById(R.id.time);
        count=findViewById(R.id.count);
        start=findViewById(R.id.start);
//        img1=findViewById(R.id.game_icon1);
//        img2=findViewById(R.id.game_icon2);
//        img3=findViewById(R.id.game_icon3);
//        img4=findViewById(R.id.game_icon4);
//        img5=findViewById(R.id.game_icon5);
//        img6=findViewById(R.id.game_icon6);
//        img7=findViewById(R.id.game_icon7);
//        img8=findViewById(R.id.game_icon8);
//        img9=findViewById(R.id.game_icon9);

        for(int i = 0; i<img_array.length; i++){
            /*int img_id = getResources().getIdentifier("imageView"+i+1, "id", "com.example.pc_20.molegame");*/
            img_array[i] = (ImageView)findViewById(imageID[i]);
            img_array[i].setImageResource(R.drawable.teemo1);
            img_array[i].setTag(TAG_OFF);


            img_array[i].setOnClickListener(new View.OnClickListener() { //두더지이미지에 온클릭리스너
                @Override
                public void onClick(View v) {
                    if(((ImageView)v).getTag().toString().equals(TAG_ON)){
                        Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_LONG).show();
                        count.setText(String.valueOf(score++));
                        ((ImageView) v).setImageResource(R.drawable.teemo1);
                        v.setTag(TAG_OFF);
                    }else{
                        Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_LONG).show();
                        if(score<=0){
                            score=0;
                            count.setText(String.valueOf(score));
                        }else{
                            count.setText(String.valueOf(score--));
                        }
                        ((ImageView) v).setImageResource(R.drawable.teemo2);
                        v.setTag(TAG_ON);
                    }
                }
            });
        }

        time.setText("15초");
        count.setText("0마리");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start.setVisibility(View.GONE);
                count.setVisibility(View.VISIBLE);

                new Thread(new timeCheck()).start();

                for(int i = 0; i<img_array.length; i++){
                    new Thread(new DThread(i)).start();
                }
            }
        });
    }

    Handler onHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            img_array[msg.arg1].setImageResource(R.drawable.teemo2);
            img_array[msg.arg1].setTag(TAG_ON); //올라오면 ON태그 달아줌
        }
    };

    Handler offHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            img_array[msg.arg1].setImageResource(R.drawable.teemo1);
            img_array[msg.arg1].setTag(TAG_OFF); //내려오면 OFF태그 달아줌

        }
    };

    public class DThread implements Runnable{ //두더지를 올라갔다 내려갔다 해줌
        int index = 0; //두더지 번호

        DThread(int index){
            this.index=index;
        }

        @Override
        public void run() {
            while(true){
                try {
                    Message msg1 = new Message();
                    int offtime = new Random().nextInt(5000) + 1000 ;
                    Thread.sleep(offtime); //두더지가 내려가있는 시간

                    msg1.arg1 = index;
                    onHandler.sendMessage(msg1);

                    int ontime = new Random().nextInt(1000)+1000;
                    Thread.sleep(ontime); //두더지가 올라가있는 시간
                    Message msg2 = new Message();
                    msg2.arg1= index;
                    offHandler.sendMessage(msg2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            time.setText(msg.arg1 + "초");
        }
    };

    public class timeCheck implements Runnable {
        final int MAXTIME = 15;

        @Override
        public void run() {
            for (int i = MAXTIME; i >= 0; i--) {
                Message msg = new Message();
                msg.arg1 = i;
                handler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(game.this, game_result.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }
}