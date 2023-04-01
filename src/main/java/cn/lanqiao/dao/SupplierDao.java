package cn.lanqiao.dao;

import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.pojo.User;

import java.util.List;

public interface SupplierDao {

    /**
     * 供应商列表查询
     * @param name
     * @return
     */
    List<Supplier> selectAll(String name);

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
