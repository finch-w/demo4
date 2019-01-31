package com.yuan.demomybatis2;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.system.dao.UserMapper;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMybatis2ApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        UserMapper userMapper = context.getBean(UserMapper.class);
        IPage<SysUser> aa = userMapper.findAllByCondition(new Page<>(1, 1), SysUserDto.builder().build());
        System.out.println(aa.getRecords());
    }


}

