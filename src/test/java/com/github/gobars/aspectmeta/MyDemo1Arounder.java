package com.github.gobars.aspectmeta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyDemo1Arounder implements Arounder<MyDemo1> {
  @Override
  public Object around(Invoker invoker, Object[] args, MyDemo1 annotation, Meta meta)
      throws Throwable {
    log.info("around before: {}", annotation);

    try {
      return invoker.invoke(args);
    } finally {
      log.info("around after: {}", annotation);
    }
  }
}
