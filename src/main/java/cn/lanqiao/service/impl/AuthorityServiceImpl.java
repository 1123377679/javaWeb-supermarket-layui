package cn.lanqiao.service.impl;

import cn.lanqiao.dao.AuthorityDao;
import cn.lanqiao.dao.impl.AuthorityDaoImpl;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.AuthorityService;

import java.util.List;

public class AuthorityServiceImpl implements AuthorityService {
    AuthorityDao authorityDao = new AuthorityDaoImpl();
    @Override
    public List<User> selectAll(String username) {
        return authorityDao.selectAll(username);
    }
}
