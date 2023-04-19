package cn.lanqiao.service;

import cn.lanqiao.pojo.User;

import java.util.List;

public interface AuthorityService {
    List<User> selectAll(String username);
}
