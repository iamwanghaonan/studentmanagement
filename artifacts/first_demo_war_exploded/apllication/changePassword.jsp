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
    <title>修改密码</title>
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
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: absolute;
            top: 0;
            width: 100%;
        }

        .content {
            padding: 80px;
        }

        .footer {
            position: fixed;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            color: black;
            padding: 0;
            font-size: 12px; /* 调整字体大小，根据需要调整 */
        }
        .class-list-section table {
            width: 100%;
            border-collapse: collapse;
        }

        .class-list-section th, .class-list-section td {
            padding: 8px;
            text-align: left;
        }

        .class-list-section .class-number {
            width: 10%; /* 设置编号列的宽度 */
        }

        .class-list-section .class-name {
            width: 40%; /* 设置班级列的宽度 */
        }

        .class-list-section .teacher {
            width: 50%; /* 设置老师列的宽度 */
        }

    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <a>班级人员信息管理系统</a>
    </div>
    <div class="sidebar">
        <h2>菜单</h2>
        <ul>
            <li><a href="joinclass.jsp">加入班级</a></li>
            <li><a href="myclass.jsp">我的班级</a></li>
            <li><a href="message.jsp">消息通知</a></li>
            <li><a href="personal.jsp">个人信息</a></li>
            <li id="adminLink" style="display: none;"><a href="../root/class.jsp">管理入口</a></li>
            <li><a href="changePassword.jsp">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">退出登录</a></li>
        </ul>
    </div>

    <div class="main-content">

        <div class="content">
            <form id="changePasswordForm">
                <div style="margin-bottom: 30px;">
                    <label style="font-size: 30px" for="oldPassword">原密码：</label>
                    <input style="font-size: 30px" type="password" id="oldPassword" placeholder="请输入原密码" required>
                </div>
                <div style="margin-bottom: 30px;">
                    <label style="font-size: 30px" for="newPassword">新密码：</label>
                    <input style="font-size: 30px" type="password" id="newPassword" placeholder="请输入新密码" required>
                </div>
                <div style="margin-bottom: 30px;">
                    <label style="font-size: 30px" for="confirmPassword">确认密码：</label>
                    <input style="font-size: 30px" type="password" id="confirmPassword" placeholder="请再次输入新密码" required>
                </div>
                <div>
                    <button style="font-size: 30px" type="button" onclick="changePassword()">修改</button>
                </div>
            </form>
        </div>


        <div class="footer">
            <p>版权 © 2024 班级人员信息管理系统</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        // 读取信息
        var userInfo = sessionStorage.getItem('userInfo');
        userInfo = JSON.parse(userInfo)
        console.log(userInfo);
        // 根据 isroot 的值动态显示/隐藏 "管理入口"
        if (userInfo.isroot === 0) {
            document.getElementById('adminLink').style.display = 'none';
        } else {
            document.getElementById('adminLink').style.display = 'list-item';
        }

        function changePassword() {
            var oldPassword = document.getElementById('oldPassword').value;
            var newPassword = document.getElementById('newPassword').value;
            var confirmPassword = document.getElementById('confirmPassword').value;
            console.log("原密码：" + oldPassword + "新密码：" + newPassword + "确认：" + confirmPassword)
            // Check if new password and confirm password match
            if (newPassword !== confirmPassword) {
                alert('两次输入密码不相等');
                return;
            }

            $.ajax({
                url: '<%= request.getContextPath() %>/changePassword',
                type: 'POST',
                data: { id: userInfo.id, oldPassword: oldPassword, newPassword: newPassword},
                success: function(response) {
                    console.log("修改结果：" + response)
                    if (response === "Change succeed") {
                        alert("修改成功")
                        window.location.href = "/logout";
                    } else if(response === "Change failed") {
                        alert("原密码不正确")
                    } else {
                        alert("修改失败，请重试")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });

        }

    </script>


</div>
</body>
</html>
