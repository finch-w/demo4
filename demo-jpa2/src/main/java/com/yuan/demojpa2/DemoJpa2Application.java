package com.yuan.demojpa2;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.yuan.demojpa2.commons.dao.impl.BaseDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {EasyPoiAutoConfiguration.class})
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
public class DemoJpa2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpa2Application.class, args);
    }

}

