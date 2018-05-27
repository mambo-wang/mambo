package com.wb.wbao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dell on 2017/7/2.
 */
@SpringBootApplication
@EnableAsync
public class Application {

    /** 注册Bean 获取Rest请求 */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }
}
