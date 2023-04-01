import cn.lanqiao.dao.UserDao;
import cn.lanqiao.dao.impl.UserDaoImpl;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.UserService;
import cn.lanqiao.service.impl.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class demo1 {
    UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        User user = new User("dxl","123456");
        User user1 = new UserDaoImpl().login(user);
        System.out.println(user1);
        if (user1 != null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
    @Test
    public void test1(){
        System.out.println(Arrays.toString(divisibilityArray("998244353",3)));
    }
    public int[] divisibilityArray(String word, int m) {
        int[] arr =new int[word.length()];
        String c = String.valueOf(m);
        BigInteger b = new BigInteger(c);
        for (int i = 0; i < arr.length; i++) {
            BigInteger a=new BigInteger( word.substring(0, i+1));
            if (Integer.valueOf(a.mod(b).toString()) == 0){
                arr[i]=1;
            }
        }
        return arr;
    }

    @Test
    public void demo4(){
        int[] num = new int[200];
        int[] shuzu ={11,31,41,21,1,0,9,8,7,12,55,33,17,29};
        int count = 0;//索引
        int h = 0;
        //遍历数组
        for (int i = shuzu[h];h< shuzu.length;h++){
            //用来做判断，我这里假设数组里面所有的数字都是质数
            boolean b = true;
            for (int j =2; j<i;j++){
                //判断质数
                if (i%j==0){
                    b=false;//能进入这里说明该数字不是质数
                    break;//打断本次for循环
                }
                if (b==true){
                    //说明是质数，保存起来
                    num[count]=i;
                }
            }
        }
        //打印
        for (int k = 0;k<count;k++){
            System.out.println(num[k]);
        }
    }
    @Test
    public void demo5(){
        int[] num = new int[200];
        int[] shuzu ={11,31,41,21,1,0,9,8,7,12,55,33,17,29};
        int count=0;
        int h = 0;
        int j;
        for (int i = 0;i< shuzu.length;i++){
            //用来做判断，我这里假设数组里面所有的数字都是质数
            for (j =2;j<=shuzu[i];j++){//循环除数
                if (shuzu[i]%j==0){//判断是否为质数
                    break;
                }
            }
            if (j>i){
                System.out.println(shuzu[i]);
            }
        }
    }

    /**
     * 数组反转
     */
    @Test
    public void test6(){
        /*
        提供字符串数组：{"I", "Love", "You", "Daddy"}
        翻转后输出字符串：I evoL uoY yddaD
         */
        String[] arr = {"I", "Love", "You", "Daddy"};
    }
    @Test
    public void test7(){
        List<User> departs = userService.getDeparts("l", 0, 5);
        for (User user: departs) {
            System.out.println(user);
        }
    }
}
