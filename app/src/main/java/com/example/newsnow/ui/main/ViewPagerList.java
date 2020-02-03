package com.example.newsnow.ui.main;

public class ViewPagerList {
    private String url;
    private String headline;
    private String time;
    private String discriptione;

    public ViewPagerList(String url, String headline, String time, String discriptione) {
        this.url = url;
        this.headline = headline;
        this.time = time;
        this.discriptione = discriptione;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiscriptione() {
        return discriptione;
    }

    public void setDiscriptione(String discriptione) {
        this.discriptione = discriptione;
    }
}
