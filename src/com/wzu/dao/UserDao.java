package com.wzu.dao;

import com.wzu.pojo.User;

import java.util.List;

public interface UserDao {
    User login(User user);
    int insert(User user);
    List<User> findAll();
    boolean changeClass(String id, String classname);
    List<User> getClassUser(String classname);
    boolean saveUserInfo(User user);
    boolean changePassword(String id, String oldPassword, String newPassword);
    User getUserInfo(String id);
}
