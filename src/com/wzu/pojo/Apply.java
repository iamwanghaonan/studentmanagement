package com.wzu.pojo;

public class Apply {
    private String id;
    private String name;
    private String classid;
    private String classname;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassid() {
        return classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    public Apply(){
        this.id = "";
        this.name = "";
        this.classid = "";
        this.classname = "";
    }
    public Apply(String id, String name, String classid, String classname){
        setId(id);
        setName(name);
        setClassid(classid);
        setClassname(classname);
    }
}
