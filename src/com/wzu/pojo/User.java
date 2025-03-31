package com.wzu.pojo;

public class User {
    private String id;
    private String name;
    private String password;
    private String classname;
    private int isroot;
    private String email;
    private String phone;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassname() { return classname; }

    public void setClassname(String classname) { this.classname = classname; }

    public int getIsroot() { return isroot; }

    public void setIsroot(int isroot) { this.isroot = isroot; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {this.phone = phone; }

    public User(){
        this.name = "";
        this.id = "";
        this.password = "";
        this.classname = "";
        this.isroot = 0;
        this.email = "";
        this.phone = "";
    }

    public User(String name, String id, String password){
        setName(name);
        setId(id);
        setPassword(password);
        this.classname = "";
        this.isroot = 0;
        this.email = "";
        this.phone = "";
    }

    public User(String name, String id, String password, String classname, int isroot){
        setName(name);
        setId(id);
        setPassword(password);
        setClassname(classname);
        setIsroot(isroot);
        this.email = "";
        this.phone = "";
    }

    public User(String name, String id, String password, String classname, int isroot, String email, String phone){
        setName(name);
        setId(id);
        setPassword(password);
        setClassname(classname);
        setIsroot(isroot);
        setEmail(email);
        setPhone(phone);
    }

}
