package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.DataDao;
import cn.lanqiao.pojo.User;
import cn.lanqiao.utils.DButils;

import java.util.List;

public class DataDaoImpl implements DataDao {
    @Override
    public int getTotalCount() {
        return DButils.commonQueryCount("select COUNT(id) FROM tb_users GROUP BY date_format(birthday,'%Y-%m')");
    }

    @Override
    public List<User> totalCount() {
        return DButils.commonQuery(User.class,"select COUNT(id) FROM tb_users GROUP BY date_format(birthday,'%Y-%m')");
    }
}
