package cn.lanqiao.pojo;

import lombok.Data;

@Data
public class Logs {
    private Integer id;
    private String username;
    private String address;
    private String ip;
    private String logintime;
    private Integer isdelete;

    public Logs() {
    }

    public Logs(Integer id, String username, String address, String ip, String logintime, Integer isdelete) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.ip = ip;
        this.logintime = logintime;
        this.isdelete = isdelete;
    }
}
