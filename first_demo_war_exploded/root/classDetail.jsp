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
    <title>班级人员信息管理系统</title>
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
        }

        .class-list-section th, .class-list-section td {
            padding: 8px;
            text-align: left;
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
        .class-list-section .title2 {
            text-align: center;
            background: darkgoldenrod;
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
        <ul>
            <li><a href="../apllication/joinclass.jsp">返回</a></li>
            <li><a href="class.jsp">管理班级</a></li>
            <li><a href="student.jsp">管理学生</a></li>
            <li><a href="message.jsp">消息通知</a></li>
        </ul>
    </div>


    <div class="main-content">
        <div class="content">

            <div class="class-list-section">
                <button style="font-size: 30px;float: right;background: lightcoral" onclick="gotoClass()">返回</button>
                <a style="font-size: 20px;">当前所在的班级：</a>
                <a style="font-size: 25px;"  id="myClassName"></a>
                <h2>申请列表</h2>
                <table align="center" border="1" style="width:1500px;height:100px">
                    <thead>
                    <tr>
                        <th class="title">学号</th>
                        <th class="title">姓名</th>
                        <th class="title">申请班级</th>
                        <th class="title">操作</th>
                    </tr>
                    </thead>
                    <tbody id="applyListBody">
                    <!-- 生成班级列表 -->
                    <!-- 示例：<tr><td>[编号]</td><td>[班级]</td><td>[老师]</td></tr> -->
                    </tbody>
                </table>
            </div>

            <div class="class-list-section">
                <h2>班级列表</h2>
                <table align="center" border="1" style="width:1500px;height:100px">
                    <thead>
                    <tr>
                        <th class="title2">学号</th>
                        <th class="title2">姓名</th>
                        <th class="title2">邮箱</th>
                        <th class="title2">电话</th>
                        <th class="title2">操作</th>
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
        let classid = sessionStorage.getItem('root_classInfo');
        console.log("classid:" + classid);
        let classname = ""

        function rejectApply(id){
            console.log("拒绝" + id)
            $.ajax({
                url: '<%= request.getContextPath() %>/rejectApply',
                type: 'POST',
                data: { id: id, classid: classid},
                success: function(response) {
                    if (response === "The rejection succeeded") {
                        getApply();
                        alert("已拒绝" + id + "加入班级")
                    } else if(response === "The rejection failed") {
                        alert("拒绝失败，请重试")
                    } else {
                        alert("拒绝失败")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });
        }

        function gotoClass(){
            window.location.href = "class.jsp";
        }

        function approveApply(id){
            console.log("同意" + id + userInfo.id)
            $.ajax({
                url: '<%= request.getContextPath() %>/approveApply',
                type: 'POST',
                data: { id: id, classid: classid},
                success: function(response) {
                    console.log("同意结果：" + response)
                    if (response === "Approval succeeded") {
                        getUserInfo();
                        getApply();
                        getClassInfo();
                        alert("已同意" + id + "加入班级")
                    } else if(response === "Approval failed") {
                        alert("同意失败，请重试")
                    } else {
                        alert("同意失败")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });
        }

        function getClassName(){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/getClassName',
                    type: 'GET',
                    data:{classid: classid},
                    success: function(data) {
                        console.log("classname:" + data)
                        document.getElementById('myClassName').innerText = data;
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });
        }

        function getUserInfo(id){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/getUserInfo',
                    type: 'GET',
                    data:{id: userInfo.id},
                    dataType: 'json',
                    success: function(data) {
                        userInfo = data
                        console.log("获取用户信息：" + userInfo)

                        sessionStorage.setItem('userInfo', JSON.stringify(data));
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });
        }

        function getApply(){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/applyInfo',
                    type: 'GET',
                    data:{classid: classid},
                    dataType: 'json',
                    success: function(data) {
                        var applyList = data.applies;
                        var applyListBody = $('#applyListBody');
                        console.log("申请列表信息：" + applyList);
                        // 清空现有内容
                        applyListBody.empty();

                        // 遍历班级列表
                        for (var i = 0; i < applyList.length; i++) {
                            var applyInfo = applyList[i];
                            classname = applyInfo.classname;
                            applyInfo_data = JSON.stringify(applyInfo).replace(/"/g,"'");
                            // 创建一行表格数据
                            var row = '<tr>' +
                                '<td class="class-number">' + applyInfo.id + '</td>' +
                                '<td class="class-name">' + applyInfo.name + '</td>' +
                                '<td class="teacher">' + applyInfo.classname + '</td>' +
                                '<td><button onclick="rejectApply(' + applyInfo.id + ')">' + '拒绝</button>' +
                                '<button onclick="approveApply(' + applyInfo.id + ')">' + '同意</button></td>' +
                                '</tr>';

                            // 将该行数据添加到表格体中
                            applyListBody.append(row);
                        }
                    },
                    error: function(error) {
                        console.log('Error:', error);
                    }
                });
            });

        }


        function getClassInfo(){

            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/classUserInfo',
                    type: 'GET',
                    data:{classid: classid},
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
                                '<td><button onclick="removeClassUser(' + classInfo.id + ',' + classid + ')">' + '移出本班</button></td>' +
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

        function removeClassUser(id, classid){
            console.log("移出本班：" + id + " " + classid)
            $.ajax({
                url: '<%= request.getContextPath() %>/removeClassUser',
                type: 'POST',
                data: { id: id, classid: classid},
                success: function(response) {
                    console.log("移出结果：" + response)
                    if (response === "Remove succeeded") {
                        getUserInfo();
                        getClassInfo();
                        alert("成功移出")
                    } else if(response === "Remove failed") {
                        alert("移出失败，请重试")
                    } else {
                        alert("移出失败")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });
        }


        window.onload = (event) => {
            getClassName();
            getApply();
            getClassInfo();
            console.log("page is fully loaded");
        };

    </script>


</div>
</body>
</html>
