package com.wzu.servlet;

import com.wzu.pojo.User;
import com.wzu.service.UserService;
import com.wzu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        User user=new User();
        user.setPassword(password);
        user.setId(id);
        System.out.println("---login POST---");
        System.out.println("id: " + user.getId());
        System.out.println("password: " + user.getPassword());
        User userInfo = userService.userLogin(user);
        System.out.println(userInfo);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(userInfo != null){
            System.out.println("1");
            Cookie ucookie=new Cookie("id",id);
            Cookie pcookie=new Cookie("password",password);
            ucookie.setMaxAge(60*60*24*30);
            pcookie.setMaxAge(60*60*24*30);
            ucookie.setPath(req.getContextPath());
            pcookie.setPath(req.getContextPath());
            resp.addCookie(ucookie);
            resp.addCookie(pcookie);
            // 登录成功时，将用户信息以JSON格式返回给前端
            HttpSession session = req.getSession();
            session.setAttribute("userId", id);
            String userInfoJson = getUserInfoJson(userInfo);
            out.write(userInfoJson);
        }else{
            out.write("fail");
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
