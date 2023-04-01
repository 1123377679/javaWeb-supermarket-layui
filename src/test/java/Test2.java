import cn.lanqiao.pojo.User;
import cn.lanqiao.service.DataService;
import cn.lanqiao.service.UserService;
import cn.lanqiao.service.impl.DataServiceImpl;
import cn.lanqiao.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;


public class Test2 {
    UserService userService = new UserServiceImpl();
    DataService dataService = new DataServiceImpl();
    @Test
    public void demo2(){
        int totalCount = userService.getTotalCount();
        System.out.println(totalCount);
    }
    @Test
    public void demo1(){
        int totalCount = dataService.getTotalCount();
        System.out.println(totalCount);
    }

    @Test
    public void demo3(){
        List<User> users = dataService.totalCount();
        System.out.println(users);
    }

    @Test
    public void demo4(){

    }
}
