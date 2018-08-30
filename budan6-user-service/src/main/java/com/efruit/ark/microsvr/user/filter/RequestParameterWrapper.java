package com.efruit.ark.microsvr.user.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 用来重构请求参数
 * Created by yangyang on 2018/8/24.
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {

    private Map<String,String[]> params = null;

    //构造方法
    public RequestParameterWrapper(HttpServletRequest request){
        super(request);
        this.params = new HashMap<>(request.getParameterMap());
    }

    //添加参数的方法
    public void addParameters(Map<String,Object> extraParams){
        for(Map.Entry<String,Object> entry : extraParams.entrySet()){
            addParameters(entry.getKey(),entry.getValue());
        }
    }

    //这四个重写的方法是很重要的！！！只有重写了这四个方法，后续才能在这个request中获取到对应的参数
    @Override
    public String getParameter(String name){
        String[] values = params.get(name);
        if(values == null || values.length == 0){
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String,String[]> getParameterMap(){
        return params;
    }

    public String[] getParamaterValues(String name){
        return params.get(name);
    }

    //一般网上的方法都没有重写这个方法，如果不重写这个方法，
    //而且controller中的参数为javaBean时，bean中的属性将不会自动绑定，下面会详细讲解这个
    @Override
    public Enumeration<String> getParameterNames(){
        Vector<String> nameList = new Vector<String>();
        for(Map.Entry<String,String[]> entry: params.entrySet()){
            nameList.add(entry.getKey());
        }
        return nameList.elements();
    }

    public void addParameters(String name,Object value){
        if(null != value){
            if(value instanceof String[]){
                params.put(name,(String[]) value);
            }else if(value instanceof String){
                params.put(name,new String[]{(String) value});
            }else{
                params.put(name,new String[]{String.valueOf(value)});
            }
        }
    }
}




