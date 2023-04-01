package cn.lanqiao.controller;

import cn.lanqiao.pojo.Logs;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.LogsDaoService;
import cn.lanqiao.service.UserService;
import cn.lanqiao.service.impl.LogsDaoServiceImpl;
import cn.lanqiao.service.impl.UserServiceImpl;
import cn.lanqiao.utils.DateUtils;
import cn.lanqiao.utils.IpAddressUtils;
import cn.lanqiao.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    LogsDaoService logsService = new LogsDaoServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String value = req.getParameter("action");
        //获取session
        HttpSession session = req.getSession();
        IpAddressUtils ipAddressUtils = new IpAddressUtils();
        //登录
        if (value.equals("login")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //前端输入的验证码
            String usercode = req.getParameter("usercode");
            //系统生成的验证码
            String syscode = String.valueOf(session.getAttribute("syscode"));
            if (syscode.equalsIgnoreCase(usercode)){
                User user = new User(username,password);
                User login = userService.login(user);
                //将登录的信息存在session中
                session.setAttribute("loginUser",login);
                if (login != null){
                    //用户登录成功,记录日志到数据库中
                    Logs logs = new Logs(null,login.getUsername(), ipAddressUtils.getAddress(), ipAddressUtils.getIp(),DateUtils.nowTime(),0);
                    int i = logsService.addLogs(logs);
                    resp.sendRedirect("/index.jsp");
                }else {
                    req.setAttribute("message","账号或密码错误");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }
            }else {
                req.setAttribute("message","验证码错误!");
                //重新转发
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }
        //退出
        if (value.equals("logout")){
            session.invalidate();
            resp.sendRedirect("login.jsp");
        }
        //查询
        if (value.equals("list")){
            //前端传递的查询信息
            String username = req.getParameter("username");
            //通过数据库查询到的信息
            List<User> userList = userService.selectAll(username);
            //将数据保存起来
            req.setAttribute("userList",userList);
            //搜索回显
            req.setAttribute("username",username);
            //转发到页面去
            req.getRequestDispatcher("member-list.jsp").forward(req,resp);
        }
        //新增
        if (value.equals("add")){
            //获取前端的数据
            String userName = req.getParameter("userName");
            String userpassword = req.getParameter("userpassword");
            String sex = req.getParameter("sex");
            String birthday = req.getParameter("birthday");
            String userphone = req.getParameter("userphone");
            String userAddress = req.getParameter("userAddress");
            String userlei = req.getParameter("userlei");
            //封装对象
            User users = new User(userName,userpassword,Integer.valueOf(sex),birthday,userphone,userAddress,Integer.valueOf(userlei));
            System.out.println("要新增的对象是:"+users);
            int i = userService.add(users);
            PrintWriter writer = resp.getWriter();
            if (i>0){
                writer.print("<script>" +
                        "alert('用户新增成功');" +
                        "window.parent.location.href = '/userServlet?action=list'" +
                        "</script>");
            }else {
                writer.print("<script>" +
                        "alert('用户新增失败');location.href = '/member-add.jsp'" +
                        "</script>");
            }
        }
        //跳转更新页面回显
        if (value.equals("goUpdate")){
            String id = req.getParameter("id");
            User users = userService.findById(id);
            req.setAttribute("users",users);
            req.getRequestDispatcher("/member-edit.jsp").forward(req,resp);
        }
        //更新操作
        if (value.equals("update")){
            String id = req.getParameter("id");
            String userName = req.getParameter("userName");
            String sex = req.getParameter("sex");
            String birthday = req.getParameter("birthday");
            String userphone = req.getParameter("userphone");
            String userAddress = req.getParameter("userAddress");
            String userlei = req.getParameter("userlei");
            User user = new User(Integer.valueOf(id),userName,Integer.valueOf(sex),birthday,userphone,userAddress,Integer.valueOf(userlei));
            System.out.println("要更新的对象:"+user);
            int count = userService.updateUser(user);
            System.out.println(count);
            PrintWriter writer = resp.getWriter();
            if (count>0){
                writer.print("<script>" +
                        "alert('用户修改成功');" +
                        "window.parent.location.href = '/userServlet?action=list'" +
                        "</script>");
            }else {
                writer.print("<script>" +
                        "alert('用户修改失败');location.href = /userServlet?action=goUpdate&id="+id+";</script>");
            }
        }
        //显示个人信息
        if (value.equals("details")){
            //获取用户要显示的详情id
            String id = req.getParameter("id");
            User users = userService.findById(id);
            System.out.println(users);
            req.setAttribute("users",users);
            req.getRequestDispatcher("/user-view.jsp").forward(req,resp);
        }
        //根据id删除
        if (value.equals("delete")){
            String id = req.getParameter("id");
            int i = userService.deleteById(id);
            PrintWriter writer = resp.getWriter();
            if (i>0){
                writer.print("<script>" +
                        "alert('用户删除成功');" +
                        "window.location.href = '/userServlet?action=list'" +
                        "</script>");
            }else {
                writer.print("<script>" +
                        "alert('用户修改失败');" +
                        "window.location.href = '/userServlet?action=list'" +
                        "</script>");
            }
        }
        //检查用户输入的原密码是否正确
        if(value.equals("checkOldPass")){
            //用户输入的原密码
            String oldPassword = req.getParameter("oldPassword");
            //获取当前登录的密码
            User users = (User) session.getAttribute("loginUser");
            //判断
            PrintWriter writer = resp.getWriter();
            if (users.getPassword().equals(oldPassword)){
                //原始密码输入正确
                writer.print("1");
            }else {
                //原始密码输入不正确
                writer.print("0");
            }
        }
        //修改当前用户密码
        if (value.equals("updatePwd")){
            //获取当前登录的密码
            User users = (User) session.getAttribute("loginUser");
            //旧密码
            String oldpass = req.getParameter("oldpass");
            //新密码
            String newpass = req.getParameter("newpass");
            //确认密码
            String repass = req.getParameter("repass");
            //判断
            PrintWriter writer = resp.getWriter();
            if (oldpass.equals(users.getPassword())){
                if (!oldpass.equals(newpass)){
                    if (newpass.equals(repass)){
                        //修改密码
                        int i = userService.updatePwd(users.getId(), newpass);
                        System.out.println(i);
                        if (i>0){
                            //密码修改成功,清空Session，跳转登录
                            session.invalidate();
                            writer.print(
                                    "<script>" +
                                            "alert('密码修改完毕，重新登录!');" +
                                            "window.parent.location.href = '/login.jsp';" +
                                    "</script>"
                            );
                        }else{
                            writer.print(
                                    "<script>" +
                                            "alert('密码修改异常!');" +
                                            "window.location.href = '/member-password.jsp';" +
                                    "</script>"
                            );
                        }
                    }else {
                        writer.print(
                                "<script>" +
                                        "alert('两次输入的密码必须一致!');" +
                                        "window.location.href = '/member-password.jsp';" +
                                "</script>"
                        );
                    }
                }else {
                    writer.print(
                            "<script>" +
                                    "alert('要修改的密码不能跟旧密码相同!');" +
                                    "window.location.href = '/member-password.jsp';" +
                                    "</script>"
                    );
                }
            }else {
                writer.print(
                        "<script>" +
                                "alert('原始密码输入错误!');" +
                                "window.location.href = '/member-password.jsp';" +
                        "</script>"
                );
            }

        }
        //分页功能
        if (value.equals("limit")){
            //当前页
            String pageIndex = req.getParameter("pageIndex");
            //System.out.println("当前页:"+pageIndex);
            //每页显示条数
            String pageSize = req.getParameter("pageSize");
            //System.out.println("每页显示条数:"+pageSize);
            //前端传递的信息
            String name = req.getParameter("username");
            //System.out.println("name:"+name);
            //总条数
            int totalCount = userService.getTotalCount();
            //System.out.println("总条数:"+totalCount);
            //每页数据
            List<User> departs = userService.getDeparts(name,(Integer.parseInt(pageIndex)-1)*(Integer.parseInt(pageSize)),Integer.parseInt(pageSize));
            //System.out.println("查询出来的每页数据"+departs);
            PageUtils pageUtils = new PageUtils<>(Integer.parseInt(pageIndex),Integer.parseInt(pageSize),totalCount,departs);
            //System.out.println(pageUtils.getRecords());
            System.out.println(pageUtils);
            //将查出来的值保存起来
            req.setAttribute("departs",departs);
            //存值转发
            req.setAttribute("pageUtils",pageUtils);
            req.getRequestDispatcher("/member-list.jsp").forward(req,resp);
        }
    }
}
