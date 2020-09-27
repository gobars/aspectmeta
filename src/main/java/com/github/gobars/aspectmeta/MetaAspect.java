package com.github.gobars.aspectmeta;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
@Slf4j
public class MetaAspect {
  @Autowired ApplicationContext appContext;

  @Around("execution(@(@com.github.gobars.aspectmeta.Meta *) * *(..))")
  public Object meta(ProceedingJoinPoint jp) throws Throwable {
    Method method = ((MethodSignature) jp.getSignature()).getMethod();
    log.info("meta around: {}", method);

    InvokerAdapter adapter = new InvokerAdapter(jp::proceed);
    for (val meta : listMetas(method.getAnnotations(), Meta.class)) {
      adapter = new InvokerAdapter(new MetaInvoker(meta, adapter));
    }

    return adapter.invoke(jp.getArgs());
  }

  @AllArgsConstructor
  private class MetaInvoker implements Invoker {
    MetaAnnotation<Meta> meta;
    InvokerAdapter adapter;

    @Override
    public Object invoke(Object[] args) throws Throwable {
      Arounder bean = appContext.getBean(meta.meta.value());
      return bean.around(adapter, args, meta.annotation, meta.meta);
    }
  }

  @Value
  @AllArgsConstructor
  public static class MetaAnnotation<T extends Annotation> {
    T meta;
    Annotation annotation;
  }

  public static <T extends Annotation> List<MetaAnnotation<T>> listMetas(
      Annotation[] annotations, Class<T> annotationType) {
    ArrayList<MetaAnnotation<T>> l = new ArrayList<>();
    for (val annotation : annotations) {
      T meta = annotation.annotationType().getAnnotation(annotationType);
      if (meta != null) {
        l.add(0, new MetaAnnotation(meta, annotation));
      }
    }

    return l;
  }
}
