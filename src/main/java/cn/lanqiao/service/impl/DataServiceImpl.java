package cn.lanqiao.service.impl;

import cn.lanqiao.dao.DataDao;
import cn.lanqiao.dao.impl.DataDaoImpl;
import cn.lanqiao.pojo.User;
import cn.lanqiao.service.DataService;

import java.util.List;

public class DataServiceImpl implements DataService {
    DataDao dataDao = new DataDaoImpl();
    @Override
    public int getTotalCount() {
        return dataDao.getTotalCount();
    }

    @Override
    public List<User> totalCount() {
        return dataDao.totalCount();
    }
}
