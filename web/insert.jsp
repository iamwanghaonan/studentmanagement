

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

        .return-link {
            display: block;
            margin-top: 10px;
            font-size: 10px;
            text-decoration: none;
            color: #333333;
        }
    </style>
    <title>注册</title>
</head>
<body>

<div class="login-container">
    <div class="form-box">
        <h2>注册</h2>
        <form id="loginForm" action="/Insert" method="post">
            姓名：<input type="text" name="name" placeholder="请输入姓名" required><br>
            学号：<input type="text" name="id" id="id" placeholder="请输入学号" required><br>
            密码：<input type="password" name="password" placeholder="请输入密码" required><br>
            管理员验证码：<input type="text" name="root" placeholder="请输入管理员验证码"><br>
            <input type="submit" value="注册">
        </form>
        <a href="login.jsp" class="return-link">返回</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
        $(document).ready(function () {
            $("#loginForm").submit(function (event) {
                event.preventDefault(); // 阻止默认的表单提交行为

                // 获取表单数据并编码中文姓名
                var formData = $(this).serialize();
                formData = formData.replace(/name=([^&]*)/, function(match, p1) {
                    return 'name=' + encodeURIComponent(p1);
                });
                console.log("注册信息：" + formData)


                $.post("/Insert", formData , function (data) {
                    console.log("注册结果：" + data);
                    // 处理服务器的响应
                    if (data === "Registration is successful") {
                        alert("注册成功");
                        window.location.href = "login.jsp";
                    } else if (data === "Info is incomplete") {
                        alert("注册信息填写不完整");
                    } else if (data === "ID is registered") {
                        alert("学号已注册");
                    } else if (data === "not root"){
                        alert("管理员验证码错误");
                    } else {
                        alert("注册失败");
                    }
                });
            });
        });
    </script>


</div>


</body>
</html>
