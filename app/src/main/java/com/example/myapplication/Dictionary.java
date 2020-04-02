package com.example.myapplication;

import java.util.ArrayList;

public class Dictionary implements Comparable<Dictionary> {

    public String title;
    private int write_number;
    private String category;
    private String time;
    private String id;
    private String contents;
    private ArrayList<Comment_dict> C_dict = new ArrayList<Comment_dict>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;




//    protected Dictionary(Parcel in) {
//        title = in.readString();
//        write_number = in.readString();
//        category = in.readString();
//        time = in.readString();
//        id = in.readString();
//        contents = in.readString();
//    }
//
//    public static final Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
//        @Override
//        public Dictionary createFromParcel(Parcel in) {
//            return new Dictionary(in);
//        }
//
//        @Override
//        public Dictionary[] newArray(int size) {
//            return new Dictionary[size];
//        }
//    };

    public ArrayList<Comment_dict> getC_dict() {
        return C_dict;
    }


    public void setC_dict(ArrayList<Comment_dict> c_dict) {
        C_dict = c_dict;
    }




    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWrite_number() {
        return write_number;
    }

    public void setWrite_number(int write_number) {
        this.write_number = write_number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Dictionary(String title, int write_number, String category, String time, String id, String contents,ArrayList<Comment_dict> c_dict) {
        this.title = title;
        this.write_number = write_number;
        this.category = category;
        this.time = time;
        this.id = id;
        this.contents = contents;
        this.C_dict = C_dict;

    }

    @Override
    public int compareTo(Dictionary o) {

        if(this.write_number > o.write_number) {
            return 1;
        }else if(this.write_number < o.write_number){
            return -1;
        }else{
            return 0;
        }
    }


//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        dest.writeString(title);
//        dest.writeString(write_number);
//        dest.writeString(category);
//        dest.writeString(time);
//        dest.writeString(id);
//        dest.writeString(contents);
//        dest.writeList(C_dict);
//
//    }



}
