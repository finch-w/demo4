package com.yuan.demomybatis2;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.mapper.UserMapper;
import com.yuan.demomybatis2.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMybatis2ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(userMapper.insert(new User()));

        System.out.println(userMapper.selectList(null));
    }

}

