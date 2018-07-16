package com.enterprise.demo.admin.util;

import com.enterprise.demo.admin.enums.ExceptionEnum;
import com.enterprise.demo.api.Result;

/**
 * 返回工具类
 *
 * @author jinzhengang
 * @create 2018-01-25 11:36
 **/
public class ResultUtils {

    /**
     * 返回成功，传入返回体具体出參
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 自定义错误信息
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     */
    public static Result error(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        return result;
    }
}
