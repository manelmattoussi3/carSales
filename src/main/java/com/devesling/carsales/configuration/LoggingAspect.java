package com.devesling.carsales.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final ThreadLocal<StopWatch> stopWatchThreadLocal = ThreadLocal.withInitial(StopWatch::new);
    @Pointcut("execution(* com.devesling.carsales.controller.*.*(..))")
    private void forControllerMethods() {}
    @Before("forControllerMethods()")
    public void logMethodInput(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        System.out.println("Method invoked: " + methodName);
        System.out.println("Method arguments: " + Arrays.toString(args));

        stopWatchThreadLocal.get().start();
    }
    @AfterReturning(pointcut = "forControllerMethods()", returning = "result")
    public void logMethodOutput(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        stopWatchThreadLocal.get().stop();
        System.out.println("Method completed: " + methodName);
        System.out.println("Method returned: " + result);
        System.out.println("Execution time: " + stopWatchThreadLocal.get().getLastTaskTimeMillis() + "ms");
        stopWatchThreadLocal.remove();
    }
    @AfterThrowing(pointcut = "forControllerMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();
        stopWatchThreadLocal.get().stop();
        System.out.println("Exception in method: " + methodName);
        System.out.println("Exception details: " + exception.getMessage());
        System.out.println("Execution time: " + stopWatchThreadLocal.get().getLastTaskTimeMillis() + "ms");
        stopWatchThreadLocal.remove();
    }
}
