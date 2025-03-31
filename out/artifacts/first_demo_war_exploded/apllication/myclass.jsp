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
    <title>我的班级</title>
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
            padding: 50px;
        }

        .class-list-section table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .class-list-section th, .class-list-section td {
            padding: 8px;
        }

        .class-list-section .class-number {
            text-align: center;
        }

        .class-list-section .class-name {
            text-align: center;
        }

        .class-list-section .teacher {
            text-align: center;
        }
        .class-list-section .title {
            text-align: center;
            background: lightblue;
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

            <div id="notJoinedMessage" style="display: none;">
                <h2>暂未加入班级</h2>
            </div>

            <div class="class-list-section" id="classListSection" style="display: none;">
                <h2>班级列表</h2>
                <table align="center" border="1" id="t1" style="width:1500px;height:100px">
                    <thead>
                    <tr>
                        <th class="title">学号</th>
                        <th class="title">姓名</th>
                        <th class="title">邮箱</th>
                        <th class="title">电话</th>
                    </tr>
                    </thead>
                    <tbody id="classListBody">
                    <!-- 生成班级列表 -->
                    <!-- 示例：<tr><td>[编号]</td><td>[班级]</td><td>[老师]</td></tr> -->
                    </tbody>
                </table>
            </div>

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
        let classname = userInfo.classname;

        // 根据 isroot 的值动态显示/隐藏 "管理入口"
        if (userInfo.isroot === 0) {
            document.getElementById('adminLink').style.display = 'none';
        } else {
            document.getElementById('adminLink').style.display = 'list-item';
        }

        function getClassInfo(){

            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/classUserInfo',
                    type: 'GET',
                    data:{classname: classname},
                    dataType: 'json',
                    success: function(data) {
                        var classList = data.classUserInfos;
                        var classListBody = $('#classListBody');
                        console.log("班级成员列表信息：" + classList);
                        // 清空现有内容
                        classListBody.empty();

                        // 遍历班级列表
                        for (var i = 0; i < classList.length; i++) {
                            var classInfo = classList[i];
                            classInfo_data = JSON.stringify(classInfo).replace(/"/g,"'");
                            // 创建一行表格数据
                            var row = '<tr>' +
                                '<td class="class-number">' + classInfo.id + '</td>' +
                                '<td class="class-name">' + classInfo.name + '</td>' +
                                '<td class="teacher">' + classInfo.email + '</td>' +
                                '<td class="teacher">' + classInfo.phone + '</td>' +
                                '</tr>';

                            // 将该行数据添加到表格体中
                            classListBody.append(row);
                        }
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });

        }


        window.onload = (event) => {
            //getClassInfo();
            var notJoinedMessage = document.getElementById('notJoinedMessage');
            var classListSection = document.getElementById('classListSection');

            if (classname === "") {
                // 如果 classname 为空，则显示“暂未加入班级”消息
                notJoinedMessage.style.display = 'block';
            } else {
                // 如果 classname 不为空，则加载班级信息并显示班级列表
                getClassInfo();
                classListSection.style.display = 'block';
            }
            console.log("page is fully loaded");
        };

    </script>


</div>
</body>
</html>
