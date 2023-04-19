package cn.lanqiao.dao;

import cn.lanqiao.pojo.User;

import java.util.List;

public interface AuthorityDao {

    List<User> selectAll(String username);
}
