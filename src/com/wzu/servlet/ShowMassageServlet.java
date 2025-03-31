package com.wzu.servlet;

import com.wzu.pojo.Massage;
import com.wzu.service.MassageService;
import com.wzu.service.MassageServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/showMassage")
public class ShowMassageServlet extends HttpServlet {
    MassageService MassageService = new MassageServicelmpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Massage> Massage = MassageService.getMassage();
        System.out.println("---getMassage---");
        System.out.println("Massage = " + Massage);
        req.setAttribute("Massage", Massage);
        // 将班级列表数据转换为 JSON 格式
        String MassageToJson = MassageToJson(Massage);
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取响应的写入流
        PrintWriter out = resp.getWriter();
        // 将 JSON 数据写入响应
        out.print(MassageToJson);
        out.flush();
    }

    private String MassageToJson(List<Massage> Massage) {
        StringBuilder json = new StringBuilder("{\"Massage\":[");
        for (int i = 0; i < Massage.size(); i++) {
            Massage massage = Massage.get(i);
            json.append("{\"id\":\"").append(massage.getId()).append("\",")
                    .append("\"classid\":\"").append(massage.getClassid()).append("\",")
                    .append("\"title\":\"").append(massage.getTitle()).append("\",")
                    .append("\"time\":\"").append(massage.getTime()).append("\",")
                    .append("\"detail\":\"").append(massage.getDetail()).append("\",")
                    .append("\"root\":\"").append(massage.getRoot()).append("\"}");

            if (i < Massage.size() - 1) {
                json.append(",");
            }
        }
        json.append("]}");
        return json.toString();
    }
}
