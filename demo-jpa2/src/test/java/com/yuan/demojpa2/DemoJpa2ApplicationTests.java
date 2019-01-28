package com.yuan.demojpa2;

import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJpa2ApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void contextLoads() {
    }

    @Autowired
    private ApplicationContext context;


    @Test
    @Transactional
    public void test2() {
        EntityManager entityManager = context.getBean(EntityManager.class);
        Query user = entityManager.createQuery("select u.id from User u");
        user.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        System.out.println(user.getResultList());
    }


}