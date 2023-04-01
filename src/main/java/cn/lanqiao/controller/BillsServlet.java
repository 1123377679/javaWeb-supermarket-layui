package cn.lanqiao.controller;

import cn.lanqiao.pojo.Bills;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.service.BillsService;
import cn.lanqiao.service.SupplierService;
import cn.lanqiao.service.impl.BillsServiceImpl;
import cn.lanqiao.service.impl.SupplierServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/BillsServlet")
public class BillsServlet extends HttpServlet {

    BillsService billsService = new BillsServiceImpl();
    SupplierService supplierService = new SupplierServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应的代码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取前端的业务请求
        String action = req.getParameter("action");

        //账单列表查询
        if (action.equals("list")){
            String title = req.getParameter("title");
            String providerid = req.getParameter("providerid");
            String ispay = req.getParameter("ispay");
            //查询到的账单数据
            List<Bills> billList = billsService.list(title, providerid, ispay);
            //查询供应商的下拉集合
            List<Supplier> suppliersList = supplierService.selectAll(null);
            //供应商的集合存值转发
            req.setAttribute("suppliersList",suppliersList);
            //回显的数据
            req.setAttribute("title",title);
            req.setAttribute("providerid",providerid);
            req.setAttribute("ispay",ispay);
            //存值转发
            req.setAttribute("billList",billList);
            req.getRequestDispatcher("bill-list.jsp").forward(req,resp);
        }
        //显示个人信息
        if (action.equals("details")){
            String id = req.getParameter("id");
            //账单对象
            Bills billsById = billsService.findById(id);
            //供应商对象
            Supplier suppliers = supplierService.findById(String.valueOf(billsById.getProviderid()));
            System.out.println(suppliers.getName());
            //给扩展字段赋值
            billsById.setProviderName(suppliers.getName());
            req.setAttribute("billsById",billsById);
            req.getRequestDispatcher("/bill-view.jsp").forward(req,resp);
        }
        //删除
        if (action.equals("delete")){
            String id = req.getParameter("id");
            int i = billsService.deleteById(id);
            PrintWriter writer = resp.getWriter();
            if (i>0){
                writer.print("<script>" +
                        "alert('删除成功');" +
                        "window.location.href = '/BillsServlet?action=list'" +
                        "</script>"
                );
            }else {
                writer.print("<script>" +
                        "alert('删除失败');" +
                        "window.location.href = '/BillsServlet?action=list'" +
                        "</script>"
                );
            }
        }
        //获取下拉框的数据
        if (action.equals("loadSupplier")){
            //前端发送请求过来
            //System.out.println("前端发送了下拉框请求");
            //查询供应商
            //这是一个Java对象
            List<Supplier> suppliersList = supplierService.selectAll(null);
            //通过fastJson转换字符串
            String jsonString = JSONObject.toJSONString(suppliersList);
            //System.out.println(jsonString);
            PrintWriter writer = resp.getWriter();
            //响应浏览器一段JSON数据
            writer.print(jsonString);
        }
        //添加
        if (action.equals("add")){
            //前端发送过来的新增数据
            String title = req.getParameter("title");
            String unit = req.getParameter("unit");
            String num = req.getParameter("num");
            String money = req.getParameter("money");
            String providerid = req.getParameter("providerid");
            String ispay = req.getParameter("ispay");
            //封装对象
            Bills bills = new Bills(null,title,unit,Integer.valueOf(num),Integer.valueOf(money),Integer.valueOf(providerid),Integer.valueOf(ispay),0);
            //调用新增方法
            int i = billsService.addBills(bills);
            PrintWriter writer = resp.getWriter();
            if (i > 0){
                writer.print("<script>" +
                        "alert('账单新增成功');" +
                        "window.parent.location.href = '/BillsServlet?action=list'" +
                        "</script>");
            }else {
                writer.print("<script>" +
                        "alert('账单新增失败');location.href = '/bill-add.jsp'" +
                        "</script>");
            }
        }
        //跳转更新页面
        if (action.equals("goupdate")){
            //获取前端发送过来的ID
            String id = req.getParameter("id");
            //根据ID查询
            Bills bills = billsService.findById(id);
            //存值转发
            req.setAttribute("bills",bills);
            req.getRequestDispatcher("/bill-edit.jsp").forward(req,resp);

        }
        //更新操作
        if (action.equals("update")){
            //前端发送过来的新增数据
            String id = req.getParameter("id");
            String title = req.getParameter("title");
            String unit = req.getParameter("unit");
            String num = req.getParameter("num");
            String money = req.getParameter("money");
            String providerid = req.getParameter("providerid");
            String ispay = req.getParameter("ispay");
            //封装对象
            Bills bills = new Bills(Integer.valueOf(id),title,unit,Integer.valueOf(num),Integer.valueOf(money),Integer.valueOf(providerid),Integer.valueOf(ispay),0);
            //调用更新方法
            int i = billsService.updateById(bills);
            PrintWriter writer = resp.getWriter();
            if (i > 0){
                writer.print("<script>" +
                        "alert('账单更新成功');" +
                        "window.parent.location.href = '/BillsServlet?action=list'" +
                        "</script>");
            }else {
                writer.print("<script>" +
                        "alert('账单更新失败');location.href = '/BillsServlet?action=goupdate&id="+id+"'</script>");
            }
        }
    }
}
