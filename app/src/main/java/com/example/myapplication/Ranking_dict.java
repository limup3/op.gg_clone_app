package com.example.myapplication;

public class Ranking_dict {

    private String summoner;
    private String rnak_number;
    private String score;
    private String kda;
    private int icon;
    private int score_icon;

    public Ranking_dict(String summoner, String rnak_number, String score, String kda, int icon, int score_icon) {
        this.summoner = summoner;
        this.rnak_number = rnak_number;
        this.score = score;
        this.kda = kda;
        this.icon = icon;
        this.score_icon = score_icon;
    }

    public String getSummoner() {
        return summoner;
    }

    public void setSummoner(String summoner) {
        this.summoner = summoner;
    }

    public String getRnak_number() {
        return rnak_number;
    }

    public void setRnak_number(String rnak_number) {
        this.rnak_number = rnak_number;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getScore_icon() {
        return score_icon;
    }

    public void setScore_icon(int score_icon) {
        this.score_icon = score_icon;
    }

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

}
