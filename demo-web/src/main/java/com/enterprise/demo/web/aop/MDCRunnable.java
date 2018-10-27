package com.enterprise.demo.web.aop;

import java.util.Map;
import org.slf4j.MDC;

/**
 * MDC装饰器
 *
 * @author jinzhengang
 * @create 2018-03-06 18:40
 **/
public class MDCRunnable implements Runnable {

  private final Runnable runnable;

  private final Map<String, String> map;

  public MDCRunnable(Runnable runnable) {
    this.runnable = runnable;
    // 保存当前线程的MDC值
    this.map = MDC.getCopyOfContextMap();
  }

  @Override
  public void run() {
    // 传入已保存的MDC值
    for (Map.Entry<String, String> entry : map.entrySet()) {
      MDC.put(entry.getKey(), entry.getValue());
    }
    // 装饰器模式，执行run方法
    runnable.run();
    // 移除已保存的MDC值
    for (Map.Entry<String, String> entry : map.entrySet()) {
      MDC.remove(entry.getKey());
    }
  }

}
