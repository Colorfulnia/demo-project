package com.example.demoproject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(* com.example.demoproject.service..*.*(..))")
    public void serviceMethod() {
    }

    @Before("serviceMethod()")
    public void beforeMethod(JoinPoint joinPoint) {
        String time = LocalDateTime.now().format(formatter);
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("==================");
        System.out.println("【前置日志】" + time);
        System.out.println("【调用类】" + className);
        System.out.println("【调用方法】" + methodName);
        System.out.println("【方法参数】" + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceMethod()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("【返回值】" + result);
        System.out.println("【后置日志】方法 " + methodName + " 执行成功");
        System.out.println("==================");
    }

    @AfterThrowing(pointcut = "serviceMethod()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("【异常日志】方法 " + methodName + " 抛出异常: " + exception.getMessage());
        System.out.println("==================");
    }

    @After("serviceMethod()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("【最终通知】方法执行完毕");
    }
}
