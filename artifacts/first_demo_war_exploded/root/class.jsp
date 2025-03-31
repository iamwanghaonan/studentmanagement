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
            width: 10%; /* 设置编号列的宽度 */
        }

        .class-list-section .class-name {
            width: 40%; /* 设置班级列的宽度 */
        }

        .class-list-section .teacher {
            width: 50%; /* 设置老师列的宽度 */
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
        .class-list-section .detil{
            text-align: center;

        }
        .create-section{
            width: 100%;
            max-width: 1500px; /* 可以根据需要调整最大宽度 */
            font-size: 25px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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

            <div class="create-section">
                <form id="createForm" action="<%=request.getContextPath()%>/createClass" method="post">
                    <label for="className">编号：</label>
                    <input style="font-size: 25px"  type="text" id="classNumber" name="id" placeholder="请输入编号" required>

                    <label for="className">班级：</label>
                    <input style="font-size: 25px" type="text" id="className" name="classname" placeholder="请输入班级" required>

                    <label for="className">老师：</label>
                    <input style="font-size: 25px" type="text" id="root" name="root" placeholder="请输入老师姓名" value="" required>

                    <input style="font-size: 25px" type="submit" value="创建">
                </form>
            </div>

            <div class="class-list-section">
                <h2>班级列表</h2>
                <table border="1">
                    <thead>
                    <tr>
                        <th class="title">编号</th>
                        <th class="title">班级</th>
                        <th class="title">老师</th>
                        <th class="title">操作</th>
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

        // 获取“老师”输入框元素
        var rootInput = document.getElementById("root");
        // 修改“老师”输入框的值
        rootInput.value = userInfo.name;

        function delClass(classid){
            console.log("删除班级： " + classid);

            $.ajax({
                url: '<%= request.getContextPath() %>/deleteClass',
                type: 'POST',
                data: { classid: classid},
                success: function(response) {
                    console.log("删除结果：" + response)
                    if (response === "Delete succeed") {
                        getClassInfo()
                        alert("删除成功")
                    } else if(response === "Delete failed") {
                        alert("删除失败，请重试")
                    } else {
                        alert("删除失败")
                    }
                },
                error: function(error) {
                    console.log('Error:', error);
                }
            });
        }


        function classDetail(classid) {
            console.log("管理班级： " + classid);
            sessionStorage.setItem('root_classInfo', classid);
            window.location.href = "./classDetail.jsp";
        }



        function getClassInfo(){
            $(document).ready(function() {
                $.ajax({
                    url: '<%= request.getContextPath() %>/classInfo',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        var classList = data.classInfos;
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
                                '<td class="detil">' + classInfo.id + '</td>' +
                                '<td class="detil">' + classInfo.classname + '</td>' +
                                '<td class="detil">' + classInfo.root + '</td>' +
                                '<td class="detil"><button onclick="delClass(' + classInfo.id + ')">' + '删除</button>' +
                                '<button onclick="classDetail(' + classInfo.id + ')">' + '管理</button></td>' +
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


        $(document).ready(function () {
            $("#createForm").submit(function (event) {
                event.preventDefault(); // 阻止默认的表单提交行为

                // 获取表单数据
                var formData = $(this).serialize();
                formData = formData.replace(/className=([^&]*)/, function(match, p1) {
                    return 'className=' + encodeURIComponent(p1);
                });
                formData = formData.replace(/root=([^&]*)/, function(match, p1) {
                    return 'root=' + encodeURIComponent(p1);
                });
                console.log(formData)

                $.post("<%=request.getContextPath()%>/createClass", formData , function (data) {
                    console.log("创建结果：" + data);
                    // 处理服务器的响应
                    if (data === "The creation is successful") {
                        getClassInfo();
                        alert("创建成功");
                    } else if (data === "The ID already exists") {
                        alert("编号已存在");
                    } else if (data === "The class already exists") {
                        alert("班级已存在");
                    } else if (data === "The creation information is incomplete") {
                        alert("创建信息填写不完整");
                    } else {
                        sessionStorage.setItem('classInfo', data);
                    }
                });
            });
        });
    </script>


</div>
</body>
</html>
