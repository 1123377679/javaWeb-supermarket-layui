package cn.lanqiao.service.impl;

import cn.lanqiao.dao.BillsDao;
import cn.lanqiao.dao.SupplierDao;
import cn.lanqiao.dao.impl.BillsDaoImpl;
import cn.lanqiao.dao.impl.SupplierDaoImpl;
import cn.lanqiao.pojo.Bills;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.service.BillsService;

import java.util.List;

public class BillsServiceImpl implements BillsService {
    BillsDao billsDao = new BillsDaoImpl();
    SupplierDao supplierDao = new SupplierDaoImpl();
    @Override
    public List<Bills> listByProviderId(String prividerId) {
        return billsDao.listByProviderId(prividerId);
    }

    @Override
    public List<Bills> list(String title, String prividerId, String ispay) {
        List<Bills> list = billsDao.list(title, prividerId, ispay);
        for (Bills b: list) {
            Integer providerid = b.getProviderid();
            Supplier byId = supplierDao.findById(String.valueOf(providerid));
            b.setProviderName(byId.getName());
        }
        return list;
    }

    @Override
    public Bills findById(String id) {
        return billsDao.findById(id);
    }

    @Override
    public int deleteById(String id) {
        return billsDao.deleteById(id);
    }

    @Override
    public int addBills(Bills bills) {
        return billsDao.addBills(bills);
    }

    @Override
    public int updateById(Bills bills) {
        return billsDao.updateById(bills);
    }
}
