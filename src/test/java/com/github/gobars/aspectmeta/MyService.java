package com.github.gobars.aspectmeta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService {
  @MyDemo1("演示1")
  @MyDemo2("演示1")
  public void doSomething() {
    log.info("something");
  }
}
