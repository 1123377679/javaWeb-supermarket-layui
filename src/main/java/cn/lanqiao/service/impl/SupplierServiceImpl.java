package cn.lanqiao.service.impl;

import cn.lanqiao.dao.SupplierDao;
import cn.lanqiao.dao.impl.SupplierDaoImpl;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao = new SupplierDaoImpl();

    @Override
    public List<Supplier> selectAll(String username) {
        return supplierDao.selectAll(username);
    }

    @Override
    public int add(Supplier supplier) {
        return supplierDao.add(supplier);
    }

    @Override
    public Supplier findById(String id) {
        return supplierDao.findById(id);
    }

    @Override
    public int updateUser(Supplier supplier) {
        return supplierDao.updateUser(supplier);
    }

    @Override
    public int deleteById(String id) {
        return supplierDao.deleteById(id);
    }
}
