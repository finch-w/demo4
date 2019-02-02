package com.yuan.demojpa;

import com.yuan.demojpa.system.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJpaApplicationTests {
    @Autowired
    private ApplicationContext context;


    @Test
    public void contextLoads() {
        EntityManager manager = context.getBean(EntityManager.class);
        TypedQuery<SysUser> query = manager.createQuery("select u from SysUser u where date (u.createDate)=date(?)", SysUser.class);
        query.setParameter(1, new Date());
        System.out.println(query.getResultList());
    }


}

