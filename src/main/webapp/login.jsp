<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">超市账单管理系统</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" action="/userServlet" >
        <%--隐藏域--%>
        <input type="hidden" name="action" value="login">
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
            <input id="yanzhengm" style="width: 131px;" type="text" name="usercode" placeholder="请输入验证码" required/>
            <!--验证码图片-->
            <img src="/CodeServlet" onclick="changeImage(this);" style="position: relative;top:3px;cursor: pointer;"/>
        <input  value="登录" style="width:100%;margin-top: 13px;" type="submit">
        <hr class="hr20" >
            <div style="color: red">
                <%=request.getAttribute("message")==null?"":request.getAttribute("message")%>
            </div>
    </form>
</div>

</body>
</html>
<script>
    //点击验证码图片的时候，更换验证码
    function changeImage(img) {
        //图片重新加载src地址，因为图片是一个GET请求，浏览器有缓存  time 表示是一个随机参数 ，防止浏览器缓存
        img.src="/CodeServlet?time="+new Date();
    }
</script>
