package example.springmovie.util.pageHelperUtil;

import java.util.List;

public class PageBean<T> {
    private List<T> list;
    private long total;

    public PageBean(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    // 省略getter和setter方法
}