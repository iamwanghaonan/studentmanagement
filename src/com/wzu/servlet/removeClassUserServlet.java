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

@WebServlet("/removeClassUser")
public class removeClassUserServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String classid = req.getParameter("classid");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(classService.removeClassUser(id, classid)){
            out.write("Remove succeeded");
        }else{
            out.write("Remove failed");
        }
    }
}
