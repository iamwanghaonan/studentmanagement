package com.wzu.dao;

import com.wzu.pojo.Apply;
import com.wzu.pojo.ClassInfo;
import com.wzu.pojo.User;
import com.wzu.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl implements ClassDao{

    @Override
    public int createClass(ClassInfo classInfo) {
        /*
            1:创建成功
            0:编号已存在
            -1:班级已存在
            -2:创建信息填写不完整
        */
        if(classInfo.getId().isEmpty() || classInfo.getClassname().isEmpty()){
            // 创建信息填写不完整
            return -2;
        }

        Connection conn = JDBCUtils.getConnection();//链接

        String findClass="select * from class";
        try {
            PreparedStatement ps = conn.prepareStatement(findClass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String classname = rs.getString("classname");
                if(classInfo.getId().equals(id))  // 编号已存在
                    return 0;
                if(classInfo.getClassname().equals(classname))  // 班级已存在
                    return -1;
            }
            rs.close();
            ps.close(); // 关闭 PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 创建班级成功
        String sql = "INSERT INTO class (id, classname, root) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classInfo.getId());
            ps.setString(2, classInfo.getClassname());
            ps.setString(3, classInfo.getRoot());
            ps.executeUpdate();
            ps.close(); // 关闭 PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // 创建数据表
        String sqlCreateTable = String.format("CREATE TABLE class_%s (id CHAR(20))", classInfo.getId());
        try {
            Statement st = conn.createStatement();
            int row = st.executeUpdate(sqlCreateTable);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 在 finally 块中关闭连接，以确保无论如何都会被关闭
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return 1;
    }

    @Override
    public List<ClassInfo> getClassInfo() {
        List<ClassInfo> classInfos = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = "SELECT * FROM class";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setId(rs.getString("id"));
                classInfo.setClassname(rs.getString("classname"));
                classInfo.setRoot(rs.getString("root"));

                classInfos.add(classInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classInfos;
    }

    @Override
    public boolean joinClass(String userId, String userName, String classId, String className) {
        Connection conn = JDBCUtils.getConnection();//链接
        //查找申请是否已存在
        String find = "SELECT * FROM applyClass";
        try {
            PreparedStatement ps = conn.prepareStatement(find);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String classid = rs.getString("classid");
                if(classId.equals(classid) && userId.equals(id))  // 申请已存在
                    return true;
            }
            rs.close();
            ps.close(); // 关闭 PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //申请不存在则添加记录
        String sql = "INSERT INTO applyClass (id, name, classid, classname) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, userName);
            ps.setString(3, classId);
            ps.setString(4, className);
            ps.executeUpdate();
            ps.close(); // 关闭 PreparedStatement
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Apply> getApplyDetail(String classid) {
        List<Apply> applies = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = "SELECT * FROM applyClass";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getString("classid").equals(classid)){
                    Apply apply = new Apply();
                    apply.setId(rs.getString("id"));
                    apply.setName(rs.getString("name"));
                    apply.setClassname(rs.getString("classname"));
                    apply.setClassid(rs.getString("classid"));

                    applies.add(apply);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applies;
    }

    @Override
    public boolean delApply(String id) {
        Connection conn = JDBCUtils.getConnection();//链接
        //查找申请是否已存在
        String sql = "delete from applyClass where id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getClassName(String classid) {
        Connection conn = JDBCUtils.getConnection();//链接
        String find = "SELECT * FROM class";
        try {
            PreparedStatement ps = conn.prepareStatement(find);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                if(classid.equals(id)){
                    return rs.getString("classname");
                }
            }
            rs.close();
            ps.close(); // 关闭 PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getClassId(String classname) {
        Connection conn = JDBCUtils.getConnection();//链接
        String find = "SELECT * FROM class";
        try {
            PreparedStatement ps = conn.prepareStatement(find);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("classname");
                if(classname.equals(name)){
                    return rs.getString("classid");
                }
            }
            rs.close();
            ps.close(); // 关闭 PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delClass(String classid) {
        Connection conn = JDBCUtils.getConnection();//链接
        String classname = getClassName(classid);
        String sql = "update user set classname=? where classname=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "");
            ps.setString(2, classname);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String del_1 = "delete from class where id=?";
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement(del_1);
            ps.setString(1,classid);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String del_2 = "DROP TABLE class_" + classid;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(del_2);
            statement.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean userAddClass(String id, String classid) {
        Connection conn = JDBCUtils.getConnection();//链接
        String sql = "INSERT INTO class_" + classid + "(id) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeClassUser(String id, String classid) {
        Connection conn = JDBCUtils.getConnection();//链接
        String classname = getClassName(classid);
        String sql = "update user set classname=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "");
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String del_1 = "delete from class_" + classid + " where id=?";
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement(del_1);
            ps.setString(1,id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
