package com.efruit.ark.microsvr.user.dao.domain;

/**
 * Created by yangyang on 2018/8/18.
 */
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RequestParm implements Serializable {
    private static final long serialVersionUID = -410882117424748919L;
    private List<String> list = null;
    private Map<String, String> map = null;
    private Object obj1 = null ;
    private Object obj2 = null;

    public List<String> getList() {
        return list;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public Object getObj1() {
        return obj1;
    }
    public void setObj1(Object obj1) {
        this.obj1 = obj1;
    }
    public Object getObj2() {
        return obj2;
    }
    public void setObj2(Object obj2) {
        this.obj2 = obj2;
    }
}
