package cn.lanqiao.controller;

import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.SupplierService;
import cn.lanqiao.service.UserService;
import cn.lanqiao.service.impl.SupplierServiceImpl;
import cn.lanqiao.service.impl.UserServiceImpl;
import cn.lanqiao.utils.DateUtils;
import cn.lanqiao.utils.ExprotCellStyle;
import cn.lanqiao.utils.ImportExcelUtils;
import cn.lanqiao.utils.JsonResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@MultipartConfig //标注当前servlet支持文件上传
@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet {
    SupplierService supplierService = new SupplierServiceImpl();
    UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求和响应的代码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取前端的业务请求
        String value = req.getParameter("action");
        //导出数据成excel表格
        if (value.equals("exportException")){
            String name = req.getParameter("name");
            //System.out.println("要进行Excel导出操作...查询"+name);
            //获取数据库中的数据
            List<Supplier> suppliers = supplierService.selectAll(name);
            //1.创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet("供应商信息表");
            sheet.setDefaultColumnWidth(20);//设置所有列的列宽
            //3.首行合并
            CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 6);
            CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 6);
            sheet.addMergedRegion(region1);
            sheet.addMergedRegion(region2);

            //的sheet上创建行
            int rownum = 0;
            HSSFRow row01 = sheet.createRow(rownum);
            //在row01上创建单元格
            HSSFCell cell_row01 = row01.createCell(0);
            //向cell_row01写东西
            cell_row01.setCellValue("供应商数据");
            //设置标题样式
            HSSFCellStyle titleStyle = ExprotCellStyle.createTitleCellStyle(workbook);
            cell_row01.setCellStyle(titleStyle);

            //第二行
            rownum++;
            HSSFRow row02 = sheet.createRow(rownum);
            HSSFCell cell_row02 = row02.createCell(0);
            cell_row02.setCellValue("总数:" + suppliers.size() + "，导出时间:" + DateUtils.nowTime());
            //设置小标题样式
            HSSFCellStyle secondTitleStyle = ExprotCellStyle.createSecondTitleStyle(workbook);
            cell_row02.setCellStyle(secondTitleStyle);

            //第三行
            rownum++;
            HSSFRow row03 = sheet.createRow(rownum);
            String[] titles = {"供应商ID", "供应商名称", "供应商系人", "供应商电话", "供应商地址", "供应商传真", "供应商描述"};
            //得到表头的样式
            HSSFCellStyle tableTitleStyle = ExprotCellStyle.createTableTitleStyle(workbook);
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell = row03.createCell(i);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(tableTitleStyle);
            }

            //表格主题样式
            HSSFCellStyle tableBodyStyle = ExprotCellStyle.setRowCellCenter(workbook);
            for (int i = 0; i < suppliers.size(); i++) {
                rownum++;

                HSSFRow row = sheet.createRow(rownum);
                Supplier p = suppliers.get(i);

                //创建idcell
                HSSFCell idCell = row.createCell(0);
                idCell.setCellValue(p.getId());
                idCell.setCellStyle(tableBodyStyle);

                //创建namecell
                HSSFCell nameCell = row.createCell(1);
                nameCell.setCellValue(p.getName());
                nameCell.setCellStyle(tableBodyStyle);

                //创建linkmaneCell
                HSSFCell linkmaneCell = row.createCell(2);
                linkmaneCell.setCellValue(p.getLinkman());
                linkmaneCell.setCellStyle(tableBodyStyle);

                //创建phoneCell
                HSSFCell phoneCell = row.createCell(3);
                phoneCell.setCellValue(p.getPhone());
                phoneCell.setCellStyle(tableBodyStyle);

                //创建addressCell
                HSSFCell addressCell = row.createCell(4);
                addressCell.setCellValue(p.getAddress());
                addressCell.setCellStyle(tableBodyStyle);

                //创建faxCell
                HSSFCell faxCell = row.createCell(5);
                faxCell.setCellValue(p.getFax());
                faxCell.setCellStyle(tableBodyStyle);

                //创建descriptionCell
                HSSFCell descriptionCell = row.createCell(6);
                descriptionCell.setCellValue(p.getDescription());
                descriptionCell.setCellStyle(tableBodyStyle);
            }

            //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
            //下载中文文件时，需要注意的地方就是中文文件名要使用
            String fileName = "供应商数据信息表.xls";
            // URLEncoder.encode方法进行编码(URLEncoder.encode(fileName, "字符编码"))，否则会出现文件名乱码。
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            //输出字节流生成的Excel文件作为浏览器的一个输出流--进行输出
            ServletOutputStream outputStream = resp.getOutputStream();
            //把Workbook作为浏览响应字节流进行附件下载
            workbook.write(outputStream);
            System.out.println("数据导出完毕");
            //关闭流
            outputStream.close();

        }
        //下载excel模板
        if (value.equals("downloadExcelModel")){
            //1.获取要下载的文件的绝对路径
            //在resources目录放入QQ.png，注意项目导出后resource中的文件被打包到/WEB-INF/classes下,服务器的真实路径
            String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/用户数据文件模板.xls");
            //2.获取要下载的文件名
            String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
            System.out.println("要下载的文件名:" + fileName);
            //字节输入流
            FileInputStream inputStream = new FileInputStream(realPath);
            //浏览器输出流
            ServletOutputStream outputStream = resp.getOutputStream();

            //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
            // URLEncoder.encode方法进行编码(URLEncoder.encode(fileName, "字符编码"))，否则会出现文件名乱码。
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            //5.准备数据缓冲区
            int len = 0;
            byte[] buffer = new byte[1024]; // 1KB
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
        }
        //实现文件上传到数据库
        if (value.equals("excelImport")){
            extracted(req, resp);
        }
    }

    private void extracted(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //执行excel文件导入操作
        List<User> userList = new ArrayList<>();
        //取上传的文件数量
        Collection<Part> parts = req.getParts();
        PrintWriter writer = resp.getWriter();
        System.out.println("文件上传的个数:"+parts.size());
        try {
            if (parts.size()>0){
                Part part = req.getPart("file");
                //取文件名
                String submittedFileName = part.getSubmittedFileName();
                System.out.println("上传的文件名:"+submittedFileName);
                //上传的文件对应输入流
                InputStream inputStream = part.getInputStream();
                //读取excel中的数据
                Workbook workbook = ImportExcelUtils.getWorkbookByInputStream(inputStream, submittedFileName);
                //得到工作表
                Sheet sheet = ImportExcelUtils.getSheetByWorkbook(workbook,0);
                //根据需要是否加条数限制
                if (sheet.getRow(1000) != null){
                    throw new RuntimeException("系统已限制单批次导入必须小于或等于1000笔!");
                }
                int rowNum = 0;//已取值的行数
                int colNum = 0;//列号
                //获取数据行数
                int realRowCount = sheet.getPhysicalNumberOfRows();
                //处理表格数据
                for (Row row : sheet){
                    if (realRowCount == rowNum){
                        //excel文件所有的行读取完毕，结束循环
                        break;
                    }
                    if (ImportExcelUtils.isBlankRow(row)){//空行跳过
                        continue;
                    }
                    if (row.getRowNum() == -1){
                        continue;
                    }else {
                        if (row.getRowNum() == 0 || row.getRowNum() == 1){//第一行，第二行表头跳过
                            continue;
                        }
                    }
                    rowNum++;
                    colNum = 1;
                    User user = new User();
                    //验证Excel文件字段，这里根据表格内名称
                    ImportExcelUtils.validCellValue(sheet,row,colNum,"用户名");
                    user.setUsername(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                    //列号需要自加获取
                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"初始密码");
                    user.setPassword(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"性别");
                    user.setSex("男".equals(ImportExcelUtils.getCellValue(sheet,row,colNum-1))?1:0);

                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"生日");
                    user.setBirthday(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"电话");
                    user.setPhone(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"地址");
                    user.setAddress(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                    ImportExcelUtils.validCellValue(sheet,row,++colNum,"类别");
                    String cellValue = ImportExcelUtils.getCellValue(sheet, row, colNum - 1);
                    if ("管理员".equals(cellValue)){
                        user.setType(1);
                    }else if ("经理".equals(cellValue)){
                        user.setType(2);
                    }else {
                        user.setType(3);
                    }
                    //存储对象到list集合中
                    userList.add(user);
                }
                System.out.println("===================导入的数据是=================");
                for (User user: userList) {
                    System.out.println(user);
                    //对象添加到数据库
                    userService.add(user);
                }
                //到这里添加数据成功
                JsonResult jsonResult = new JsonResult<>("200","导入数据成功");
                String jsonString = JSONObject.toJSONString(jsonResult);
                writer.print(jsonString);
            }
        }catch (Exception e){
            e.printStackTrace();
            JsonResult jsonResult = new JsonResult<>("500","导入数据失败");
            String jsonString = JSONObject.toJSONString(jsonResult);
            writer.print(jsonString);
        }
    }
}
