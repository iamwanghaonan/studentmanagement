package com.wzu.pojo;

public class Massage {
    private String id;
    private String classid;
    private String title;
    private String time;
    private String detail;
    private String root;

    public Massage(String id, String classid, String title, String time, String detail, String root) {
        this.id = id;
        this.classid = classid;
        this.title = title;
        this.time = time;
        this.detail = detail;
        this.root = root;
    }

    public Massage() {
        this.id = "";
        this.classid = "";
        this.title = "";
        this.time = "";
        this.detail = "";
        this.root = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
