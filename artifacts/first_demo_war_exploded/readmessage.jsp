<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2024/1/4
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统</title>
    <style>
        /* 这里直接写入 CSS 代码 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            min-height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            display: flex;
            flex-direction: row;
        }

        .sidebar {
            width: 90px;
            background-color: #333;
            color: white;
            padding: 15px;
        }

        .sidebar h2 {
            padding: 10px;
            color: #fff;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar ul li a {
            color: white;
            padding: 10px;
            display: block;
            text-decoration: none;
        }

        .sidebar ul li a:hover {
            background-color: #575757;
        }

        .main-content {
            flex-grow: 1;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px 0;
            position: absolute;
            width: 100%;
        }

        .content {
            padding: 80px;
        }

        .footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h2>菜单</h2>
        <ul>
            <li><a href="joincalss.jsp">加入班级</a></li>
            <li><a href="readmessage.jsp">信息阅读</a></li>
            <li><a href="replymessage.jsp">信息回复</a></li>
        </ul>
    </div>

    <div class="main-content">
        <div class="header">
            <a>学生信息管理系统</a>
        </div>

        <div class="content">
            <a>信息阅读</a>
        </div>

        <div class="footer">
            <p>版权 © 2024 学生信息管理系统</p>
        </div>
    </div>
</div>
</body>
</html>
