<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wzu.pojo.User" %>  <!-- 替换为你的 User 类的包路径 -->
<html>
<head>
    <title>User List</title>
</head>
<body>
<h2>User List</h2>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
    </tr>

    <p>Users attribute: <%= request.getAttribute("users") %></p>
    <script>
        console.log(request.getAttribute("users"))

    </script>

    <%
        // 从请求中获取用户列表
        List<User> users = (List<User>) request.getAttribute("users");

        // 遍历用户列表并显示每个用户的数据
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getPassword() %></td>
    </tr>
    <%
        }
    %>

    <!-- 直接输出 request.getAttribute("users") 的内容 -->


</table>
</body>
</html>
