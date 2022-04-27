package com.benny.jane.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

public class Result implements Serializable {
    private static final long serialVersionUID = 2719931935414658118L;

    private Integer status;
    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeStamp = new Date();

    // 无参构造函数
    public Result() {
        super();
        this.status = null;
        this.message = null;
        this.data = null;
    }

    // 包含返回值
    public Result(Integer status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 没有返回值
    public Result(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static Result okResult(Object data) {
        return new Result(200, "success!", data);
    }

    public static Result errorResult(String message) {
        return new Result(10000, message);
    }


    public static void main(String[] args) {
        Result r = new Result(200, "success");
    }
}
