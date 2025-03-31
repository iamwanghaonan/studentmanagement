package com.wzu.service;

import com.wzu.pojo.User;

import java.util.List;

public interface UserService {
    User userLogin(User user);
    int inSert(User user);
    List<User> findAllUsers();
    boolean changeClass(String id, String classname);
    List<User> getClassUser(String classname);
    boolean saveUserInfo(User user);
    boolean changePassword(String id, String oldPassword, String newPassword);
    User getUserInfo(String id);
}
