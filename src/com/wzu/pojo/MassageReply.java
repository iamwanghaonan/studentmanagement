package com.wzu.pojo;

public class MassageReply {
    private String id;

    private String name;

    private String time;

    private String detail;

    public MassageReply(String id, String name, String time, String detail) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.detail = detail;
    }
    public MassageReply() {
        this.id = "";
        this.name = "";
        this.time = "";
        this.detail = "";
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
