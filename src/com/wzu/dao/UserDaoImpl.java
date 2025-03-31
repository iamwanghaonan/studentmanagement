package com.wzu.dao;

import com.wzu.pojo.User;
import com.wzu.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(User user) {
        Connection conn = JDBCUtils.getConnection();

        String sql="select * from user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String password = rs.getString("password");
                if(user.getId().equals(id) && user.getPassword().equals(password)) {
                    String name = rs.getString("name");
                    String classname = rs.getString("classname");
                    int isroot = rs.getInt("isroot");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    User userInfo = new User(name, id, password, classname, isroot, email, phone);
                    return userInfo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(User user) {
        /*
            1:注册成功
            0:注册信息填写不完整
            -1:学号已注册
        */
        if(user.getName().isEmpty() || user.getId().isEmpty() || user.getPassword().isEmpty()){
            // 注册信息填写不完整
            return 0;
        }

        Connection conn = JDBCUtils.getConnection();//链接

        String findUser="select id from user";
        try {
            PreparedStatement ps = conn.prepareStatement(findUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                if(user.getId().equals(id))  // 学号已注册
                    return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 注册成功
        String sql = "INSERT INTO user (name, id, password, classname, isroot) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getId());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getClassname());
            ps.setInt(5, user.getIsroot());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<User> findAll() {
        System.out.println("我正在直行findAll");
        List<User> users = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = "SELECT id, password FROM user";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("getting spotting");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean changeClass(String id, String classname) {
        Connection conn = JDBCUtils.getConnection();//链接
        String sql = "update user set classname=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classname);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getClassUser(String classname) {
        List<User> users = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = "SELECT * FROM user";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("getting spotting");
            while (rs.next()) {
                User user = new User();
                user.setClassname(rs.getString("classname"));
                System.out.println(user.getClassname());
                if(Objects.equals(classname, user.getClassname())){
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean saveUserInfo(User user) {
        Connection conn = JDBCUtils.getConnection();//链接
        String sql = "update user set email=?,phone=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword) {
        Connection conn = JDBCUtils.getConnection();//链接
        String sql="select * from user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
                    String userPassword = rs.getString("password");
                    System.out.println("dao:   " + userPassword);
                    if(!Objects.equals(userPassword, oldPassword)){
                        return false;
                    }else{
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "update user set password=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserInfo(String id) {
        Connection conn = JDBCUtils.getConnection();
        String sql="select * from user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String classname = rs.getString("classname");
                    int isroot = rs.getInt("isroot");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    User user =  new User(name, id, password, classname, isroot, email, phone);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
