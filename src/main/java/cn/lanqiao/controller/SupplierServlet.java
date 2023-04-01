package cn.lanqiao.controller;

import cn.lanqiao.pojo.Bills;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.service.BillsService;
import cn.lanqiao.service.SupplierService;
import cn.lanqiao.service.impl.BillsServiceImpl;
import cn.lanqiao.service.impl.SupplierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 供应商控制层
 */
@WebServlet("/SupplierServlet")
public class SupplierServlet extends HttpServlet {
    SupplierService supplier = new SupplierServiceImpl();
    //账单业务逻辑层
    BillsService billsService = new BillsServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取请求
        String action = req.getParameter("action");
        //查询列表
        if (action.equals("list")) {
            //前端传递的查询信息
            String name = req.getParameter("name");
            //通过数据库查询到的信息
            List<Supplier> suppliersList = supplier.selectAll(name);
            //存储起来
            req.setAttribute("suppliersList", suppliersList);
            //搜索回显
            req.setAttribute("name", name);//存值搜索条件
            req.getRequestDispatcher("/supplier-list.jsp").forward(req, resp);
        }
        //新增
        if (action.equals("add")) {
            String name = req.getParameter("name");
            String linkman = req.getParameter("linkman");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String fax = req.getParameter("fax");
            String description = req.getParameter("description");
            //封装对象
            Supplier supplier1 = new Supplier(null, name, linkman, phone, address, fax, description, 0);
            int add = supplier.add(supplier1);
            PrintWriter writer = resp.getWriter();
            if (add > 0) {
                writer.print("<script>" +
                        "alert('用户新增成功');" +
                        "window.parent.location.href = '/SupplierServlet?action=list'" +
                        "</script>");
            } else {
                writer.print("<script>" +
                        "alert('用户新增失败');location.href = '/supplier-add.jsp'" +
                        "</script>");
            }
        }
        //显示个人信息
        if (action.equals("details")) {
            //获取用户要显示的详情id
            String id = req.getParameter("id");
            Supplier supplier1 = supplier.findById(id);
            req.setAttribute("supplier1", supplier1);
            req.getRequestDispatcher("/supplier-view.jsp").forward(req, resp);
        }
        //携带数据跳转更新页面
        if (action.equals("goUpdate")) {
            String id = req.getParameter("id");
            Supplier supplierById = supplier.findById(id);
            req.setAttribute("supplierById", supplierById);
            req.getRequestDispatcher("/supplier-edit.jsp").forward(req, resp);
        }
        //更新
        if (action.equals("update")) {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String linkman = req.getParameter("linkman");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String fax = req.getParameter("fax");
            String description = req.getParameter("description");
            Supplier supplier1 = new Supplier(Integer.valueOf(id), name, linkman, phone, address, fax, description);
            int i = supplier.updateUser(supplier1);
            PrintWriter writer = resp.getWriter();
            if (i > 0) {
                writer.print("<script>" +
                        "alert('用户修改成功');" +
                        "window.parent.location.href = '/SupplierServlet?action=list'" +
                        "</script>");
            } else {
                writer.print("<script>" +
                        "alert('用户修改失败');location.href = /SupplierServlet?action=goUpdate&id=" + id + ";</script>"
                );
            }
        }
        //删除
        if (action.equals("delete")) {
            String id = req.getParameter("id");
            System.out.println("要删除的id是:" + id);
            List<Bills> bills = billsService.listByProviderId(id);
            PrintWriter out = resp.getWriter();
            //这个要删除的供应商数据是否在使用中，如果是，那么不能删除，如果没有使用中可以删除
            if (bills != null && bills.size() > 0) {
                //表示这个供应商下面有账单数据，不能删除!
                out.print("<script>" +
                        "alert('供应商下有账单数据,请勿删除');" +
                        "window.location.href = '/SupplierServlet?action=list'" +
                        "</script>"
                );
            } else {
                //表示这个供应商可以被删除
                int i = supplier.deleteById(id);
                if (i > 0) {
                    out.print("<script>" +
                            "alert('删除成功');" +
                            "window.location.href = '/SupplierServlet?action=list'" +
                            "</script>"
                    );
                } else {
                    out.print("<script>" +
                            "alert('删除失败');" +
                            "window.location.href = '/SupplierServlet?action=list'" +
                            "</script>"
                    );
                }
            }
        }
    }
}
