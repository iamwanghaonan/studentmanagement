项目结构
项目主要包含以下几个部分：

web 目录：存放前端页面文件，如 JSP 页面，用于展示和交互。
WEB-INF：包含应用程序的配置文件和资源。
application：可能存放应用程序的相关资源。
img：存放图片资源。
insert.jsp、login.jsp、userList.jsp 等：JSP 页面，用于用户注册、登录和用户列表展示等功能。
lib 目录：存放项目依赖的 JAR 包，包含 Java EE 相关的库，如 Servlet、JSP、JSTL 等。
src 目录：存放 Java 源代码，按照包结构组织。
com.wzu：主要的包，包含多个子包和类。
dao：数据访问对象层，包含与数据库交互的接口和实现类。
pojo：实体类包，定义了系统中的数据模型。
service：业务逻辑层，包含业务逻辑的接口和实现类。
servlet：Servlet 层，处理 HTTP 请求和响应。
filter：过滤器包，包含登录过滤器。
utils：工具类包，包含 JDBC 工具类。
db.properties 文件：数据库配置文件，包含数据库驱动、URL、用户名和密码等信息。
主要功能模块
1. 用户管理
登录功能：LoginServlet 处理用户登录请求，验证用户信息，并将用户信息以 JSON 格式返回给前端。
注册功能：InsertServlet 处理用户注册请求，验证注册信息，并将用户信息插入到数据库中。
修改密码功能：changePasswordServlet 处理用户修改密码请求，验证旧密码，并更新数据库中的密码信息。
用户列表展示：UserServlet 获取所有用户信息，并将其转发到 userList.jsp 页面进行展示。
2. 消息管理
消息列表展示：ShowMassageServlet 获取所有消息信息，并将其以 JSON 格式返回给前端。
消息详情获取：getMessageInfoServlet 根据消息 ID 获取消息详情，并将其以 JSON 格式返回给前端。
消息回复功能：sendReplyServlet 处理消息回复请求，将回复信息插入到数据库中。
消息回复列表获取：getReplyServlet 根据消息 ID 获取消息回复列表，并将其以 JSON 格式返回给前端。
添加消息功能：AddMassage 处理添加消息请求，将消息信息插入到数据库中。
3. 班级管理
班级信息获取：GetClassServlet 获取所有班级信息，并将其以 JSON 格式返回给前端。
班级申请信息获取：GetApplyServlet 根据班级 ID 获取班级申请信息，并将其以 JSON 格式返回给前端。
批准班级申请：approveApplyServlet 处理批准班级申请请求，将用户添加到班级中，并删除申请信息。
拒绝班级申请：rejectApplyServlet 处理拒绝班级申请请求，删除申请信息。
4. 过滤器
登录过滤器：LoginFilter 过滤 /apllication/* 和 /root/* 路径的请求，验证用户是否已登录，如果未登录则重定向到登录页面。
数据库连接
项目使用 JDBC 连接 MySQL 数据库，通过 JDBCUtils 工具类实现数据库连接和资源释放。数据库配置信息存储在 db.properties 文件中。
技术栈
后端：Java、Servlet、JSP、JDBC
前端：JSP、HTML、CSS（未展示在代码中）
数据库：MySQL
JSON 处理：Jackson 库，用于将 Java 对象转换为 JSON 格式字符串。
