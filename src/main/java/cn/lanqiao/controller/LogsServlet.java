package cn.lanqiao.controller;

import cn.lanqiao.pojo.Logs;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.LogsDaoService;
import cn.lanqiao.service.impl.LogsDaoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/LogsServlet")
public class LogsServlet extends HttpServlet {
    //业务逻辑层
    LogsDaoService logsService = new LogsDaoServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应的代码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取前端的业务请求
        String value = req.getParameter("action");
        //显示列表
        if (value.equals("mylogs")){
            //登录成功会将信息存在session中，在这里直接获取session就能够拿到用户的登录信息了
            HttpSession session = req.getSession();
            User loginUser = (User) session.getAttribute("loginUser");
            //查询登录日志
            List<Logs> logs = logsService.queryMyLogs(loginUser.getUsername());
            //存值转发
            req.setAttribute("logs",logs);
            req.getRequestDispatcher("/login-log.jsp").forward(req,resp);
        }
        //删除
        if (value.equals("delete")){
            String id = req.getParameter("id");
            int i = logsService.deleteById(id);
            PrintWriter out = resp.getWriter();
            if (i>0){
                out.print("<script>" +
                        "alert('删除成功');" +
                        "window.location.href = '/LogsServlet?action=mylogs'" +
                        "</script>"
                );
            }else {
                out.print("<script>" +
                        "alert('删除失败');" +
                        "window.location.href = '/LogsServlet?action=mylogs'" +
                        "</script>"
                );
            }
        }
    }
}
