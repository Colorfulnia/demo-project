package com.example.demoproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    @Pointcut("execution(* com.example.demoproject.service..*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        System.out.println("【性能监控】开始执行："+className+"."+methodName);
        Object result = null;
        try{
            result = joinPoint.proceed();
        }catch (Throwable throwable){
            System.out.println("【性能监控】方法执行异常："+throwable.getMessage());
            throw throwable;
        }finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("【性能监控】执行完成："+className+"."+methodName);
            System.out.println("【执行时间】"+duration+"ms");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
        return result;
    }
}
