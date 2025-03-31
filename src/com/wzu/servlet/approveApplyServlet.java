package com.wzu.servlet;

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
import java.util.HashMap;

@WebServlet("/approveApply")
public class approveApplyServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String classid = req.getParameter("classid");
        String classname = classService.getClassName(classid);
        System.out.println("---approveApply POST---");
        System.out.println("id: " + id);
        System.out.println("classid: " + classid);
        System.out.println("classname: " + classname);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(classService.userAddClass(id, classid) && userService.changeClass(id, classname) && classService.delApply(id)){
            out.write("Approval succeeded");
        }else{
            out.write("Approval failed");
        }
    }
}
