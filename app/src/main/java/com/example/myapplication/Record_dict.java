package com.example.myapplication;

import android.graphics.Bitmap;

public class Record_dict {

    private String Win;
    private String Bar;
    private String Time;
    private String Kda;
    private String kill_per;
    private String Type;
    private String Day;
    private Bitmap Summer_icon;
    private Bitmap Spell1;
    private Bitmap Spell2;
    private Bitmap Char1;
    private Bitmap Char2;
    private Bitmap Item1;
    private Bitmap Item2;
    private Bitmap Item3;
    private Bitmap Item4;
    private Bitmap Item5;
    private Bitmap Item6;
    private Bitmap Item7;

    public Bitmap getSpell1() {
        return Spell1;
    }

    public void setSpell1(Bitmap spell1) {
        Spell1 = spell1;
    }

    public Bitmap getSpell2() {
        return Spell2;
    }

    public void setSpell2(Bitmap spell2) {
        Spell2 = spell2;
    }

    public Bitmap getChar1() {
        return Char1;
    }

    public void setChar1(Bitmap char1) {
        Char1 = char1;
    }

    public Bitmap getChar2() {
        return Char2;
    }

    public void setChar2(Bitmap char2) {
        Char2 = char2;
    }

    public Bitmap getItem1() {
        return Item1;
    }

    public void setItem1(Bitmap item1) {
        Item1 = item1;
    }

    public Bitmap getItem2() {
        return Item2;
    }

    public void setItem2(Bitmap item2) {
        Item2 = item2;
    }

    public Bitmap getItem3() {
        return Item3;
    }

    public void setItem3(Bitmap item3) {
        Item3 = item3;
    }

    public Bitmap getItem4() {
        return Item4;
    }

    public void setItem4(Bitmap item4) {
        Item4 = item4;
    }

    public Bitmap getItem5() {
        return Item5;
    }

    public void setItem5(Bitmap item5) {
        Item5 = item5;
    }

    public Bitmap getItem6() {
        return Item6;
    }

    public void setItem6(Bitmap item6) {
        Item6 = item6;
    }

    public Bitmap getItem7() {
        return Item7;
    }

    public void setItem7(Bitmap item7) {
        Item7 = item7;
    }

    public Bitmap getSummer_icon() {
        return Summer_icon;
    }

    public void setSummer_icon(Bitmap summer_icon) {
        Summer_icon = summer_icon;
    }

    public String getWin() {
        return Win;
    }

    public void setWin(String win) {
        Win = win;
    }

    public String getBar() {
        return Bar;
    }

    public void setBar(String bar) {
        Bar = bar;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getKda() {
        return Kda;
    }

    public void setKda(String kda) {
        Kda = kda;
    }

    public String getKill_per() {
        return kill_per;
    }

    public void setKill_per(String kill_per) {
        this.kill_per = kill_per;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public Record_dict(String win, String kda, String kill_per, Bitmap summer_icon, Bitmap spell1, Bitmap spell2, Bitmap char1, Bitmap char2, Bitmap item1, Bitmap item2, Bitmap item3, Bitmap item4, Bitmap item5, Bitmap item6, Bitmap item7) {
        Win = win;
        Kda = kda;
        this.kill_per = kill_per;
        Summer_icon = summer_icon;
        Spell1 = spell1;
        Spell2 = spell2;
        Char1 = char1;
        Char2 = char2;
        Item1 = item1;
        Item2 = item2;
        Item3 = item3;
        Item4 = item4;
        Item5 = item5;
        Item6 = item6;
        Item7 = item7;
    }
}
