package com.github.gobars.aspectmeta;

import java.lang.annotation.*;

/**
 * 元注解，用来注解其它注解.
 *
 * @author bingoobjca
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Meta {
  /**
   * 拦截处理器类型.
   *
   * @return Class<? extends Aspector>
   */
  Class<? extends Arounder> value();
}
