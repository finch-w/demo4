package com.yuan.demotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTestApplicationTests {

    @Test
    public void contextLoads() {
        Objects.nonNull("aaa");
    }

}

