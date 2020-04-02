package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;
    private Context context;
    private Intent intent;
    Gson gson;








    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected TextView write_number;
        protected TextView title;
        protected TextView category;
        protected TextView time;
        protected TextView id;



        public CustomViewHolder(View view) {
            super(view);
            this.write_number = (TextView) view.findViewById(R.id.textView41);
            this.title = (TextView) view.findViewById(R.id.textView42);
            this.category = (TextView) view.findViewById(R.id.textView43);
            this.time = (TextView) view.findViewById(R.id.textView44);
            this.id = (TextView) view.findViewById(R.id.textView45);
            view.setOnCreateContextMenuListener(this);





        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분하게 됩니다.

            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Delete.setOnMenuItemClickListener(onEditMenu);

    }



        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {



            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                case 1002:
                    // 6. ArratList에서 해당 데이터를 삭제하고
                mList.remove(getAdapterPosition());
                    // 7. 어댑터에서 RecyclerView에 반영하도록 합니다.
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), mList.size());



                break;
                }
                return true;
            }
        };



    }

    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;



    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.community_list, viewGroup, false);

        CustomViewHolder

                viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {


        viewholder.write_number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);


        viewholder.write_number.setGravity(Gravity.CENTER);
        viewholder.title.setGravity(Gravity.LEFT);
        viewholder.category.setGravity(Gravity.LEFT);
        viewholder.time.setGravity(Gravity.LEFT);
        viewholder.id.setGravity(Gravity.LEFT);


        viewholder.write_number.setText(String.valueOf(mList.get(position).getWrite_number()));
        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.category.setText(mList.get(position).getCategory());
        viewholder.time.setText(mList.get(position).getTime());
        viewholder.id.setText(mList.get(position).getId());






//        complete.setOnClickListener(new View.OnClickListener() {





    }






    @Override
    public int getItemCount() {

/*        return (null != mList ? mList.size() : 0);*/

        return mList.size();
    }



//    public void filter(String charText) {
//
//        ArrayList<Dictionary> listdata = new ArrayList<>();
//        Dictionary mListData;
//        ArrayList<Dictionary> arrayList = new ArrayList<Dictionary>();
//
//        charText = charText.toLowerCase(Locale.getDefault());
//        listdata.clear();
//
//        if(charText.length() == 0)
//        {
//            listdata.addAll(mList);
//        } else {
//            for(Dictionary potion : mList) {
//                String name = potion.title;
//                if(name.toLowerCase().contains(charText)) {
//                    listdata.add(potion);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}