package com.yuan.demojpa2;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {EasyPoiAutoConfiguration.class})
//@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
public class DemoJpa2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpa2Application.class, args);
    }

}

