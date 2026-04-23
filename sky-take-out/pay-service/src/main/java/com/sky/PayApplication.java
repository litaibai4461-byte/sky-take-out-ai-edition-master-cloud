package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @EnableCaching // 开启缓存注解功能 - temporarily disabled
@EnableTransactionManagement //开启注解方式的事务管理
@EnableScheduling // 开启任务调度
@EnableFeignClients
@Slf4j
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
        log.info("server started");

    }
}
