package com.example.demoproject.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void task1() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【定时任务1】每5秒执行一次 - 当前时间: " + time);
    }

    @Scheduled(fixedDelay = 10000)
    public void task2() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【定时任务2】每10秒执行一次 - 当前时间: " + time);
    }

    @Scheduled(initialDelay = 3000, fixedRate = 60000)
    public void task3() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【定时任务2】每10秒执行一次 - 当前时间:  " + time);
    }

}
