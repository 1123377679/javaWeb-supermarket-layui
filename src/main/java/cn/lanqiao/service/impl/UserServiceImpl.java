package cn.lanqiao.service.impl;

import cn.lanqiao.dao.UserDao;
import cn.lanqiao.dao.impl.UserDaoImpl;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.UserService;
import cn.lanqiao.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User users) {
        return userDao.login(users);
    }

    @Override
    public List<User> selectAll(String username) {
        return userDao.selectAll(username);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteById(String id) {
        return userDao.deleteById(id);
    }

    @Override
    public int updatePwd(Integer userId, String newPwd) {
        return userDao.updatePwd(userId,newPwd);
    }

    @Override
    public int getTotalCount() {
        return userDao.getTotalCount();
    }

    @Override
    public List<User> getDeparts(String name, Integer pageStart, Integer pageSize) {
        return userDao.getDeparts(name,pageStart,pageSize);
    }


}
