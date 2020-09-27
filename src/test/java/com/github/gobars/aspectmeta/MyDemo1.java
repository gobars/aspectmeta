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
@Meta(MyDemo1Arounder.class)
@Target({ElementType.METHOD})
public @interface MyDemo1 {}
