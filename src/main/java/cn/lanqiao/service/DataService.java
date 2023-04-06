package cn.lanqiao.service;

import cn.lanqiao.pojo.User;

import java.util.ArrayList;
import java.util.List;

public interface DataService {
    //查询总数
    ArrayList<Integer> getTotalCount(User user);
}
