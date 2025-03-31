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
    <title>个人主页</title>
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

        p {
            text-align: left;
            margin-left: 10px; /* 可根据需要调整左边距 */
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
            <div style="text-align: center;">
                <div style="width: 100px; height: 100px; border-radius: 50%; background-color: #4CAF50; color: white; display: flex; align-items: center; justify-content: center; font-size: 24px; font-weight: bold;" id="nameInitial">
                    <!-- 这里使用 JavaScript 动态填充姓名的第一个字 -->
                </div>
                <p>姓名：<span id="name"></span></p>
                <p>学号：<span id="id"></span></p>
                <p>班级：<span id="classname"></span></p>
                <p>邮箱：<span id="email" contenteditable="true"></span></p>
                <p>电话：<span id="phone" contenteditable="true"></span></p>
                <button onclick="saveUserInfo()">保存</button>
            </div>
        </div>


        <div class="footer">
            <p>版权 © 2024 班级人员信息管理系统</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
        let userInfo = sessionStorage.getItem('userInfo');
        userInfo = JSON.parse(userInfo)
        console.log(userInfo);

        // 根据 isroot 的值动态显示/隐藏 "管理入口"
        if (userInfo.isroot === 0) {
            document.getElementById('adminLink').style.display = 'none';
        } else {
            document.getElementById('adminLink').style.display = 'list-item';
        }

        // 在页面中使用 userInfo 的属性
        document.getElementById('nameInitial').innerText = userInfo.name.charAt(0);
        document.getElementById('name').innerText = userInfo.name;
        document.getElementById('id').innerText = userInfo.id;
        if(userInfo.classid === "" || userInfo.classid == null){
            document.getElementById('classname').innerText = "暂未加入班级";
        }else{
            document.getElementById('classname').innerText = userInfo.classname;
        }
        document.getElementById('email').innerText = userInfo.email;
        document.getElementById('phone').innerText = userInfo.phone;

        window.onload = (event) => {// 读取信息

            console.log("page is fully loaded");
        };

        // 保存用户信息的函数
        function saveUserInfo() {
            // 获取更新后的邮箱和电话值
            var updatedEmail = document.getElementById('email').innerText;
            var updatedPhone = document.getElementById('phone').innerText;
            console.log("in:" + updatedEmail + "  " + updatedPhone)

            if(updatedEmail === userInfo.email && updatedPhone === userInfo.phone){
                alert("保存成功")
                return;
            }

            // 修改信息发送后端保存
            $.ajax({
                url: '<%= request.getContextPath() %>/saveUserInfo',
                type: 'POST',
                data: { id: userInfo.id, email: updatedEmail, phone: updatedPhone},
                success: function(response) {
                    console.log("保存结果：" + response)
                    if (response === "Save succeed") {
                        // 更新userInfo
                        userInfo.email = updatedEmail;
                        userInfo.phone = updatedPhone;
                        console.log("out:" + userInfo.email + "  " + userInfo.phone)
                        sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
                        console.log(userInfo)
                        // window.location.href = "personal.jsp";
                        // 更新显示
                        document.getElementById('email').innerText = updatedEmail;
                        document.getElementById('phone').innerText = updatedPhone;
                        alert("保存成功")
                    } else if(response === "Save failed") {
                        alert("保存失败，请重试")
                    } else {
                        alert("保存失败")
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
