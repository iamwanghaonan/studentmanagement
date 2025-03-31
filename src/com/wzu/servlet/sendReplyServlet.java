package com.wzu.servlet;

import com.wzu.pojo.MassageReply;
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
import java.time.format.DateTimeFormatter;

@WebServlet("/sendReply")
public class sendReplyServlet extends HttpServlet {
    com.wzu.service.MassageService MassageService = new MassageServicelmpl();

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

        String id = req.getParameter("id");
        String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
        String time = formattedDateTime;
        String detail = URLDecoder.decode(req.getParameter("replyText"), "UTF-8");
        String messageid = req.getParameter("messageid");
        System.out.println("---sendReply POST---");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("time = " + time);
        System.out.println("detail = " + detail);
        System.out.println("messageid = " + messageid);


        MassageReply Massagereply = new MassageReply(id, name, time, detail);
        boolean Massagereply_flag = MassageService.replyMassage(Massagereply, messageid);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(Massagereply_flag){
            out.write("Send succeed");
        }else if(!Massagereply_flag){
            out.write("Send failed");
        }
        out.flush();
        out.close();
    }
}
