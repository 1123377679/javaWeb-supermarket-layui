import cn.lanqiao.dao.SupplierDao;
import cn.lanqiao.dao.impl.SupplierDaoImpl;
import cn.lanqiao.pojo.Bills;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.BillsService;
import cn.lanqiao.service.UserService;
import cn.lanqiao.service.impl.BillsServiceImpl;
import cn.lanqiao.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoTest {
    SupplierDao supplierDao = new SupplierDaoImpl();
    UserService userService = new UserServiceImpl();

    BillsService billsService = new BillsServiceImpl();


    @Test
    public void demo6(){
        Bills bills = new Bills(1,"李某人","箱",100,10000,1,1,0);
        int i = billsService.updateById(bills);
        System.out.println(i);
        if (i>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
    }
    @Test
    public void demo1(){

        Supplier supplier = new Supplier(null,"啦啦啦视频","张先生","18108391256","重庆","沙坪坝区","旺旺公司真牛逼",0);
        int add = supplierDao.add(supplier);
        if (add>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    @Test
    public void demo2(){
        List<Supplier> suppliers = supplierDao.selectAll("");
        System.out.println(suppliers);
    }
    @Test
    public void demo3(){
        int i = userService.updatePwd(1, "123");
        if (i>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    /**
     * 测试fastjson
     */
    @Test
    public void demo4(){
        //根据id查询一个Java对象
        User byId = userService.findById("1");
        System.out.println("Java对象是:"+byId);
        //通过fastjson转换成json字符串对象
        String s = JSONObject.toJSONString(byId);
        System.out.println("转换的JSON对象是:"+s);
    }

    @Test
    public void demo5(){
        //根据id查询一个Java对象
        User byId = userService.findById("1");
        System.out.println("Java对象是:"+byId);
        //通过fastjson转换成json字符串对象
        String s = JSONObject.toJSONString(byId);
        System.out.println("转换的JSON对象是:"+s);
        String b = "{\"address\":\"重庆市南岸区\",\"birthday\":\"2023-03-04\",\"id\":1,\"isdelete\":0,\"password\":\"123456\",\"phone\":\"18108391256\",\"sex\":1,\"type\":1,\"username\":\"lmx\"}";
        //通过fastjson将JSON字符串对象转换成Java对象
        User user = JSONObject.parseObject(b, User.class);
        System.out.println("转换成的Java对象是:"+user);
    }

    @Test
    public void test9(){
            System.out.println("********************************************************");
            System.out.println("**************        学生信息管理系统       **************");
            System.out.println("**************        1.添加学生信息        **************");
            System.out.println("**************        2.删除学生信息        **************");
            System.out.println("**************        3.修改学生信息        **************");
            System.out.println("**************        4.查找学生信息        **************");
            System.out.println("**************        5.退出               **************");
            System.out.println("********************************************************");
            System.out.print("请选择其功能:");
    }
}
