package com.wzu.servlet;

import com.wzu.pojo.Apply;
import com.wzu.pojo.ClassInfo;
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
import java.util.List;

@WebServlet("/applyInfo")
public class GetApplyServlet extends HttpServlet {
    ClassService classService = new ClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classid = req.getParameter("classid");
        // 添加非空检查
        if (classid == null) {
            // 处理参数缺失的情况，可以返回适当的错误信息或者进行其他处理
            // 返回400 Bad Request
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Apply> applies = classService.getApplyDetail(classid);
        System.out.println("---getApplyInfo---");
        System.out.println("classid = " + classid);
        req.setAttribute("applies", applies);
        // 将班级列表数据转换为 JSON 格式
        String jsonClassInfos = convertClassInfosToJson(applies);
        System.out.println("applies:" + jsonClassInfos);
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取响应的写入流
        PrintWriter out = resp.getWriter();
        // 将 JSON 数据写入响应
        out.print(jsonClassInfos);
        out.flush();
    }
    private String convertClassInfosToJson(List<Apply> applies) {
        StringBuilder json = new StringBuilder("{\"applies\":[");
        for (int i = 0; i < applies.size(); i++) {
            Apply apply = applies.get(i);
            json.append("{\"id\":\"").append(apply.getId()).append("\",")
                    .append("\"name\":\"").append(apply.getName()).append("\",")
                    .append("\"classid\":\"").append(apply.getClassid()).append("\",")
                    .append("\"classname\":\"").append(apply.getClassname()).append("\"}");

            if (i < applies.size() - 1) {
                json.append(",");
            }
        }
        json.append("]}");
        return json.toString();
    }

}
