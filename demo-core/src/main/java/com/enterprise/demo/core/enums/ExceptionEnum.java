package com.enterprise.demo.core.enums;

/**
 * 异常枚举
 *
 * @author jinzhengang
 * @create 2018-01-25 11:34
 **/
public enum ExceptionEnum {

  UNKNOW_ERROR(-1, "未知错误");

  private Integer code;
  private String msg;

  ExceptionEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
