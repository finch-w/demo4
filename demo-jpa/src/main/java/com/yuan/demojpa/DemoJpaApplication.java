package com.yuan.demojpa;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.yuan.demojpa.commons.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
//@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, considerNestedRepositories = true)
public class DemoJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

}

