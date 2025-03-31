package com.wzu.pojo;

public class ClassInfo {
    private String id;
    private String classname;
    private String root;

    public String getId() { return id; }
    public String getClassname() { return classname; }
    public String getRoot() { return root; }
    public void setId(String id) { this.id = id; }
    public void setClassname(String classname) { this.classname = classname; }
    public void setRoot(String root) { this.root = root; }
    public ClassInfo(){
        setId("");
        setClassname("");
        setRoot("");
    }
    public ClassInfo(String id, String classname, String root){
        setId(id);
        setClassname(classname);
        setRoot(root);
    }
}
