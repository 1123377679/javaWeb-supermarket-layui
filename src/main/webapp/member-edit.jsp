<%@ page import="cn.lanqiao.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%
    //获取users对象
    User users = (User) request.getAttribute("users");
%>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" action="/userServlet?action=update&id=<%=users.getId()%>" method="post">
                <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">
                    <span class="x-red">*</span>用户名</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="userName" required="" lay-verify="email" autocomplete="off" class="layui-input" value="<%=users.getUsername()%>"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>将会成为您唯一的登入名
                </div>
                </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="1" title="男" <%=users.getSex()==1?"checked":""%>>
                    <input type="radio" name="sex" value="0" title="女" <%=users.getSex()==0?"checked":""%>>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">生日日期</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birthday" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="<%=users.getBirthday()%>">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>用户电话</label>
                <div class="layui-input-inline">
                    <input type="text" id="userphone" name="userphone" required="" lay-verify="repass" autocomplete="off" class="layui-input" value="<%=users.getPhone()%>">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>用户地址</label>
                <div class="layui-input-inline">
                    <input type="text" id="userAddress" name="userAddress" required="" lay-verify="repass" autocomplete="off" class="layui-input" value="<%=users.getAddress()%>">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户类别</label>
                <div class="layui-input-block">
                    <input type="radio" name="userlei" value="1" title="管理员" <%=users.getType()==1?"checked":""%>>
                    <input type="radio" name="userlei" value="2" title="经理" <%=users.getType()==2?"checked":""%>>
                    <input type="radio" name="userlei" value="3" title="普通用户" <%=users.getType()==3?"checked":""%>>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" type="submit">保存</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer','jquery'],
        function() {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;

            //自定义验证规则
            form.verify({
                nikename: function(value) {
                    if (value.length < 5) {
                        return '昵称至少得5个字符啊';
                    }
                },
                pass: [/(.+){6,12}$/, '密码必须6到12位'],
                repass: function(value) {
                    if ($('#L_pass').val() != $('#L_repass').val()) {
                        return '两次密码不一致';
                    }
                }
            });

            //监听提交
            form.on('submit(add)',
                function(data) {
                    console.log(data);
                    //发异步，把数据提交给php
                    layer.alert("增加成功", {
                            icon: 6
                        },
                        function() {
                            //关闭当前frame
                            xadmin.close();

                            // 可以对父窗口进行刷新
                            xadmin.father_reload();
                        });
                    return false;
                });

        });</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
