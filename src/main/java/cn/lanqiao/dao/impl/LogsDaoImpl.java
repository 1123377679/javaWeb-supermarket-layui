package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.LogsDao;
import cn.lanqiao.pojo.Logs;
import cn.lanqiao.utils.DButils;
import com.mysql.cj.log.Log;

import java.util.List;

public class LogsDaoImpl implements LogsDao {

    @Override
    public int addLogs(Logs log) {
        return DButils.commonUpdate("Insert into tb_logs values (null,?,?,?,?,?)",
                log.getUsername(),
                log.getAddress(),
                log.getIp(),
                log.getLogintime(),
                0);
    }

    @Override
    public List<Logs> queryMyLogs(String username) {
        return DButils.commonQuery(Logs.class,"select * from tb_logs where isdelete = 0 and username = ? order by logintime desc",username);
    }

    @Override
    public int deleteById(String id) {
        return DButils.commonUpdate("update tb_logs set isdelete = 1 where id = ?",id);
    }
}
