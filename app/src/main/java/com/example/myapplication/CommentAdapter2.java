package com.example.myapplication;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class CommentAdapter2 extends RecyclerView.Adapter<CommentAdapter2.CustomViewHolder> {

    private ArrayList<Comment_dict> mList;
    private ArrayList<ArrayList<Comment_dict>> mList2;
    private ArrayList<ArrayList<ArrayList<Comment_dict>>> mList3;
    private Context mContext;
    private int count = -1;    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    String formatDate2 = formatDate.substring(11,16);



    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        protected TextView id;
        protected TextView write_number;
        protected TextView contents;
        protected TextView time;

        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.textView20);
            this.write_number = (TextView) view.findViewById(R.id.textView26);
            this.contents = (TextView) view.findViewById(R.id.textView28);
            this.time = (TextView) view.findViewById(R.id.textView27);

            view.setOnCreateContextMenuListener(this); //2. OnCreateContextMenuListener 리스너를 현재 클래스에서 구현한다고 설정해둡니다.
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다. ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분하게 됩니다.

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }
        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {



            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case 1001:  // 5. 편집 항목을 선택시


                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextID.setText(mList.get(getAdapterPosition()).getContents());



                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {


                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로

                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();

                                SharedPreferences sharedPreferences2 = mContext.getSharedPreferences("Login_data", MODE_PRIVATE);
                                String login_id = sharedPreferences2.getString("ID","");

                                count++;
                                Comment_dict dict = new Comment_dict("",formatDate2,login_id,strID);


                                // 8. ListArray에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);


                                // 9. 어댑터에서 RecyclerView에 반영하도록 합니다.

                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();
                            }
                        });

                        dialog.show();


                        break;

                    case 1002:  // 5. 삭제 항목을 선택시


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


//    public CustomAdapter(ArrayList<Dictionary> list) {
//        this.mList = list;
//    }

    public CommentAdapter2(Context context, ArrayList<Comment_dict> list) {
        mList = list;
        mContext = context;

    }
//    public CommentAdapter(Context context, ArrayList<ArrayList<Comment_dict>> list2) {
//        mList2 = list2;
//        mContext = context;
//
//    }
//    public CommentAdapter(Context context, ArrayList<ArrayList<ArrayList<Comment_dict>>> list3) {
//        mList3 = list3;
//        mContext = context;
//
//    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.write_number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.contents.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        viewholder.id.setGravity(Gravity.LEFT);
        viewholder.write_number.setGravity(Gravity.CENTER);
        viewholder.contents.setGravity(Gravity.LEFT);
        viewholder.time.setGravity(Gravity.LEFT);



        viewholder.id.setText(mList.get(position).getId());
        viewholder.write_number.setText(mList.get(position).getWrite_number());
        viewholder.contents.setText(mList.get(position).getContents());
        viewholder.time.setText(mList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}