package com.yuan.demomybatis;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import com.yuan.demomybatis.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = EasyPoiAutoConfiguration.class)
@MapperScan(basePackageClasses = {UserMapper.class})
public class DemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class, args);
    }

}

