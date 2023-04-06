package cn.lanqiao.controller;

import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.DataService;
import cn.lanqiao.service.ReportService;
import cn.lanqiao.service.SupplierService;
import cn.lanqiao.service.impl.DataServiceImpl;
import cn.lanqiao.service.impl.ReportServiceImpl;
import cn.lanqiao.service.impl.SupplierServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 大数据首页信息展示
 */
@WebServlet("/IndexServlet.do")
public class IndexServlet extends HttpServlet {
    SupplierService supplierService = new SupplierServiceImpl();
    DataService dataService = new DataServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应的代码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取前端的业务请求
        String value = req.getParameter("action");
        //柱状图请求
        if (value.equals("BillingNum")){
            //System.out.println("前端发送的请求获取到了");
            //这是一个Java对象
            //查询所有的供应商
            List<Supplier> suppliersList = supplierService.selectAll(null);
            //通过fastJson转换字符串
            String jsonString = JSONObject.toJSONString(suppliersList);
            PrintWriter writer = resp.getWriter();
            writer.print(jsonString);
        }
        //折线图请求
        if (value.equals("peopleNum")){
            //System.out.println("折线图请求发送过来");
            User user = new User();
            ArrayList<Integer> totalCount = dataService.getTotalCount(user);
            //System.out.println(totalCount);
            String jsonString = JSONObject.toJSONString(totalCount);
            PrintWriter writer = resp.getWriter();
            writer.print(jsonString);
        }
    }
}
