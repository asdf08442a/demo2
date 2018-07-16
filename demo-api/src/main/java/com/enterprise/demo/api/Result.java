package com.enterprise.demo.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回类
 *
 * @author jinzhengang
 * @create 2018-01-25 11:24
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2583954615153498290L;
    private Integer code;
    private String msg;
    private T data;
}
