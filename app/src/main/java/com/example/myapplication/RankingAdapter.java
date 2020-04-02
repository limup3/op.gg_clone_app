package com.example.myapplication;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ItemViewHolder> {

    private ArrayList<Ranking_dict> listData = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



    // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
    // return 인자는 ViewHolder 입니다.
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_list, parent, false);
        return new

    ItemViewHolder(view);


}


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));

        holder.rank_number.setGravity(Gravity.CENTER);


    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Ranking_dict data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

// RecyclerView의 핵심인 ViewHolder 입니다.
// 여기서 subView를 setting 해줍니다.
class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView rank_number;
    private TextView summoner;
    private TextView score;
    private TextView kda;
    private ImageView icon;
    private ImageView score_icon;

    ItemViewHolder(View itemView) {
        super(itemView);

        rank_number = itemView.findViewById(R.id.textView10);
        kda = itemView.findViewById(R.id.textView46);
        summoner = itemView.findViewById(R.id.textView17);
        score = itemView.findViewById(R.id.textView19);
        icon = itemView.findViewById(R.id.imageView10);
        score_icon = itemView.findViewById(R.id.imageView12);
    }

    void onBind(Ranking_dict data) {
        rank_number.setText(data.getRnak_number());
        kda.setText(data.getKda());
        summoner.setText(data.getSummoner());
        score.setText(data.getScore());
        icon.setImageResource(data.getIcon());
        score_icon.setImageResource(data.getScore_icon());
    }
}
}