package com.yuan.demomybatis;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
@EnableCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
@EnableGlobalMethodSecurity(proxyTargetClass = true, jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableSpringHttpSession
public class DemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class, args);
    }

}

