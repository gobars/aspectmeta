package com.github.gobars.aspectmeta;

import java.lang.annotation.*;

/**
 * 演示自定义注解.(使用@Meta注解).
 *
 * @author bingoohuang
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Meta(MyDemo2Arounder.class)
@Target({ElementType.METHOD})
public @interface MyDemo2 {
  String value() default "";
}
