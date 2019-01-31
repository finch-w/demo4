package com.yuan.demomybatis2;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
//@EnableCaching
@EnableAsync
//@EnableGlobalMethodSecurity(proxyTargetClass = true, jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
//@EnableSpringHttpSession
public class DemoMybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatis2Application.class, args);
    }

    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}

