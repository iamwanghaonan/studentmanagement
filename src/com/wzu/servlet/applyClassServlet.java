package com.wzu.servlet;

import com.wzu.pojo.User;
import com.wzu.service.ClassService;
import com.wzu.service.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/applyToJoin")
public class applyClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String name = URLDecoder.decode(req.getParameter("userName"), "UTF-8");
        String classId = req.getParameter("classId");
        String className = URLDecoder.decode(req.getParameter("className"), "UTF-8");
        System.out.println("---applyToJoin POST---");
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("classId: " + classId);
        System.out.println("className: " + className);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(classService.joinClass(id, name, classId, className)){
            out.write("The application is successful");
        }else{
            out.write("The application failed");
        }

        out.flush();
        out.close();
    }
}
