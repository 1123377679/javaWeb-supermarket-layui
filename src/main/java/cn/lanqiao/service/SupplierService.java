package cn.lanqiao.service;

import cn.lanqiao.pojo.Supplier;

import java.util.List;

public interface SupplierService {
    /**
     * 供应商列表查询
     * @param username
     * @return
     */
    List<Supplier> selectAll(String username);

    /**
     * 新增
     */
    int add(Supplier supplier);

    /**
     * 根据id获取要更新的数据
     */
    Supplier findById(String id);

    /**
     * 根据用户ID更新数据
     */
    int updateUser(Supplier supplier);

    /**
     * 根据ID删除(逻辑删除)
     * @param id
     * @return
     */
    int deleteById(String id);
}
