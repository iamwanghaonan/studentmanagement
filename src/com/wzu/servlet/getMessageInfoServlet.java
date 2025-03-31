package com.wzu.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzu.pojo.Massage;
import com.wzu.pojo.User;
import com.wzu.service.MassageService;
import com.wzu.service.MassageServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getMessageInfo")
public class getMessageInfoServlet extends HttpServlet {
    MassageService massageService = new MassageServicelmpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageid = req.getParameter("messageid");
        System.out.println("---getMessageInfo POST---");
        System.out.println("messageid: " + messageid);
        Massage massage = massageService.getMessageInfo(messageid);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (massage != null){
            String userInfoJson = getUserInfoJson(massage);
            System.out.println(userInfoJson);
            out.write(userInfoJson);
        }
    }

    private String getUserInfoJson(Massage massage) {
        // 使用JSON库将User对象转换成JSON格式字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(massage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // 处理异常时返回一个空JSON对象
        }

    }

}
