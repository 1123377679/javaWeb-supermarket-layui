package cn.lanqiao.service.impl;

import cn.lanqiao.dao.LogsDao;
import cn.lanqiao.dao.impl.LogsDaoImpl;
import cn.lanqiao.pojo.Logs;
import cn.lanqiao.service.LogsDaoService;

import java.util.List;

public class LogsDaoServiceImpl implements LogsDaoService {
    LogsDao logsDao = new LogsDaoImpl();
    @Override
    public int addLogs(Logs log) {
        return logsDao.addLogs(log);
    }

    @Override
    public List<Logs> queryMyLogs(String username) {
        return logsDao.queryMyLogs(username);
    }

    @Override
    public int deleteById(String id) {
        return logsDao.deleteById(id);
    }
}
