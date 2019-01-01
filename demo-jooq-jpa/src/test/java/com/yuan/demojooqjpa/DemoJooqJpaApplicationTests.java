package com.yuan.demojooqjpa;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJooqJpaApplicationTests {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DSLContext dslContext;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void test() {
        entityManager.persist(new User());
        entityManager.flush();
        Param<String[]> id = DSL.param("id", new String[]{"297e9acd67e5dd1a0167e5dd2aea0000", "8dad0e8c-88ab-4b0a-a9e1-9be38b9cc0ce"});
        ResultQuery<Record> records = DSL.resultQuery("select * from demo5.user where ID in (:id)", id);
//        ResultQuery<Record> records = DSL.resultQuery("select * from demo5.user where NAME=?","name");
//        records=records.bind("name","name");
//        System.out.println(records.fetchMaps());
//        SelectConditionStep<Record> user = DSL.selectFrom(DSL.table("user")).where(DSL.field("id").in("297e9acd67e5dd1a0167e5dd2aea0000","8dad0e8c-88ab-4b0a-a9e1-9be38b9cc0ce"));
        Query nativeQuery = entityManager.createNativeQuery(records.getSQL());
        int i = 0;
        for (Param<?> param : records.getParams().values()) {
            nativeQuery.setParameter(i + 1, convertToDatabaseType(param));
            i++;
        }

//        List<Object> bindValues = records.getBindValues();


//        for (int i = 0; i < bindValues.size(); i++) {
//            System.out.println(i);
//            nativeQuery.setParameter(i+1,bindValues.get(i));
//        }
//        for (int i = 0; i < records.getBindValues().size(); i++) {
//            nativeQuery.setParameter(i+1,records.getBindValues().get(0));
//        }
//        nativeQuery.setParameter(1,records.getBindValues().get(0));
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        System.out.println(nativeQuery.getResultList());
    }

    private <T> Object convertToDatabaseType(Param<T> param) {
        return param.getBinding().converter().to(param.getValue());
    }

}

