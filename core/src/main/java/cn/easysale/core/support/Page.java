package cn.easysale.core.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaozhisong on 3/22/14.
 */
public class Page<T> {

    private int pageSize = 20;

    private int pageNo = 1;

    private long total;

    private List<T> data = new ArrayList<T>();

    public Page() {
    }

    public Page(int pageSize, int pageNo, long total, List<T> data) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.total = total;
        this.data = data;
    }


    public long getTotalPageCount() {
        return total % pageSize == 0 ? total / pageSize : total
                / pageSize + 1;
    }

    public boolean hasNextPage() {
        return pageNo < getTotalPageCount();
    }


    public boolean hasPreviousPage() {
        return pageNo > 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
