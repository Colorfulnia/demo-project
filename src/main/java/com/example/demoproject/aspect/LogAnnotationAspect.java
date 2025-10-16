package com.example.demoproject.aspect;


import com.example.demoproject.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LogAnnotationAspect {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Around("@annotation(log)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        String time = LocalDateTime.now().format(formatter);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║【自定义日志】"+log.value());
        System.out.println("║【时间】"+time);
        System.out.println("║【类名】"+className);
        System.out.println("║方法】"+methodName);
        System.out.println("║【参数】"+ Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("║【结果】成功");
            System.out.println("║【耗时】"+duration+"ms");
            System.out.println("║【返回值】"+result);

        }catch (Throwable throwable){
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("║【结果】失败");
            System.out.println("║【耗时】"+duration+"ms");
            System.out.println("║【异常】"+throwable.getMessage());
            throw throwable;
        }finally {
            System.out.println("╚═══════════════════════════════════════════════════╝");
        }
        return result;

    }
}
