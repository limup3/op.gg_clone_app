package com.example.myapplication;

public class Comment_dict {

    private String write_number;
    private String time;
    private String id;
    private String contents;

    public String getWrite_number() {
        return write_number;
    }

    public void setWrite_number(String write_number) {
        this.write_number = write_number;
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Comment_dict(String write_number, String time, String id, String contents) {
        this.write_number = write_number;
        this.time = time;
        this.id = id;
        this.contents = contents;
    }
}
