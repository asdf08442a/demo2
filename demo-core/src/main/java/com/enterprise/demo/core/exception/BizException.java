package com.enterprise.demo.core.exception;

import com.enterprise.demo.core.enums.ExceptionEnum;

/**
 * 自定义业务异常
 *
 * @author jinzhengang
 * @create 2018-01-25 11:40
 **/
public class BizException extends RuntimeException {

    private Integer code;

    /**
     * 继承exception，加入错误状态值
     */
    public BizException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    /**
     * 自定义错误信息
     */
    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
