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
    <title>加入班级</title>
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
            max-width: 1500px; /* 可以根据需要调整最大宽度 */
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .class-list-section th,
        .class-list-section td {
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
            font-size: 12px;
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

            <div class="class-list-section">
                <h2>班级列表</h2>
                <table align="center" border="1" id="t1" style="width:6000px;height:200px;">
                    <thead>
                    <tr>
                        <th class="title">编号</th>
                        <th class="title">班级</th>
                        <th class="title">老师</th>
                        <th class="title">操作</th>
                    </tr>
                    </thead>
                    <tbody align="center" valign="center" id="classListBody">
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

    <script type="application/javascript">
        // 读取信息
        let userInfo = sessionStorage.getItem('userInfo');
        userInfo = JSON.parse(userInfo)
        console.log(userInfo);
        let classList = null;

        // 根据 isroot 的值动态显示/隐藏 "管理入口"
        if (userInfo.isroot === 0) {
            document.getElementById('adminLink').style.display = 'none';
        } else {
            document.getElementById('adminLink').style.display = 'list-item';
        }

        function getClassInfo(){

            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/classInfo',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        classList = data.classInfos;
                        var classListBody = $('#classListBody');
                        console.log("班级列表信息：" + classList);
                        // 清空现有内容
                        classListBody.empty();

                        // 遍历班级列表
                        for (var i = 0; i < classList.length; i++) {
                            var classInfo = classList[i];
                            classInfo_data = JSON.stringify(classInfo).replace(/"/g,"'");
                            // 创建一行表格数据
                            var row = '<tr>' +
                                '<td class="class-number">' + classInfo.id + '</td>' +
                                '<td class="class-name">' + classInfo.classname + '</td>' +
                                '<td class="teacher">' + classInfo.root + '</td>' +
                                '<td align="center"><button onclick="applyToJoin(' + classInfo_data + ')">' + '申请加入</button></td>' +
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
            getClassInfo();
            console.log("page is fully loaded");
        };

        //申请加入
        function applyToJoin(classInfo) {
            // 在这里执行 AJAX 请求，将学号和班级编号传回后端
            console.log("申请加入： " + classInfo);
            var studentClassName = "";
            for(var i = 0; i < classList.length; i++){
                if(classList[i].classname === userInfo.classname){
                    studentClassName = classList[i].classname;
                    break;
                }
            }
            if(studentClassName !== "" ){
                alert("您已在" + studentClassName);
                return;
            }



            // 这里可以使用 jQuery 的 AJAX 方法或者原生的 fetch 方法发送请求
            // 例如，使用 jQuery：
            $.ajax({
                url: '<%= request.getContextPath() %>/applyToJoin',
                type: 'POST',
                data: { userId: userInfo.id, userName: userInfo.name, classId: classInfo.id, className: classInfo.classname},
                success: function(response) {
                    if (response === "The application is successful") {
                        alert("申请成功")
                    } else if(response === "The application failed") {
                        alert("申请失败，请重试")
                    } else {
                        alert("申请失败")
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
