package com.example.myapplication;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class RecordAdapter extends RecyclerView.Adapter<com.example.myapplication.RecordAdapter.ItemViewHolder> {

        private ArrayList<Record_dict> listData = new ArrayList<>();


//        public RecordAdapter(ArrayList<Record_dict> listdata) {
//        }

        @NonNull
        @Override
        public com.example.myapplication.RecordAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



            // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
            // return 인자는 ViewHolder 입니다.
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_list, parent, false);
            return new ItemViewHolder(view);


        }


        @Override
        public void onBindViewHolder(@NonNull com.example.myapplication.RecordAdapter.ItemViewHolder holder, int position) {
            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
            holder.onBind(listData.get(position));
        Log.d("확인7", String.valueOf(listData.get(position)));


        }

        @Override
        public int getItemCount() {
            // RecyclerView의 총 개수 입니다.
            return listData.size();
        }

        void addItem(Record_dict data) {
            // 외부에서 item을 추가시킬 함수입니다.
            listData.add(data);
        }

        // RecyclerView의 핵심인 ViewHolder 입니다.
// 여기서 subView를 setting 해줍니다.
        class ItemViewHolder extends RecyclerView.ViewHolder {

            private TextView Win;
            private TextView Kda;
            private TextView Kill_per;
            private ImageView Summer_icon;
            private ImageView Spell1;
            private ImageView Spell2;
            private ImageView Char1;
            private ImageView Char2;
            private ImageView Item1;
            private ImageView Item2;
            private ImageView Item3;
            private ImageView Item4;
            private ImageView Item5;
            private ImageView Item6;
            private ImageView Item7;
            private FrameLayout Mlayout;


            ItemViewHolder(View itemView) {
                super(itemView);
                Mlayout = (FrameLayout)itemView.findViewById(R.id.frameLayout2);
                Win = itemView.findViewById(R.id.Win);
                Kda = itemView.findViewById(R.id.Kda);
                Kill_per = itemView.findViewById(R.id.Kill_per);
                Summer_icon = itemView.findViewById(R.id.Summoner_icon);
                Spell1 = itemView.findViewById(R.id.Spell1);
                Spell2 = itemView.findViewById(R.id.Spell2);
                Char1 = itemView.findViewById(R.id.char1);
                Char2 = itemView.findViewById(R.id.char2);
                Item1 = itemView.findViewById(R.id.Item1);
                Item2 = itemView.findViewById(R.id.Item2);
                Item3 = itemView.findViewById(R.id.Item3);
                Item4 = itemView.findViewById(R.id.Item4);
                Item5 = itemView.findViewById(R.id.Item5);
                Item6 = itemView.findViewById(R.id.Item6);
                Item7 = itemView.findViewById(R.id.Item7);
            }

            void onBind(Record_dict data) {
                Win.setText(data.getWin());
                if(data.getWin() == "승리")
                {
                    Mlayout.setBackgroundColor(Color.rgb(0,0,255));
                }
                if(data.getWin() == "패배")
                {
                    Mlayout.setBackgroundColor(Color.rgb(255,0,0));
                }
                Kda.setText(data.getKda());
                Kill_per.setText(data.getKill_per());
                Summer_icon.setImageBitmap(data.getSummer_icon());
                Spell1.setImageBitmap(data.getSpell1());
                Spell2.setImageBitmap(data.getSpell2());
                Char1.setImageBitmap(data.getChar1());
                Char2.setImageBitmap(data.getChar2());
                Item1.setImageBitmap(data.getItem1());
                Item2.setImageBitmap(data.getItem2());
                Item3.setImageBitmap(data.getItem3());
                Item4.setImageBitmap(data.getItem4());
                Item5.setImageBitmap(data.getItem5());
                Item6.setImageBitmap(data.getItem6());
                Item7.setImageBitmap(data.getItem7());

            }
        }
    }
