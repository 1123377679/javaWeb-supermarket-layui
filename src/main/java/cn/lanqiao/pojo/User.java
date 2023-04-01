package cn.lanqiao.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private String birthday;
    private String phone;
    private String address;
    private Integer type;
    private Integer isdelete;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, Integer sex, String birthday, String phone, String address, Integer type, Integer isdelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.isdelete = isdelete;
    }
    public User( String username, String password, Integer sex, String birthday, String phone, String address, Integer type) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }
    //id,userName,userpassword,Integer.valueOf(sex),birthday,userphone,userAddress,Integer.valueOf(userlei)
    public User(Integer id, String username, Integer sex, String birthday, String phone, String address, Integer type) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", isdelete=" + isdelete +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}
