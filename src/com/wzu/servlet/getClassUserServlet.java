package com.wzu.servlet;

import com.wzu.pojo.ClassInfo;
import com.wzu.pojo.User;
import com.wzu.service.ClassService;
import com.wzu.service.ClassServiceImpl;
import com.wzu.service.UserService;
import com.wzu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/classUserInfo")
public class getClassUserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    ClassService classService = new ClassServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classname = "";
        String classid = req.getParameter("classid");
        if(classid != null && classid != ""){
            classname = classService.getClassName(classid);
        }else {
            classname = req.getParameter("classname");
        }
        // String classname = classService.getClassName(classid);
        // String classname = req.getParameter("classname");
        List<User> classUserInfos = userService.getClassUser(classname);
        System.out.println("---getClassUserInfo---");
        //System.out.println("classid = " + classid);
        System.out.println("classname = " + classname);
        // 将班级列表数据转换为 JSON 格式
        String jsonClassInfos = convertClassInfosToJson(classUserInfos);
        System.out.println("classUserInfos:" + jsonClassInfos);
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取响应的写入流
        PrintWriter out = resp.getWriter();
        // 将 JSON 数据写入响应
        out.print(jsonClassInfos);
        out.flush();
    }

    private String convertClassInfosToJson(List<User> classUserInfos) {
        StringBuilder json = new StringBuilder("{\"classUserInfos\":[");
        for (int i = 0; i < classUserInfos.size(); i++) {
            User classUserInfo = classUserInfos.get(i);
            json.append("{\"id\":\"").append(classUserInfo.getId()).append("\",")
                    .append("\"name\":\"").append(classUserInfo.getName()).append("\",")
                    .append("\"email\":\"").append(classUserInfo.getEmail()).append("\",")
                    .append("\"phone\":\"").append(classUserInfo.getPhone()).append("\"}");

            if (i < classUserInfos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]}");
        return json.toString();
    }
}
