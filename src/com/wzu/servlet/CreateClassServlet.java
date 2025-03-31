package com.wzu.servlet;

import com.wzu.pojo.ClassInfo;
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

@WebServlet("/createClass")
public class CreateClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String classname = req.getParameter("classname");
        String root = URLDecoder.decode(req.getParameter("root"), "UTF-8");
        ClassInfo classInfo = new ClassInfo(id, classname, root);
        System.out.println("---createClass POST---");
        System.out.println("id: " + classInfo.getId());
        System.out.println("classname: " + classInfo.getClassname());
        System.out.println("root: " + classInfo.getRoot());
        int create_flag = classService.createClass(classInfo);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(create_flag == 1){
            //创建成功
            out.write("The creation is successful");
        }else if(create_flag == 0){
            //创建失败，编号已存在
            out.write("The ID already exists");
        }else if(create_flag == -1){
            //创建失败，班级已存在
            out.write("The class already exists");
        }else if(create_flag == -2){
            //创建信息不完整
            out.write("The creation information is incomplete");
        }
        out.flush();
        out.close();
    }
}
