package com.chinaasia.utils;

public class ChinaAsiaResult {
    private String code; //0 正常 1异常
    private Object data; //数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
