<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
  <meta charset="UTF-8">
  <title>欢迎页面-X-admin2.2</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="./css/font.css">
  <link rel="stylesheet" href="./css/xadmin.css">
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.3/jquery.js"></script>
  <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="./js/xadmin.js"></script>
  <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>

  <![endif]-->
</head>

<body>
<div class="layui-fluid">
  <div class="layui-row">
    <form class="layui-form" action="/userServlet?action=updatePwd" method="post" onsubmit="return checkAll();">
      <div class="layui-form-item">
        <label for="oldPassword" class="layui-form-label">
          <span class="x-red">*</span>旧密码
        </label>
        <div class="layui-input-inline">
          <input type="text" id="oldPassword" name="oldpass" lay-verify="required" autocomplete="off" class="layui-input"/>
        </div>
        <div class="layui-form-mid" id="oldPasswordSpan"></div>
      </div>
      <div class="layui-form-item">
        <label for="newPassword" class="layui-form-label">
          <span class="x-red">*</span>新密码
        </label>
        <div class="layui-input-inline">
          <input type="text" id="newPassword" name="newpass" required="" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid" id="newPasswordSpan"></div>
      </div>
      <div class="layui-form-item">
        <label for="confirmPassword" class="layui-form-label">
          <span class="x-red">*</span>确认密码
        </label>
        <div class="layui-input-inline">
          <input type="text" id="confirmPassword" name="repass" required="" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid" id="reconfirmPassword"></div>
      </div>
      <div class="layui-form-item" style="margin-left: 180px">
        <button class="layui-btn" lay-filter="save" lay-submit="" type="submit">修改密码</button>
      </div>
    </form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
  layui.use('layer', function () {
    var $ = layui.jquery, layer = layui.layer;
    //全局变量
    var checkoldPassword = false;
    //检查原密码合法性
    var oldPassword = document.querySelector('#oldPassword');
    //文本框焦点事件
    oldPassword.onblur = function checkOldPassword(){
      //获取原密码
      var oldPassword = $("#oldPassword").val();
      if (oldPassword == ""){
        $("#oldPasswordSpan").text("原密码不能为空!");
        $("#oldPasswordSpan").css("color","red");
        checkoldPassword = false;
      }
      //验证用户输入的密码是否正确，Jquery版本的Ajax请求
      $.ajax({
        type:"POST",
        url:"userServlet",
        data:"action=checkOldPass&oldPassword="+oldPassword,
        dataType:"text",//服务器响应数据的格式
        //result:后端响应的数据
        success:function (result){
          // alert("服务器响应的数据:"+result);
          //根据后端响应的数据进行判断
          if (result=="0"){
            $("#oldPasswordSpan").text("原密码填写错误!");
            $("#oldPasswordSpan").css("color","red");
            checkoldPassword = false;
          }else if (result=="1"){
            $("#oldPasswordSpan").text("√");
            $("#oldPasswordSpan").css("color","green");
            checkoldPassword = true;
          }
        }
      });
    }
    //新密码框
    var newPassword = document.querySelector('#newPassword');
    //鼠标离开焦点事件
    newPassword.onblur = function checkNewPassword(){
      //原始密码
      var oldPassword = $("#oldPassword").val();
      //新密码
      var newPassword = $("#newPassword").val();
      if (newPassword == ""){
        $("#newPasswordSpan").text("新密码不能为空!");
        $("#newPasswordSpan").css("color","red");
        checkoldPassword = false;
      }
      if (newPassword == oldPassword){
        $("#newPasswordSpan").text("新密码不能和原密码相同!");
        $("#newPasswordSpan").css("color","red");
        checkoldPassword = false;
      }
        $("#newPasswordSpan").text("√");
        $("#newPasswordSpan").css("color","green");
      checkoldPassword = true;
    }
    //确认新密码框
    var confirmPassword = document.querySelector('#confirmPassword');
    confirmPassword.onblur = function checkerNewPassword(){
      //新密码
      var newPassword = $("#newPassword").val();
      //确认新密码
      var confirmPassword = $("#confirmPassword").val();
      if(newPassword!=confirmPassword){
        $("#reconfirmPassword").text("两次密码输入不相同!");
        $("#reconfirmPassword").css("color","red");
        checkoldPassword = false;
      }
      $("#reconfirmPassword").text("√");
      $("#reconfirmPassword").css("color","green");
      checkoldPassword = true;
    }

    function checkAll(){
      return checkoldPassword&&checkNewPassword()&&checkerNewPassword();
    }
  });
</script>