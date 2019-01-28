package com.yuan.demojpa2;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.yuan.demojpa2.commons.dao.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;


@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
@EnableCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
@EnableGlobalMethodSecurity(proxyTargetClass = true, jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableSpringHttpSession
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class DemoJpa2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpa2Application.class, args);
    }

}

