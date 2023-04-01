package cn.lanqiao.pojo;

import lombok.Data;

import java.util.List;

/**
 * 分页类
 */
@Data
public class PageData<T> {
    //页码
    private int pageNo;
    //当前页的记录数
    private int pageSize;
    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;
    //获取当前页的数据
    private List<T> list;
    //查询的数据
    private String name;

    /**
     *获取总页数
     */
    public int getTotalPage() {
        totalPage = totalCount / pageSize;
        if (totalCount%pageSize!=0) {
            totalPage = totalPage+1;
        }
        return totalPage;
    }

    public PageData() {
    }

    public PageData(int pageNo, int pageSize, int totalCount, int totalPage, List<T> list,String name) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.name = name;
    }
}
