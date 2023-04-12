package cn.lanqiao.dao;

import cn.lanqiao.pojo.User;

import java.util.ArrayList;
import java.util.List;

public interface DataDao {
    //查询总数
    ArrayList<Integer> getTotalCount(User user);
}
