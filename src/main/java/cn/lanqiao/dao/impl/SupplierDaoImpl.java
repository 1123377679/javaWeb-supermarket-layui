package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.SupplierDao;
import cn.lanqiao.pojo.Supplier;
import cn.lanqiao.pojo.User;
import cn.lanqiao.utils.DButils;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {


    @Override
    public List<Supplier> selectAll(String name) {
        if (name != null && !"".equals(name)){
            return DButils.commonQuery(Supplier.class, "select * from tb_supplier where isdelete=0 and name like ?", "%" + name + "%");
        }else {
            return DButils.commonQuery(Supplier.class, "select * from tb_supplier where isdelete=0");
        }
    }

    @Override
    public int add(Supplier supplier) {
        return DButils.commonInsert("insert into tb_supplier values(null,?,?,?,?,?,?,0)",
                supplier.getName(),supplier.getLinkman(),supplier.getPhone(),
                supplier.getAddress(),supplier.getFax(),supplier.getDescription()
        );
    }

    @Override
    public Supplier findById(String id) {
        ArrayList<Supplier> suppliers = DButils.commonQuery(Supplier.class, "select * from tb_supplier where id=?", id);
        if (suppliers.size()>0){
            return suppliers.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int updateUser(Supplier supplier) {
        return DButils.commonUpdate("update tb_supplier set name=?,linkman=?,phone=?,address=?,fax=?,description=? where id=?",
                supplier.getName(),
                supplier.getLinkman(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getFax(),
                supplier.getDescription(),
                supplier.getId()
        );
    }

    @Override
    public int deleteById(String id) {
        return DButils.commonUpdate("update tb_supplier set isdelete = 1 where id = ?",id);
    }
}
