package com.wzu.servlet;

import com.wzu.service.UserService;
import com.wzu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changePassword")
public class changePasswordServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        System.out.println("---changePassword POST---");
        System.out.println("id: " + id);
        System.out.println("oldPassword: " + oldPassword);
        System.out.println("newPassword: " + newPassword);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(userService.changePassword(id, oldPassword, newPassword)){
            out.write("Change succeed");
        }else{
            out.write("Change failed");
        }
    }
}
