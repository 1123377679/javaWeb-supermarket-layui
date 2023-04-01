package cn.lanqiao.dao.impl;

import cn.lanqiao.dao.UserDao;
import cn.lanqiao.pojo.User;
import cn.lanqiao.utils.DButils;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(User users) {
        ArrayList<User> users1 = DButils.commonQuery(User.class, "select * from tb_users where username = ? and password = ? and isdelete = 0", users.getUsername(), users.getPassword());
        if (users1 != null && users1.size()>0){
            return  users1.get(0);
        }else{
            return null;
        }

    }

    @Override
    public List<User> selectAll(String username) {
        if (username != null && !"".equals(username)){
            return DButils.commonQuery(User.class, "select * from tb_users where isdelete=0 and username like ?", "%" + username + "%");
        }else {
            return DButils.commonQuery(User.class, "select * from tb_users where isdelete=0");
        }
    }

    @Override
    public int add(User user) {
        return DButils.commonInsert("insert into tb_users values(null,?,?,?,?,?,?,?,0)",
                user.getUsername(),user.getPassword(),user.getSex(),user.getBirthday()
                ,user.getPhone(),user.getAddress(),user.getType());
    }

    @Override
    public User findById(String id) {
        ArrayList<User> users = DButils.commonQuery(User.class, "select * from tb_users where id=?",id);
        if (users.size()>0){
            return users.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int updateUser(User user) {
        return DButils.commonUpdate("update tb_users set username=?,sex=?,birthday=?,phone=?,address=?,type=? where id=?",
                user.getUsername(),
                user.getSex(),
                user.getBirthday(),
                user.getPhone(),
                user.getAddress(),
                user.getType(),
                user.getId()
        );
    }

    @Override
    public int deleteById(String id) {
        return DButils.commonUpdate("update tb_users set isdelete = 1 where id = ?",id);
    }

    @Override
    public int updatePwd(Integer userId, String newPwd) {
        return DButils.commonUpdate("update tb_users set password = ? where id =?",newPwd,userId);
    }

    @Override
    public int getTotalCount() {
        return DButils.commonQueryCount("select count(*) from tb_users where isdelete = 0");
    }

    @Override
    public List<User> getDeparts(String name,Integer pageStart, Integer pageSize) {
        if (name != null && !"".equals(name)){
            return DButils.commonQuery(User.class,"SELECT * FROM tb_users where isdelete=0 and username like ? limit ?,? ","%" + name + "%",pageStart,pageSize);
        }else {
            return DButils.commonQuery(User.class,"SELECT * FROM tb_users where isdelete=0 limit ?,?",pageStart,pageSize);
        }

    }
}
