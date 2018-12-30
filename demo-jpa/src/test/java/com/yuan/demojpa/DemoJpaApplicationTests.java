package com.yuan.demojpa;

import com.yuan.demojpa.system.pojo.User;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
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
public class DemoJpaApplicationTests {


    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void contextLoads() {
        entityManager.persist(new User());
        entityManager.flush();
    }


    @Test
    public void test() {
//        userDao.save(new User());
//        TypedQuery<User> select_u_from_user_u = entityManager.createQuery("select u.id,u.name from User u ", User.class);
//        TypedQuery<Integer> query = entityManager.createQuery("select count(u) from User u", Integer.class);
//        System.out.println(query.getSingleResult());
//        System.out.println(select_u_from_user_u.getResultList());
        Query nativeQuery = entityManager.createNativeQuery("select ID,NAME from user");
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        System.out.println(nativeQuery.getResultList());
    }

    @Test
    public void test2(){
        Query query = entityManager.createQuery("select count(user) from User user");
        System.out.println(query.getSingleResult());
    }



}

