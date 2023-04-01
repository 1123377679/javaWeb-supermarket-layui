package cn.lanqiao.service;

import cn.lanqiao.pojo.Bills;

public interface ReportService {
    /**
     *根据供应商的id查询这个供应商的账单数量
     */
    int getCountBySupplierId(String providerId);
}
