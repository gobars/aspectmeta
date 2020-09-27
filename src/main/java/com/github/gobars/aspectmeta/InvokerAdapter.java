package com.github.gobars.aspectmeta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvokerAdapter implements Invoker {
  private final Invoker invoker;

  @Override
  public Object invoke(Object[] args) throws Throwable {
    return invoker.invoke(args);
  }
}
