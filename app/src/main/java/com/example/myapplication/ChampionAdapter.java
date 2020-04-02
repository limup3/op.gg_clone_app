package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChampionAdapter  extends RecyclerView.Adapter<ChampionAdapter.ItemViewHolder> {

    private ArrayList<Champion_dict> listData = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_list, parent, false);
        return new ItemViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));


    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Champion_dict data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
// 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {


        private ImageView chams1;
        private ImageView chams2;
        private ImageView chams3;

        ItemViewHolder(View itemView) {
            super(itemView);

            chams1 = itemView.findViewById(R.id.imageView9);
            chams2 = itemView.findViewById(R.id.imageView11);
            chams3 = itemView.findViewById(R.id.imageView13);
        }

        void onBind(Champion_dict data) {

            chams1.setImageResource(data.getChams1());
            chams2.setImageResource(data.getChams2());
            chams3.setImageResource(data.getChams3());

            chams1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://universe.leagueoflegends.com/ko_KR/champions/"));
                    context.startActivity(intent);
                }
            });

            chams2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://universe.leagueoflegends.com/ko_KR/champions/"));
                    context.startActivity(intent);
                }
            });

            chams3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://universe.leagueoflegends.com/ko_KR/champions/"));
                    context.startActivity(intent);
                }
            });
        }


    }
}