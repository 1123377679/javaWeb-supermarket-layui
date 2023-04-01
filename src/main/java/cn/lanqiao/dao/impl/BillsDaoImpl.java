package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.BillsDao;
import cn.lanqiao.pojo.Bills;
import cn.lanqiao.utils.DButils;

import java.util.ArrayList;
import java.util.List;

public class BillsDaoImpl implements BillsDao {
    @Override
    public List<Bills> listByProviderId(String prividerId) {
        return DButils.commonQuery(Bills.class,"select * from tb_bills where isdelete = 0 and providerid = ?",prividerId);
    }

    /**
     * 账单搜索业务方法
     * @param title
     * @param providerId
     * @param ispay
     * @return
     */
    @Override
    public List<Bills> list(String title, String providerId, String ispay) {
        StringBuilder stringBuilder = new StringBuilder("select * from tb_bills where isdelete = 0");
        List<Object> parms = new ArrayList<Object>();
        if (title!=null&&!"".equals(title)){
            stringBuilder.append(" and title like ?");
            parms.add("%"+title+"%");
        }
        if (providerId!=null&&!"-1".equals(providerId)){
            stringBuilder.append(" and providerId=?");
            parms.add(providerId);
        }
        if (ispay!=null&&!"-1".equals(ispay)){
            stringBuilder.append(" and ispay=?");
            parms.add(ispay);
        }
        return DButils.commonQuery(Bills.class,stringBuilder.toString(),parms.toArray());
    }

    @Override
    public Bills findById(String id) {
        ArrayList<Bills> bills = DButils.commonQuery(Bills.class, "select * from tb_bills where id=?", id);
        if (bills.size()>0){
            return bills.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int deleteById(String id) {
        return DButils.commonUpdate("update tb_bills set isdelete = 1 where id = ?",id);
    }

    @Override
    public int addBills(Bills bills) {
        return DButils.commonInsert("insert into tb_bills values(null,?,?,?,?,?,?,0)",bills.getTitle(),
                bills.getUnit(),bills.getNum(),bills.getMoney(),bills.getProviderid(),bills.getIspay());
    }

    @Override
    public int updateById(Bills bills) {
        return DButils.commonUpdate("update tb_bills set title=?,unit=?,num=?,money=?,providerid=?,ispay=? where id=?",
                bills.getTitle(),
                bills.getUnit(),
                bills.getNum(),
                bills.getMoney(),
                bills.getProviderid(),
                bills.getIspay(),
                bills.getId());
    }
}
