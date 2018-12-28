package com.yuan.demojpa2;

import com.yuan.demojpa2.system.dao.UserDao;
import com.yuan.demojpa2.system.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJpa2ApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void contextLoads() {
    }

//    @Test
//    @Transactional
//    public void test() {
////        entityManager.merge(new User(null, new Random().nextLong()));
//        entityManager.flush();
//        Query select_u_from_user_u = entityManager.createQuery("select new Map(u.id,u.name) from User u");
//        System.out.println(select_u_from_user_u.getResultList());
//
//        Query nativeQuery = entityManager.createNativeQuery("select id as iii, name as username from user");
//        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        System.out.println(nativeQuery.getResultList());
//    }
//
//
//    @Test
//    public void test2() {
//        User user = new User();
//        User.UserBuilder builder = User.builder();
//        builder.enabled(true);
//        User build = builder.build();
//        System.out.println(build);
//        user.setEnabled(true);
////        user.setId(new Random().nextLong());
//        user.setName("aaaaa");
////        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(user);
////        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
////        Arrays.stream(propertyDescriptors).map(propertyDescriptor -> {
////            Map map=new HashMap();
////            map.put(propertyDescriptor.getName(),propertyDescriptor.getValue(propertyDescriptor.getName()));
////            return  map;
////        }).forEach(System.out::println);
//        System.out.println(StringUtils.isEmpty(user.getName()));
//    }

    @Autowired
    private UserDao userDao;
    @Test
    public void test3(){
        userDao.save(new User());
        System.out.println(userDao.findAll());
    }

}

