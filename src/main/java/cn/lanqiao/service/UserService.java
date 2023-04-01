package cn.lanqiao.service;

import cn.lanqiao.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     */
    User login(User users);

    /**
     * 用户查询(列表)
     */
    List<User> selectAll(String username);

    /**
     * 新增
     */
    int add(User user);

    /**
     * 根据id获取要更新的数据
     */
    User findById(String id);

    /**
     * 根据用户ID更新数据
     */
    int updateUser(User user);

    /**
     * 根据id删除
     */
    int deleteById(String id);

    /**
     * 修改当前用户的密码
     * @param userId
     * @param newPwd
     * @return
     */
    int updatePwd(Integer userId,String newPwd);

    /**
     *  查询总数
     */
    int getTotalCount();

    /**
     * 查询每页数据
     */
    List<User> getDeparts(String name,Integer pageStart, Integer pageSize);

}
