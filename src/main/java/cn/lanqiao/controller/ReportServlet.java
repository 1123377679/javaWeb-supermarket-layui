package cn.lanqiao.controller;

import cn.lanqiao.pojo.Pie;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.service.ReportService;
import cn.lanqiao.service.SupplierService;
import cn.lanqiao.service.impl.ReportServiceImpl;
import cn.lanqiao.service.impl.SupplierServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    SupplierService supplierService = new SupplierServiceImpl();
    ReportService reportService = new ReportServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应的代码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String value = req.getParameter("action");
        //柱状图
        if (value.equals("show")){
            //用来存储供应商名字的
            ArrayList<String> suppliersNameList = new ArrayList<>();
            //用来存储账单数量的
            ArrayList<Integer> numberNameList = new ArrayList<>();
            List<Supplier> suppliers = supplierService.selectAll(null);
            for (Supplier supplier: suppliers){
                suppliersNameList.add(supplier.getName());
                //每个供应商下面的账单数量
                int countBySupplierId = reportService.getCountBySupplierId(String.valueOf(supplier.getId()));
                numberNameList.add(countBySupplierId);
            }
            String jsonString = JSON.toJSONString(suppliersNameList);
            String jsonString1 = JSON.toJSONString(numberNameList);
            //存值转发
            req.setAttribute("jsonString",jsonString);
            req.setAttribute("jsonString1",jsonString1);
            req.getRequestDispatcher("/report.jsp").forward(req,resp);
            //System.out.println(jsonString);
        }
        //饼状图
        if (value.equals("pie")){
            List<Pie> pieList = new ArrayList<>();
            List<Supplier> suppliers = supplierService.selectAll(null);
            for (Supplier supplier: suppliers){
                Pie pie = new Pie();
                pie.setName(supplier.getName());
                pie.setValue(reportService.getCountBySupplierId(String.valueOf(supplier.getId())));
                pieList.add(pie);
            }
            String jsonPie = JSON.toJSONString(pieList);
            //存值转发
            req.setAttribute("jsonPie",jsonPie);
            req.getRequestDispatcher("/pie.jsp").forward(req,resp);
        }
    }
}
