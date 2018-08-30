package com.efruit.ark.microsvr.user.dao.domain;

import java.util.List;

//通用的分页返回
public class QueryResult<T> {
    private List<T> list;
    private int count;


    public QueryResult() {

    }

    public QueryResult(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
