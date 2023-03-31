package com.itheima.reggie;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
//打印日志
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class ReggieApplication {
    public static void main(String[] args) {


        ConfigurableApplicationContext context = SpringApplication.run(ReggieApplication.class, args);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("*****************************************************");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        log.info("*****************************************************");

        log.info("项目启动了");
    }
}
