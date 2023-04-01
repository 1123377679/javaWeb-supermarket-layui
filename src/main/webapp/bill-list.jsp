<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
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
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" action="/BillsServlet?action=list" method="post">
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="providerid">
                                <option value="-1">---选择供应商---</option>
                                <c:forEach var="a" items="${requestScope.suppliersList}">
                                    <%--做回显--%>
                                    <option value="${a.id}"${requestScope.providerid==a.id?"selected":""}>${a.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="ispay">
                                <option value="-1" ${requestScope.ispay=="-1"?"selected":""}>---是否付款---</option>
                                <option value="1" ${requestScope.ispay=="1"?"selected":""}>已付款</option>
                                <option ${requestScope.ispay=="0"?"selected":""}>待付款</option>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="username" placeholder="请输入商品的名称" autocomplete="off" class="layui-input"></div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn layui-btn-danger" onclick="delAll()">
                        <i class="layui-icon"></i>批量删除</button>
                    <button class="layui-btn" onclick="xadmin.open('添加用户','./bill-add.jsp',800,600)">
                        <i class="layui-icon"></i>添加</button></div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" name="" lay-skin="primary">
                            </th>
                            <th>账单编码</th>
                            <th>商品名称</th>
                            <th>供应商</th>
                            <th>账单金额</th>
                            <th>是否付款</th>
                            <th>商品单位</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.billList}" var="p">
                            <tr>
                                <td>
                                    <input type="checkbox" name="" lay-skin="primary">
                                </td>
                                <td>${p.id}</td>
                                <td>${p.title}</td>
                                <td>${p.providerName}</td>
                                <td>${p.money}</td>
                                <td>${p.ispay==1?"已付款":"未付款"}</td>
                                <td>${p.unit}</td>
                                <td class="td-manage">
                                    <a title="个人信息"  onclick="xadmin.open('个人信息','/BillsServlet?action=details&id=${p.id}',600,400)" href="javascript:;">
                                        <i class="layui-icon">&#xe66e;</i>
                                    </a>
                                    <a title="编辑"  onclick="xadmin.open('编辑','/BillsServlet?action=goupdate&id=${p.id}',600,400)" href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" href="javascript:deleteUsers(${p.id});">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body">
                    <div class="page">
                        <div>
                            <a class="prev" href="">&lt;&lt;</a>
                            <a class="num" href="">1</a>
                            <span class="current">2</span>
                            <a class="num" href="">3</a>
                            <a class="num" href="">489</a>
                            <a class="next" href="">&gt;&gt;</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function deleteUsers(id){
        layer.msg(' 确定要删除吗?', {
            time: 20000, //20s后自动关闭
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                self.location = '/BillsServlet?action=delete&id='+id;//确定按钮跳转地址
            }
        });
    }
    $('#layerDemo .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
</script>
<script>
    layui.use(['laydate', 'form'],
    function() {
        var laydate = layui.laydate;

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
function member_stop(obj, id) {
    layer.confirm('确认要停用吗？',
        function(index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用');
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {
                    icon: 5,
                    time: 1000
                });

            } else {
                $(obj).attr('title', '启用');
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {
                    icon: 5,
                    time: 1000
                });
            }

        });
}

/*用户-删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？',
        function(index) {
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {
                icon: 1,
                time: 1000
            });
        });
}

function delAll(argument) {

    var data = tableCheck.getData();

    layer.confirm('确认要删除吗？' + data,
        function(index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {
                icon: 1
            });
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
}</script>

</html>
