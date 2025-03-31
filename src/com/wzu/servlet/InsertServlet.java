package com.wzu.servlet;
import com.wzu.pojo.User;
import com.wzu.service.UserService;
import com.wzu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Objects;

@WebServlet(urlPatterns = "/Insert")
public class InsertServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String root = req.getParameter("root");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        User user=new User(name, id, password);
        System.out.println("---insert POST---");
        System.out.println("name: " + user.getName());
        System.out.println("id: " + user.getId());
        System.out.println("password: " + user.getPassword());
        System.out.println("root: " + root);
        if(!Objects.equals(root, "") && !Objects.equals(root, "12345")){
            out.write("not root");
            return;
        }else{
            user.setIsroot(1);
        }
        int insert_flag = userService.inSert(user);
        if(insert_flag == 1){
            //注册成功则跳转到login页面
            out.write("Registration is successful");
        }else if(insert_flag == 0){
            //注册失败，注册信息填写不完整
            out.write("Info is incomplete");
        }else if(insert_flag == -1){
            //注册失败，学号已注册
            out.write("ID is registered");
        }
        out.flush();
        out.close();
    }
}

