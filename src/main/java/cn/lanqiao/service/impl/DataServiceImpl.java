package cn.lanqiao.service.impl;

import cn.lanqiao.dao.DataDao;
import cn.lanqiao.dao.impl.DataDaoImpl;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.DataService;

import java.util.ArrayList;
import java.util.List;

public class DataServiceImpl implements DataService {
    DataDao dataDao = new DataDaoImpl();

    @Override
    public ArrayList<Integer> getTotalCount(User user) {
        return dataDao.getTotalCount(user);
    }
}
