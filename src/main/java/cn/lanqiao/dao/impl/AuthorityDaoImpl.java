package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.AuthorityDao;
import cn.lanqiao.pojo.User;
import cn.lanqiao.utils.DButils;

import java.util.List;

public class AuthorityDaoImpl implements AuthorityDao {
    @Override
    public List<User> selectAll(String username) {
        if (username != null && !"".equals(username)){
            return DButils.commonQuery(User.class, "select * from tb_users where isdelete=0 and username like ?", "%" + username + "%");
        }else {
            return DButils.commonQuery(User.class, "select * from tb_users where isdelete=0");
        }
    }
}
