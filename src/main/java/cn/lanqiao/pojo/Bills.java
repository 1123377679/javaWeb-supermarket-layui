package cn.lanqiao.pojo;

import lombok.Data;

/**
 * 账单实现类
 */
@Data
public class Bills {
    private Integer id;
    private String title;
    private String unit;
    private Integer num;
    private Integer money;
    private Integer providerid;//供应商外键
    private String providerName;//扩展字段，为了数据显示
    private Integer ispay;
    private Integer isdelete;


    public Bills() {
    }

    public Bills(Integer id, String title, String unit, Integer num, Integer money, Integer providerid, Integer ispay, Integer isdelete) {
        this.id = id;
        this.title = title;
        this.unit = unit;
        this.num = num;
        this.money = money;
        this.providerid = providerid;
        this.ispay = ispay;
        this.isdelete = isdelete;
    }
}
