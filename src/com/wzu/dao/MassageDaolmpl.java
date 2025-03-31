package com.wzu.dao;

import com.wzu.pojo.Massage;
import com.wzu.pojo.MassageReply;
import com.wzu.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MassageDaolmpl implements MassageDao {
    @Override
    public List<Massage> getMassage() {
        List<Massage> Massages = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = " SELECT * FROM massage";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Massage Massage = new Massage();
                Massage.setId(rs.getString("id"));
                Massage.setClassid(rs.getString("classid"));
                Massage.setTitle(rs.getString("title"));
                Massage.setTime(rs.getString("time"));
                Massage.setDetail(rs.getString("detail"));
                Massage.setRoot(rs.getString("root"));
                Massages.add(Massage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Massages;
    }

    @Override
    public boolean addMassage(Massage massage) {
        Connection conn = JDBCUtils.getConnection(); // 链接数据库
        String sql = "INSERT INTO massage (id, classid, title, time, detail, root) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, massage.getId());
            ps.setString(2, massage.getClassid());
            ps.setString(3, massage.getTitle());
            ps.setString(4, massage.getTime());
            ps.setString(5, massage.getDetail());
            ps.setString(6, massage.getRoot());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 如果有SQLException也返回false
        } finally {
            // 关闭数据库连接等资源（如果有必要）
            // JDBCUtils.close(...);
        }
        String sqlCreateTable = String.format("CREATE TABLE message_%s (id CHAR(20),name CHAR(10),time CHAR(20),detail CHAR(20))", massage.getId());
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

        return true;

    }
    @Override
    public boolean replyMassage(MassageReply massageReply, String messageid) {
        System.out.println("dao:" + messageid);
        Connection conn = JDBCUtils.getConnection(); // 链接数据库
        String sql = String.format("INSERT INTO message_%s (id, name, time, detail) VALUES (?, ?, ?, ?)", messageid);
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, massageReply.getId());
            ps.setString(2, massageReply.getName());
            ps.setString(3, massageReply.getTime());
            ps.setString(4, massageReply.getDetail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public List<MassageReply> getReply(String id) {
        List<MassageReply> massageReplys = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = String.format("SELECT * FROM message_%s",id);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MassageReply massageReply = new MassageReply();
                massageReply.setId(rs.getString("id"));
                massageReply.setName(rs.getString("name"));
                massageReply.setTime(rs.getString("time"));
                massageReply.setDetail(rs.getString("detail"));
                massageReplys.add(massageReply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return massageReplys;
    }

    @Override
    public Massage getMessageInfo(String messageid) {
        try (Connection conn = JDBCUtils.getConnection()) {
            String sql = " SELECT * FROM massage";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(messageid.equals(rs.getString("id"))){
                    Massage massage = new Massage();
                    massage.setId(rs.getString("id"));
                    massage.setClassid(rs.getString("classid"));
                    massage.setTitle(rs.getString("title"));
                    massage.setTime(rs.getString("time"));
                    massage.setDetail(rs.getString("detail"));
                    massage.setRoot(rs.getString("root"));
                    return massage;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
