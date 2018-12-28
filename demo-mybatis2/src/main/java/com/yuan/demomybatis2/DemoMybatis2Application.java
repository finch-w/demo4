package com.yuan.demomybatis2;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatis2Application.class, args);
    }

    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}

