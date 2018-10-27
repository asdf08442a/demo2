package com.enterprise.demo.web.handle;

import com.enterprise.demo.api.Result;
import com.enterprise.demo.core.enums.ExceptionEnum;
import com.enterprise.demo.core.exception.BizException;
import com.enterprise.demo.core.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author jinzhengang
 * @create 2018-01-25 11:45
 **/
@Slf4j
@Component
@ControllerAdvice
public class ExceptionHandle {

  /**
   * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Result exceptionGet(Exception e) {
    log.error("【系统异常】{}", e);
    if (e instanceof BizException) {
      BizException bizException = (BizException) e;
      return ResultUtils.error(bizException.getCode(), bizException.getMessage());
    }

    return ResultUtils.error(ExceptionEnum.UNKNOW_ERROR);
  }
}
