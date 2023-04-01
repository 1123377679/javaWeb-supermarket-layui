<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lanqiao.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.3/jquery.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" action="/userServlet?action=limit&pageIndex=${requestScope.pageUtils.pageIndex}&pageSize=${requestScope.pageUtils.pageSize}" method="post">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
                    <button class="layui-btn" onclick="xadmin.open('添加用户','/member-add.jsp',600,400)"><i class="layui-icon"></i>添加</button>
                    <button class="layui-btn" onclick="downloadExcelModel()"><i class="layui-icon"></i>下载模板</button>
                    <button type="button" class="layui-btn" id="test3" name="file"><i class="layui-icon"></i>上传文件</button>
                </div>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" lay-filter="checkall" name="" lay-skin="primary">
                            </th>
                            <th>用户编码</th>
                            <th>用户名称</th>
                            <th>密码</th>
                            <th>性别</th>
                            <th>生日</th>
                            <th>电话</th>
                            <th>地址</th>
                            <th>用户类型</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.pageUtils.records}" var="u">
                        <tr>
                            <td>
                                <input type="checkbox" name="id" value="1"   lay-skin="primary">
                            </td>
                            <td>${u.id}</td>
                            <td>${u.username}</td>
                            <td>${u.password}</td>
                            <td>${u.sex==1?"男":"女"}</td>
                            <td>${u.birthday}</td>
                            <td>${u.phone}</td>
                            <td>${u.address}</td>
                            <td>
                                <c:if test="${u.type == 1}">
                                    管理员
                                </c:if>
                                <c:if test="${u.type == 2}">
                                    经理
                                </c:if>
                                <c:if test="${u.type == 3}">
                                    普通用户
                                </c:if>
                            </td>
                            <td class="td-manage">
                                <a title="个人信息"  onclick="xadmin.open('个人信息','userServlet?action=details&id=${u.id}',600,400)" href="javascript:;">
                                    <i class="layui-icon">&#xe66e;</i>
                                </a>
                                <a title="编辑"  onclick="xadmin.open('编辑','userServlet?action=goUpdate&id=${u.id}',600,400)" href="javascript:;">
                                    <i class="layui-icon">&#xe642;</i>
                                </a>
                                <a onclick="xadmin.open('修改密码','member-password.jsp',600,400)" title="修改密码" href="javascript:;">
                                    <i class="layui-icon">&#xe631;</i>
                                </a>
                                <a title="删除" href="javascript:deleteUsers(${u.id});">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
                    <a href="/userServlet?action=limit&pageIndex=${requestScope.pageUtils.pageIndex-1}&pageSize=${requestScope.pageUtils.pageSize}"
                       class="layui-laypage-prev ${requestScope.pageUtils.pageIndex==1?'layui-btn-disabled disabled':''}" data-page="0">
                        <i class="layui-icon">&lt;</i>
                    </a>
                    <c:forEach begin="${requestScope.pageUtils.numberStart}" end="${requestScope.pageUtils.numberEnd}" var="num" step="1">
                        <c:if test="${requestScope.pageUtils.pageIndex==num}">
                            <span style="color:red;font-weight: bold;">${num}</span>
                        </c:if>
                        <c:if test="${requestScope.pageUtils.pageIndex!=num}">
                            <a href="/userServlet?action=limit&pageIndex=${num}&pageSize=${requestScope.pageUtils.pageSize}">${num}</a>
                        </c:if>
                    </c:forEach>
                    <a href="/userServlet?action=limit&pageIndex=${requestScope.pageUtils.pageIndex+1}&pageSize=${requestScope.pageUtils.pageSize}"
                       class="layui-laypage-next ${requestScope.pageUtils.pageIndex==requestScope.pageUtils.pageCount?'layui-btn-disabled':''}"
                       style="${requestScope.pageUtils.pageIndex==requestScope.pageUtils.pageCount?'disabled:true;':''}" data-page="2">
                        <i class="layui-icon">&gt;</i>
                    </a>
                    <span class="layui-laypage-skip">到第
							   <input type="text" min="1" id="number" value="${requestScope.pageUtils.pageIndex}" class="layui-input">页
								<button type="button" class="layui-laypage-btn" onclick="jumpPage();">确定</button>
							</span>
                    <span class="layui-laypage-count">[当前${requestScope.pageUtils.pageIndex}/${requestScope.pageUtils.pageCount}]</span>
                    <span class="layui-laypage-limits">
							    <select lay-ignore="" onchange="goPage(this)">
									<option value="5" ${requestScope.pageUtils.pageSize==5?"selected":""}>5 条/页</option>
									<option value="10" ${requestScope.pageUtils.pageSize==10?"selected":""}>10 条/页</option>
									<option value="20" ${requestScope.pageUtils.pageSize==20?"selected":""}>20 条/页</option>
									<option value="30" ${requestScope.pageUtils.pageSize==30?"selected":""}>30 条/页</option>
									<option value="40" ${requestScope.pageUtils.pageSize==40?"selected":""}>40 条/页</option>
							</select>
							</span>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    /**
     * 动态修改页面大小
     */
    function goPage(select) {
        var pageSize = $(select).val();
        location.href = "/userServlet?action=limit&pageIndex=1&pageSize=" + pageSize;
    }

    /**
     * 跳转页码
     */
    function jumpPage() {
        var number = $("#number").val();
        // alert(number);
        if (number <= 0) {
            number = 1;
        } else if (!(/(^[1-9]\d*$)/.test(number))) {
            alert("输入的页码非法！");
            $("#number").val("");
            $("#number").focus();
            return;//终止
        } else if (number >${requestScope.pageUtils.pageCount}) {
            number =${requestScope.pageUtils.pageCount};
        }
        location.href = "/userServlet?action=limit&pageIndex=" + number + "&pageSize=${requestScope.pageUtils.pageSize}";
    }
    function downloadExcelModel(){
        location.href = "/ExcelServlet?action=downloadExcelModel";
    }
    function deleteUsers(id){
        layer.msg(' 确定要删除吗?', {
            time: 20000, //20s后自动关闭
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                self.location = '/userServlet?action=delete&id='+id;//确定按钮跳转地址
            }
        });
    }
    $('#layerDemo .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
</script>
</html>
<script>
    layui.use(['laydate','form','upload','laypage','layer'], function(){
        var laydate = layui.laydate;
        var form = layui.form;
        var upload = layui.upload;
        var laypage = layui.laypage;
        var layer = layui.layer;

        //完整功能
        laypage.render({
            elem: 'demo7'
            ,count: 100
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj){
                console.log(obj)
            }
        });
        // 指定允许上传的文件类型
        upload.render({
            elem: '#test3'
            ,url: '/ExcelServlet?action=excelImport' //此处配置你自己的上传接口即可
            ,accept: 'file' //普通文件
            ,multiple:true
            ,done: function(res){
                console.log(res);
                //如果上传成功
                if(res.code == 200){
                    layer.msg('上传成功',{

                    },function (){
                        window.location.reload();
                    });
                }else if (res.code == 500){
                    //上传失败
                    layer.msg('上传失败',{

                    },function (){
                        window.location.reload();
                    });
                }
            }
        });
        // 监听全选
        form.on('checkbox(checkall)', function(data){

            if(data.elem.checked){
                $('tbody input').prop('checked',true);
            }else{
                $('tbody input').prop('checked',false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });


    });

    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){

            if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }


    function delAll (argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
