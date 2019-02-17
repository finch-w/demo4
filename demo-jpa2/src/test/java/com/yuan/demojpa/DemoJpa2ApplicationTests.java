package com.yuan.demojpa;

import com.yuan.demojpa.system.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJpa2ApplicationTests {
    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        EntityManager entityManager = context.getBean(EntityManager.class);
        Query nativeQuery = entityManager.createNativeQuery("select * from sys_user su where su.id = :id", SysUser.class);
        nativeQuery.setParameter("id", "1");
        System.out.println(nativeQuery.getSingleResult());
    }


}