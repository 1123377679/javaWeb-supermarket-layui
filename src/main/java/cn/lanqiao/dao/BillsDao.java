package cn.lanqiao.dao;

import cn.lanqiao.pojo.Bills;

import java.util.List;

public interface BillsDao {
    /**
     * 根据供应商id查询账单信息集合
     * @param prividerId
     * @return
     */
    List<Bills> listByProviderId(String prividerId);

    /**
     * 查询
     */
    List<Bills> list(String title,String prividerId,String ispay);

    /**
     * 通过id查询信息
     * @param id
     * @return
     */
    Bills findById(String id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 添加数据
     * @param bills
     * @return
     */
    int addBills(Bills bills);

    /**
     * 更新操作
     * @param id
     * @return
     */
    int updateById(Bills bills);
}
