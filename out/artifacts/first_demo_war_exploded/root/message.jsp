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
            padding: 20px;
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
        .form-section {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto;
            width: 80%; /* 增加宽度百分比 */
            max-width: 1200px; /* 可以增加一个更大的最大宽度 */
            position: absolute; /* 或者使用 'relative'，具体根据需求选择 */
            top: 50%; /* 将元素顶部与父容器的50%处对齐 */
            left: 50%; /* 将元素左侧与父容器的50%处对齐 */
            transform: translate(-50%, -50%); /* 使用 transform 属性将元素自身的中心点移动到中心位置 */

        }

        .form-section h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .form-section form {
            display: flex;
            flex-direction: column;
        }

        .form-section label {
            margin-bottom: 5px;
            font-size: 16px;
        }

        .form-section input[type="text"],
        .form-section textarea {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-section input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .form-section input[type="submit"]:hover {
            background-color: #45a049;
        }

        @media (max-width: 600px) {
            .form-section form {
                flex-direction: column;
            }
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
            <div class="form-section">
                <h2>增添消息</h2>
                <form id=addMassage action="/AddMassage" method="post">
                    <!--<label for="id">ID:</label>
                    <input type="text" id="id" name="id"><br><br> -->

                    <!-- <label for="classid">Class ID:</label>
                    <input type="text" id="classid" name="classid"><br><br> -->

                    <label for="title">标题:</label>
                    <input type="text" id="title" name="title" required><br><br>

                    <label for="detail">详情:</label>
                    <textarea id="detail" name="detail" required></textarea><br><br>

                    <label for="root">发布人:</label>
                    <input type="text" id="root" name="root" value="" required><br><br>

                    <input type="submit" value="提交">
                </form>
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

        var rootInput = document.getElementById("root");
        rootInput.value = userInfo.name;

        function getMessage(){
            $(document).ready(function () {
                $("#addMassage").submit(function (event) {
                    event.preventDefault(); // 阻止默认的表单提交行为

                    // 获取表单数据并编码中文姓名
                    var formData = $(this).serialize();
                    formData = formData.replace(/title=([^&]*)/, function(match, p1) {
                        return 'title=' + encodeURIComponent(p1);
                    });
                    formData = formData.replace(/detail=([^&]*)/, function(match, p1) {
                        return 'detail=' + encodeURIComponent(p1);
                    });
                    formData = formData.replace(/root=([^&]*)/, function(match, p1) {
                        return 'root=' + encodeURIComponent(p1);
                    });


                    $.post("/AddMassage", formData , function (data) {
                        console.log("注册结果：" + data);
                        // 处理服务器的响应
                        if (data === "addMassage is successful") {
                            alert("消息发送成功");
                            window.location.href = "message.jsp";
                        } else if (data === "error") {
                            alert("发布失败");
                        }
                    });
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
