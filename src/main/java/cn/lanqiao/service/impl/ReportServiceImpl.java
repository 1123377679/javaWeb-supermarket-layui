package cn.lanqiao.service.impl;

import cn.lanqiao.dao.ReportDao;
import cn.lanqiao.dao.impl.ReportDaoImpl;
import cn.lanqiao.pojo.Bills;
import cn.lanqiao.service.ReportService;

public class ReportServiceImpl implements ReportService {
    ReportDao reportDao = new ReportDaoImpl();

    @Override
    public int getCountBySupplierId(String providerId) {
        return reportDao.getCountBySupplierId(providerId);
    }
}
