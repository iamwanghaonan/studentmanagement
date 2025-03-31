package com.wzu.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzu.pojo.ClassInfo;
import com.wzu.pojo.User;
import com.wzu.service.ClassService;
import com.wzu.service.ClassServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/classInfo")
public class GetClassServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassInfo> classInfos = classService.getClassInfo();
        System.out.println("---getClassInfo---");
        System.out.println("classInfos = " + classInfos);
        req.setAttribute("classInfos", classInfos);
        // 将班级列表数据转换为 JSON 格式
        String jsonClassInfos = convertClassInfosToJson(classInfos);
        System.out.println("classInfos:" + jsonClassInfos);
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取响应的写入流
        PrintWriter out = resp.getWriter();
        // 将 JSON 数据写入响应
        out.print(jsonClassInfos);
        out.flush();
    }

    private String convertClassInfosToJson(List<ClassInfo> classInfos) {
        StringBuilder json = new StringBuilder("{\"classInfos\":[");
        for (int i = 0; i < classInfos.size(); i++) {
            ClassInfo classInfo = classInfos.get(i);
            json.append("{\"id\":\"").append(classInfo.getId()).append("\",")
                    .append("\"classname\":\"").append(classInfo.getClassname()).append("\",")
                    .append("\"root\":\"").append(classInfo.getRoot()).append("\"}");

            if (i < classInfos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]}");
        return json.toString();
    }
}
