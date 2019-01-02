package com.yuan.demojooqjpa;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.yuan.demojooqjpa.commons.dao.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class DemoJooqJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJooqJpaApplication.class, args);
    }

}

