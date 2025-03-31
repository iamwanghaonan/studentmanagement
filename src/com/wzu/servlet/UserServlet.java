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
import java.util.List;

@WebServlet("/userList")
public class UserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAllUsers();
        System.out.println("---user POST---");
        System.out.println("users = " + users);
        request.setAttribute("users", users);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }
}