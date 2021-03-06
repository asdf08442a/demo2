package com.enterprise.demo.sys.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author jinzg
 * @date 2018/10/22
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

  private static ApplicationContext appContext = null;

  /**
   * 通过name获取 Bean.
   */
  public static Object getBean(String name) {
    return appContext.getBean(name);

  }

  /**
   * 通过class获取Bean.
   */
  public static <T> T getBean(Class<T> clazz) {
    return appContext.getBean(clazz);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return appContext.getBean(name, clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (appContext == null) {
      appContext = applicationContext;
    }
  }
}
