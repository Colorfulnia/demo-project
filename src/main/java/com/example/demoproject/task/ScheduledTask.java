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

    @Scheduled(cron = "0 * * * * *")
    public void cronTask1(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务1】每分钟执行 - 当前时间: "+time);
    }
    @Scheduled(cron = "0 */10 * * * *")
    public void cronTask2(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务2】每10秒执行 - 当前时间: "+time);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void cronTask3(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务3】每天9点执行 - 当前时间: "+time);
    }

    @Scheduled(cron = "0 0 10 * * Mon")
    public void cronTask4(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务4】每周一10点执行 - 当前时间: "+time);
    }

    @Scheduled(cron = "0 0 2 1 * ?")
    public void cronTask5(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务5】每月1号凌晨2点执行 - 当前时间: "+time);
    }

    @Scheduled(cron = "0 0 8 ? * MON-FRI")
    public void cronTask6(){
        String time = LocalDateTime.now().format(formatter);
        System.out.println("【Cron任务6】工作日早上8点执行 - 当前时间: "+time);
    }


}
