package com.yuan.demojpa;

import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.service.UserService;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Map;

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


    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserService userService;

    @Test
    public void testUser() {
//        userService.insert(User.builder().username("admin").name("admin").password("admin").salt(UUID.randomUUID().toString()).build());
//        System.out.println();
//        List<Map> list = userService.list(UserDto.builder().build());
//        System.out.println(list);
        Page<Map> data = userService.data(UserDto.builder().build());
        System.out.println(data.getContent());
//        List resultList = entityManager.createNativeQuery("select * from user").setHint(QueryHints.RESULT_TYPE, ResultType.Map).setFirstResult(1).setMaxResults(10).getResultList();
//        System.out.println(resultList);
    }

//    @Test
//    public void testUser2(){
//        UserDao userDao = context.getBean(UserDao.class);
//        Page<Map<String, Object>> maps = userDao.pageBySQLInMap("select * from user", PageRequest.of(0, 1));
//        System.out.println(maps.getContent());
//    }


}

