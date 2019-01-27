package com.yuan.demojooqjpa;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJooqJpaApplicationTests {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DSLContext dslContext;

    @Test
    public void contextLoads() {
        ResultQuery<Record> records = dslContext.resultQuery("select * from user u where u.id in (?)", DSL.sql("select * from user u"));
        System.out.println(records.getSQL());
        System.out.println(records.getResult());
    }

    public void test() {
        int[][] ints = {{1, 2}, {2, 3}};

        Arrays.stream(ints).flatMap(ints1 -> Arrays.stream(ints)).forEach(System.out::println);
    }

}

