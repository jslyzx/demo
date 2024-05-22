package com.yph.authcommon;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    private long current = 1;// 当前页
    private long size = 10;// 每页显示记录数
    private long total; // 总数
    private long pages; // 总页数
    private Object data; // 数据结果
    private int code;//响应状态
    private String msg;//响应内容

    public ApiResult(){

    }

    public ApiResult(IPage<T> page) {
        this.setCode(ApiResult.SUCCESS);
        this.setCurrent(page.getCurrent());
        this.setSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setPages(page.getPages());
        this.setData(page.getRecords());
    }

    public ApiResult(int code, String msg){
        this.setCode(code);
        this.setMsg(msg);
    }

    public ApiResult(int code, String msg, Object data){
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
