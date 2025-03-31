package com.wzu;

import javax.swing.tree.VariableHeightLayoutCache;
import java.sql.*;

public class First {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/classweb?serverTimezone=UTC","root","root");
            String sql="select * from user";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("name");
                String password = rs.getString("password");
                System.out.println(id+"---"+username+"---"+password);
            }
            rs.close();
            statement.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
