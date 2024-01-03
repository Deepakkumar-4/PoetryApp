package com.example.quoteapp.Model;

public class PoetryModel {
    String id;
    String poetryText;
    String poetName;
    String timeStamp;

    public PoetryModel(String id, String poetryText, String poetName, String timeStamp) {
        this.id = id;
        this.poetryText = poetryText;
        this.poetName = poetName;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoetryText() {
        return poetryText;
    }

    public void setPoetryText(String poetryText) {
        this.poetryText = poetryText;
    }

    public String getPoetName() {
        return poetName;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
