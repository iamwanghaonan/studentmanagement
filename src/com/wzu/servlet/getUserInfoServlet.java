package com.wzu.servlet;

import com.wzu.pojo.User;
import com.wzu.service.UserService;
import com.wzu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getUserInfo")
public class getUserInfoServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println("---getUserInfo POST---");
        System.out.println("id: " + id);
        User user = userService.getUserInfo(id);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (user != null){
            String userInfoJson = getUserInfoJson(user);
            System.out.println(userInfoJson);
            out.write(userInfoJson);
        }
    }

    private String getUserInfoJson(User user) {
        // 使用JSON库将User对象转换成JSON格式字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // 处理异常时返回一个空JSON对象
        }

    }
}
