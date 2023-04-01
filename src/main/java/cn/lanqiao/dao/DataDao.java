package cn.lanqiao.dao;

import cn.lanqiao.pojo.User;

import java.util.List;

public interface DataDao {
    //查询总数
    int getTotalCount();
    //查询第二种方式
    List<User> totalCount();
}
