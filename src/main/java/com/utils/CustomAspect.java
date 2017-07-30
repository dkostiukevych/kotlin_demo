package com.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.function.Consumer;

/**
 * Created by sergey on 30.07.17.
 */
@SuppressWarnings("unused")
@Aspect
public class CustomAspect {

  private static AllureLifecycle ALLURE = Allure.getLifecycle();

  @Pointcut("execution(* com.pages.*.*(..))")
  public void anyMethod() {
    //pointcut body, should be empty
  }

  @Before("anyMethod()")
  public void stepStart(JoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    final StepResult result = new StepResult().withName("test");

    ALLURE.startStep("1", result);
  }

  @AfterThrowing(pointcut = "anyMethod()", throwing = "e")
  public void stepFailed(JoinPoint joinPoint, Throwable e) {
    ALLURE.updateStep("1", setStatus(Status.FAILED));
    ALLURE.stopStep("1");
  }

  private Consumer<StepResult> setStatus(final Status status) {
    return result -> result.withStatus(status);
  }

  @AfterReturning(pointcut = "anyMethod()", returning = "result")
  public void stepStop(JoinPoint joinPoint, Object result) {
    ALLURE.stopStep("1");
  }
}