<%@ page import="cn.lanqiao.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<%
    User users = (User) request.getAttribute("users");
%>
<div class="layui-form-item">
    <label class="layui-form-label">用户编号:</label><span style="position: relative;top: 11px"><%=users.getId()%></span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">用户名:</label><span style="position: relative;top: 11px"><%=users.getUsername()%></span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">用户性别:</label>
    <span style="position: relative;top: 11px">
    <%=users.getSex().toString().equals("1")?"男":"女"%>
</span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">出生日期:</label><span style="position: relative;top: 11px"><%=users.getBirthday()%></span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">用户电话:</label><span style="position: relative;top: 11px"><%=users.getPhone()%></span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">用户地址:</label><span style="position: relative;top: 11px"><%=users.getAddress()%></span>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">用户类别:</label>
    <span style="position: relative;top: 11px">
    <%
        if (users.getType().toString().equals("1")){
            out.print("管理员");
        }else if (users.getType().toString().equals("2")){
            out.print("经理");
        }else {
            out.print("普通员工");
        }
    %>
    </span>
</div>
</body>
</html>
