package com.wzu.service;

import com.wzu.dao.UserDao;
import com.wzu.dao.UserDaoImpl;
import com.wzu.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();

    @Override
    public User userLogin(User user) {
        return userDao.login(user);
    }

    @Override
    public int inSert(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean changeClass(String id, String classname) {
        return userDao.changeClass(id, classname);
    }

    @Override
    public List<User> getClassUser(String classname) {
        return userDao.getClassUser(classname);
    }

    @Override
    public boolean saveUserInfo(User user) { return userDao.saveUserInfo(user); }

    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword) {
        return userDao.changePassword(id, oldPassword, newPassword);
    }

    @Override
    public User getUserInfo(String id) { return userDao.getUserInfo(id); }

}
