<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2024/1/5
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        input[type="submit"], .button-link {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            text-decoration: none; /* 移除链接的下划线 */
            display: inline-block; /* 使链接像按钮一样显示 */
            text-align: center;
        }

        input[type="submit"]:hover, .button-link:hover {
            background-color: #45a049;
        }

        .button-container {
            text-align: center; /* 让按钮居中显示 */
            margin-top: 15px;
        }
        /* 设置 body 标签，使背景图片覆盖整个网页 */
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .login-container {
            background-image: url('img/cg(16).jpg');
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-box {
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }

        input[type="text"], input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>
    <title>登录</title>
</head>
<body>
<%
    String id="";
    String password="";
    Cookie[] cookies=request.getCookies();
    if(cookies!=null&&cookies.length>0){
        for(int i=0;i<cookies.length;i++){
            if("id".equals(cookies[i].getName())){
                id = cookies[i].getValue();
            }
            if("password".equals(cookies[i].getName())){
                password = cookies[i].getValue();
            }

        }
    }
%>
<div class="login-container">
    <div class="form-box">
        <h2>登录</h2>
        <form id="loginForm" action="<%=request.getContextPath()%>/login" method="post">
            学号：<input type="text" name="id" placeholder="请输入学号" value="<%=id%>" required><br>
            密码：<input type="password" name="password" placeholder="请输入密码" value="<%=password%>" required><br>
            <input type="submit" value="登录">
        </form>
        <div class="button-container">
            <button class="button-link" onclick="window.location.href='<%=request.getContextPath()%>/insert.jsp'">注册</button>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
        $(document).ready(function () {
            $("#loginForm").submit(function (event) {
                event.preventDefault(); // 阻止默认的表单提交行为

                // 获取表单数据
                var formData = $(this).serialize();
                console.log(formData)

                $.post("<%=request.getContextPath()%>/login", formData , function (data) {
                    console.log("登录结果：" + data);
                    // 处理服务器的响应
                    if (data === "fail") {
                        alert("学号或密码错误");
                    } else if (data === "" || data === null) {
                        alert("登录失败");
                    } else {
                        sessionStorage.setItem('userInfo', data);
                        window.location.href = "./apllication/joinclass.jsp";
                    }
                });
            });
        });
    </script>

</div>

</body>
</html>
