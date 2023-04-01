package cn.lanqiao.dao;

public interface ReportDao {
    /**
     *根据供应商的id查询这个供应商的账单数量
     */
    int getCountBySupplierId(String providerId);
}
