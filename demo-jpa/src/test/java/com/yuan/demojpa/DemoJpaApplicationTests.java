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
public class DemoJpaApplicationTests {
    @Autowired
    private ApplicationContext context;


    @Test
    public void contextLoads() {

        EntityManager manager = context.getBean(EntityManager.class);
        System.out.println(manager.getProperties());
//
//        TypedQuery<SysUser> query = manager.createQuery("select u from SysUser u where date (u.createDate)=date(?)", SysUser.class);
//        query.setParameter(1, new Date());
//        System.out.println(query.getResultList());
    }

    @Test
    public void test() {
//        SysUserDao userDao = context.getBean(SysUserDao.class);
//        Query query = new CollectionQuery("select * from sys_user su where su.id = ?", Collections.singletonList("1"));
//        String sql=" select * from sys_user su where su.id = :id ";
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", "1");
//        Query query = new MapQuery("select * from sys_user su where su.id = :id", map);
//        Optional<SysUser> oneBySQLQuery = userDao.findOneBySQLQuery(query);
//        Optional<SysUser> one = userDao.findOneBySQL(sql, map);
//        System.out.println(one);
        EntityManager manager = context.getBean(EntityManager.class);
        Query nativeQuery = manager.createNativeQuery("select su.id from sys_user su where su.id=:id", SysUser.class);
        nativeQuery.setParameter("id", "1");
        System.out.println(nativeQuery.getSingleResult());
    }


}

