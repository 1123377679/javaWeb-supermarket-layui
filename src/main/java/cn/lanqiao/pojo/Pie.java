package cn.lanqiao.pojo;

import lombok.Data;

@Data
public class Pie {
    private Integer value;
    private String name;

    public Pie() {
    }

    public Pie(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
