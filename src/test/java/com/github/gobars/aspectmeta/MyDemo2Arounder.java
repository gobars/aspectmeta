package com.github.gobars.aspectmeta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Slf4j
@Component
public class MyDemo2Arounder implements Arounder {
  @Override
  public Object around(Invoker invoker, Object[] args, Annotation annotation, Meta meta)
      throws Throwable {
    log.info("around before: {}", annotation);
    try {
      return invoker.invoke(args);
    } finally {
      log.info("around after: {}", annotation);
    }
  }
}
