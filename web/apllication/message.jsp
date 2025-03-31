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
    <title>消息通知</title>
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
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .class-list-section th, .class-list-section td {
            padding: 8px;
        }

        .class-list-section .title {
            white-space: normal;
        }
        .class-list-section .detail {

        }


        #t1 {
            width: 1200px;
            height: 100px;
            border-collapse: collapse;
        }

        th,
        td {
            border: 1px solid black;
            padding: 5px;
        }

        .class-list-section .title {
            white-space: nowrap;
        }

        .title{
            font-family: Arial, sans-serif; /* 设置表格字体 */
            font-size: 30px; /* 设置字体大小 */
            color: brown;
            padding: inherit;
        }
        .time{
            font-family: Arial, sans-serif; /* 设置表格字体 */
            font-size: 25px; /* 设置字体大小 */
            color: darkblue;
        }
        .detail{
            font-family: Arial, sans-serif; /* 设置表格字体 */
            font-size: 20px; /* 设置字体大小 */
            color: black;
        }
        .member{
            font-family: Arial, sans-serif; /* 设置表格字体 */
            font-size: 13px; /* 设置字体大小 */
            color: #666666;
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
                <h2>信息通知</h2>
                <table align="center" border="1" id="t1" style="width:1400px;height:100px">
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
        // 根据 isroot 的值动态显示/隐藏 "管理入口"
        if (userInfo.isroot === 0) {
            document.getElementById('adminLink').style.display = 'none';
        } else {
            document.getElementById('adminLink').style.display = 'list-item';
        }

        function getMessage(){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/showMassage',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        var massageList = data.Massage;
                        var massageListBody = $('#classListBody');
                        console.log("信息表信息：" + massageList);
                        // 清空现有内容
                        massageListBody.empty();

                        // 遍历班级列表
                        for (var i = 0; i < massageList.length; i++) {
                            var messageInfo = massageList[i];
                            // console.log("信息：" + messageInfo.id)
                            // 创建一行表格数据
                            var row = '<dl onclick="gotoReply(' + messageInfo.id + ')">' +
                                '<br>'+
                                '<dd class="title" >' + messageInfo.title + '</dd>' +
                                '<dd class="member">' + messageInfo.time  +"       " + messageInfo.root+'</dd>' +
                                '<dd class="detail">' + messageInfo.detail + '</dd>' +
                                '<br>'+
                                '<dd>' + '-----------------------------------------------------------------------' +
                                '--------------------------------------------------------------------------------' +
                                '</dd>'
                            '</dl>';
                            // 将该行数据添加到表格体中
                            massageListBody.append(row);
                        }
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });
        }


        function gotoReply(messageid){
            console.log("信息详情： " + messageid);
            sessionStorage.setItem('messageid', messageid);
            window.location.href = "reply.jsp";
        }

        function sendReply(messageid){
            // 获取评论输入框的值
            var replyText = $('#replyText_' + messageid).val();
            console.log("用户 " + userInfo.id + userInfo.name +"，点击评论 " + messageid + "，评论内容：" + replyText);

            $.ajax({
                url: '<%= request.getContextPath() %>/sendReply',
                type: 'POST',
                data: { id: userInfo.id, name: userInfo.name, messageid: messageid, replyText: replyText},
                success: function(response) {
                    console.log("发送评论结果：" + response)
                    if (response === "Send succeed") {
                        getMessage();
                        alert("发送成功")
                    } else if(response === "Send failed") {
                        alert("发送失败，请重试")
                    } else {
                        alert("发送失败")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });
        }

        function getReply(messageid){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/getReply',
                    type: 'GET',
                    data: {id: messageid},
                    dataType: 'json',
                    success: function(data) {
                        var replyList = data;
                        console.log("评论：" + data)
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });
        }

        window.onload = (event) => {
            getMessage();
            console.log("page is fully loaded");
        };
    </script>


</div>
</body>
</html>
