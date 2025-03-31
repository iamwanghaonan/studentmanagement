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

@WebServlet("/saveUserInfo")
public class saveUserInfoServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        User user = new User("", id, "", "", 0, email, phone);
        System.out.println("---saveUserInfo POST---");
        System.out.println("id: " + user.getId());
        System.out.println("email: " + user.getEmail());
        System.out.println("phone: " + user.getPhone());
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(userService.saveUserInfo(user)){
            out.write("Save succeed");
        }else{
            out.write("Save failed");
        }
    }
}
