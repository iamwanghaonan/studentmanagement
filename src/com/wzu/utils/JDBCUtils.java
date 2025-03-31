package com.wzu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    static{
        InputStream in = JDBCUtils.class.getClassLoader()
                .getResourceAsStream("db.properties");
        Properties properties=new Properties();
        try {
            properties.load(in);
            driver = properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(){
          Connection conn=null;
        try {
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void release(ResultSet rs, Statement st,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


