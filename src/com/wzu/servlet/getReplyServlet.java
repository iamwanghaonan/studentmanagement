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
import java.util.List;

@WebServlet("/getReply")
public class getReplyServlet extends HttpServlet {
    MassageService massageService = new MassageServicelmpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        System.out.println("---getReply---");
        System.out.println("messageid = " + id);
        List<MassageReply> replyInfos = massageService.getReply(id);
        req.setAttribute("replyInfos", replyInfos);
        String jsonMessageInfos = convertReplyInfosToJson(replyInfos);
        System.out.println("reply = " + jsonMessageInfos);
        // 设置响应的内容类型为 JSON
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取响应的写入流
        PrintWriter out = resp.getWriter();
        // 将 JSON 数据写入响应
        out.write(jsonMessageInfos);
        out.flush();
    }

    private String convertReplyInfosToJson(List<MassageReply> MassageReplys) {
        StringBuilder json = new StringBuilder("{\"replyInfos\":[");
        for (int i = 0; i < MassageReplys.size(); i++) {
            MassageReply massageReply = MassageReplys.get(i);
            json.append("{\"id\":\"").append(massageReply.getId()).append("\",")
                    .append("\"name\":\"").append(massageReply.getName()).append("\",")
                    .append("\"time\":\"").append(massageReply.getTime()).append("\",")
                    .append("\"detail\":\"").append(massageReply.getDetail()).append("\"");

            if (i < MassageReplys.size() - 1) {
                json.append("},");
            } else {
                json.append("}");
            }
        }
        json.append("]}");
        return json.toString();
    }

}
