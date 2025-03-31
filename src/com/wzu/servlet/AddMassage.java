package com.wzu.servlet;

import com.wzu.pojo.ClassInfo;
import com.wzu.pojo.Massage;
import com.wzu.pojo.User;
import com.wzu.service.ClassService;
import com.wzu.service.ClassServiceImpl;
import com.wzu.service.MassageService;
import com.wzu.service.MassageServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet("/AddMassage")
public class AddMassage extends HttpServlet {
    MassageService massageService = new MassageServicelmpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        // 设置日期时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // 格式化当前日期时间
        String formattedDateTime = now.format(formatter);
        // 打印结果
        System.out.println("当前时间: " + formattedDateTime);
        // 格式化当前日期时间

        DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String id = now.format(formatter_2);
        // String id = req.getParameter("id");
        // String classid = req.getParameter("classid");
        String classid = "0";
        String title = URLDecoder.decode(req.getParameter("title"), "UTF-8");
        String time = formattedDateTime;
        String detail = URLDecoder.decode(req.getParameter("detail"), "UTF-8");
        String root = URLDecoder.decode(req.getParameter("root"), "UTF-8");
        Massage massage = new Massage(id, classid, title, time, detail, root);
        System.out.println("---addmassage POST---");
        System.out.println("id = " + id);
        System.out.println("classid = " + classid);
        System.out.println("title = " + title);
        System.out.println("time = " + time);
        System.out.println("detail = " + detail);
        System.out.println("root = " + root);

        boolean addMassage_flag = massageService.addMassage(massage);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(addMassage_flag){
            //创建成功
            out.write("addMassage is successful");
        }else if(!addMassage_flag){
            //创建失败，编号已存在
            out.write("error");
        }
        out.flush();
        out.close();
    }
}
