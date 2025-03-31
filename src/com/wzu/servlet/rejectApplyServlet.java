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
import java.net.URL;

@WebServlet("/rejectApply")
public class rejectApplyServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String classid = req.getParameter("classid");
        System.out.println("---rejectApply POST---");
        System.out.println("id: " + id);
        System.out.println("classid: " + classid);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(classService.delApply(id)){
            out.write("The rejection succeeded");
        }else{
            out.write("The rejection failed");
        }
    }
}
