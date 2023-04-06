package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.DataDao;
import cn.lanqiao.pojo.User;
import cn.lanqiao.utils.DButils;

import java.util.ArrayList;
import java.util.List;

public class DataDaoImpl implements DataDao {

    @Override
    public ArrayList<Integer> getTotalCount(User user) {
        return DButils.commonQueryCountAll("SELECT DATE_FORMAT(birthday, '%Y-%m') AS month, COUNT(*) AS count FROM tb_users GROUP BY month");
    }
}
