package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.ReportDao;
import cn.lanqiao.utils.DButils;

public class ReportDaoImpl implements ReportDao {
    @Override
    public int getCountBySupplierId(String providerId) {
        return DButils.commonQueryCount("select count(*) from tb_bills where isdelete = 0 and providerid=?",providerId);
    }
}
