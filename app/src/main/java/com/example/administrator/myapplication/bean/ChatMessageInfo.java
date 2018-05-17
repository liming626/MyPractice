package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2018/5/17.
 */

public class ChatMessageInfo {
    private String message;
    private String time;
    private int type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
