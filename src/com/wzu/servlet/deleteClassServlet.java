package com.wzu.servlet;

import com.wzu.service.ClassService;
import com.wzu.service.ClassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteClass")
public class deleteClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classid = req.getParameter("classid");
        System.out.println("---deleteClass POST---");
        System.out.println("classid = " + classid);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (classService.delClass(classid)){
            out.write("Delete succeed");
        }else{
            out.write("Delete failed");
        }
    }
}
