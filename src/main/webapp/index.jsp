<%@ page import="cn.lanqiao.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        // 是否开启刷新记忆tab功能
        // var is_remember = false;
    </script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="./index.jsp">超市账单管理系统</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">
                        <i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>
                <dd>
                    <a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">
                        <i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>
                <dd>
                    <a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">
                        <i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>
                <dd>
                    <a onclick="xadmin.add_tab('在tab打开','member-list.html')">
                        <i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>
                <dd>
                    <a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">
                        <i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>
            </dl>
        </li>
    </ul>
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        User users = (User) session.getAttribute("users");
        String username = null;
        if (loginUser != null){
            username = loginUser.getUsername();
        }else {
            //用户没有登录或者登录已失效
            response.sendRedirect("/login.jsp");
        }
    %>
    <ul class="layui-nav right" lay-filter="" id="layerDemo">
        <li class="layui-nav-item">
            <a href="javascript:;"><%=username%></a>
            <dl class="layui-nav-child">
                <dd>
                    <a onclick="xadmin.open('切换帐号','/login.jsp')">切换帐号</a></dd>
                <dd class="layui-btn-container">
                    <a href="#" style="margin: 2px; background-color: transparent;" data-method="confirmTrans" class="layui-btn">
                        退出
                    </a>
                </dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="/">前台首页</a></li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="会员管理">&#xe6b8;</i>
                    <cite>会员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <%--前端发送请求,需要后台拿到action=list数据--%>
                        <a onclick="xadmin.add_tab('会员列表','/userServlet?action=limit&pageIndex=1&pageSize=5')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>会员列表</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="供应商管理">&#xe6b8;</i>
                    <cite>供应商管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <%--前端发送请求,需要后台拿到action=list数据--%>
                        <a onclick="xadmin.add_tab('供应商列表','/SupplierServlet?action=list')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>供应商列表</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="账单管理">&#xe723;</i>
                    <cite>账单管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('账单管理','/BillsServlet?action=list')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>账单列表</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('统计页面','/ReportServlet?action=show')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>账单统计(柱状图)</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('统计页面','/ReportServlet?action=pie')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>账单统计(饼状图)</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="供应商管理">&#xe6b8;</i>
                    <cite>登录日志</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <%--前端发送请求,需要后台拿到action=list数据--%>
                        <a onclick="xadmin.add_tab('登录日志','/LogsServlet?action=mylogs')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户登录日志</cite></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="供应商管理">&#xe6b8;</i>
                    <cite>数据导入</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <%--前端发送请求,需要后台拿到action=list数据--%>
                        <a onclick="xadmin.add_tab('用户数据导入','#')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户数据导入</cite></a>
                    </li>
                </ul>
            </li>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="分类管理">&#xe723;</i>--%>
<%--                    <cite>分类管理</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('多级分类','cate.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>多级分类</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="城市联动">&#xe723;</i>--%>
<%--                    <cite>城市联动</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('三级地区联动','city.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>三级地区联动</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="管理员管理">&#xe726;</i>--%>
<%--                    <cite>管理员管理</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('管理员列表','admin-list.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>管理员列表</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('角色管理','admin-role.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>角色管理</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('权限分类','admin-cate.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>权限分类</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('权限管理','admin-rule.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>权限管理</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="系统统计">&#xe6ce;</i>--%>
<%--                    <cite>系统统计</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('拆线图','echarts1.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>拆线图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('拆线图','echarts2.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>拆线图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('地图','echarts3.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>地图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('饼图','echarts4.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>饼图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('雷达图','echarts5.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>雷达图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('k线图','echarts6.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>k线图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('热力图','echarts7.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>热力图</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('仪表图','echarts8.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>仪表图</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="图标字体">&#xe6b4;</i>--%>
<%--                    <cite>图标字体</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('图标对应字体','unicode.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>图标对应字体</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="其它页面">&#xe6b4;</i>--%>
<%--                    <cite>其它页面</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a href="login.html" target="_blank">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>登录页面</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('错误页面','error.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>错误页面</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('示例页面','demo.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>示例页面</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('更新日志','log.html')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>更新日志</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="javascript:;">--%>
<%--                    <i class="iconfont left-nav-li" lay-tips="第三方组件">&#xe6b4;</i>--%>
<%--                    <cite>layui第三方组件</cite>--%>
<%--                    <i class="iconfont nav_right">&#xe697;</i></a>--%>
<%--                <ul class="sub-menu">--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('滑块验证','https://fly.layui.com/extend/sliderVerify/')" target="">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>滑块验证</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('富文本编辑器','https://fly.layui.com/extend/layedit/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>富文本编辑器</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('eleTree 树组件','https://fly.layui.com/extend/eleTree/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>eleTree 树组件</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('图片截取','https://fly.layui.com/extend/croppers/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>图片截取</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('formSelects 4.x 多选框','https://fly.layui.com/extend/formSelects/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>formSelects 4.x 多选框</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('Magnifier 放大镜','https://fly.layui.com/extend/Magnifier/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>Magnifier 放大镜</cite></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a onclick="xadmin.add_tab('notice 通知控件','https://fly.layui.com/extend/notice/')">--%>
<%--                            <i class="iconfont">&#xe6a7;</i>--%>
<%--                            <cite>notice 通知控件</cite></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd></dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<script>//百度统计可去掉
var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
<script>
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

        //触发事件
        var active = {
            setTop: function () {
                var that = this;
                //多窗口模式，层叠置顶
                layer.open({

                });
            }
            , confirmTrans: function () {
                //配置一个透明的询问框
                layer.msg(' 确定要退出吗?', {
                    time: 20000, //20s后自动关闭
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        self.location = 'login.jsp';//确定按钮跳转地址
                    }
                    //回调函数
                    // yes: function(index, layero){
                    //     self.location='http://www.baidu.com';//确定按钮跳转地址
                    // },
                    // btn2: function(index, layero){
                    //     self.location='http://www.163.com';//取消按钮跳转地址
                    // },
                    // cancel: function(index,layero){ //按右上角“X”按钮跳转地址
                    //     self.location='http://www.qq.com';
                    // },
                });
            },
        };

        $('#layerDemo .layui-btn').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });
    });
</script>
