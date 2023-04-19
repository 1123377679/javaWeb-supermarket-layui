package cn.lanqiao.controller;

import cn.lanqiao.pojo.User;
import cn.lanqiao.service.AuthorityService;
import cn.lanqiao.service.impl.AuthorityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AuthorityServlet")
public class AuthorityServlet extends HttpServlet {
    AuthorityService authorityService = new AuthorityServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String value = req.getParameter("action");
        //查询
        if (value.equals("list")){
            //前端传过来的数据
            String username = req.getParameter("username");
            //通过数据库查询所有的数据
            List<User> usersList = authorityService.selectAll(username);
            System.out.println(usersList);
            //将数据都保存起来
            req.setAttribute("usersList",usersList);
            //转发页面
            req.getRequestDispatcher("/bill-cate.jsp").forward(req,resp);
        }
    }
}
