package cn.lanqiao.service;

import cn.lanqiao.pojo.Logs;

import java.util.List;

public interface LogsDaoService {
    /**
     * 新增登录日志
     * @param log
     * @return
     */
    int addLogs(Logs log);

    /**
     * 查询所有日志信息
     */
    List<Logs> queryMyLogs(String username);

    /**
     * 根据id删除日志信息
     * @param id
     * @return
     */
    int deleteById(String id);
}
