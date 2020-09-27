package com.github.gobars.aspectmeta;

import java.lang.annotation.Annotation;

public interface Arounder {
  /**
   * 切面方法.
   *
   * @param invoker    方法
   * @param args       方法参数
   * @param annotation 切面注解
   * @param meta       切面元注解
   * @return 方法执行返回
   * @throws Exception 方法执行异常
   */
  Object around(Invoker invoker, Object[] args, Annotation annotation, Meta meta) throws Throwable;
}
