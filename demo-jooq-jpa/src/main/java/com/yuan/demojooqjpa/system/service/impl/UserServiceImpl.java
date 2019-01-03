package com.yuan.demojooqjpa.system.service.impl;

import com.yuan.demojooqjpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojooqjpa.commons.utils.SQLUtils;
import com.yuan.demojooqjpa.system.dto.UserDto;
import com.yuan.demojooqjpa.system.pojo.User;
import com.yuan.demojooqjpa.system.repository.UserRepository;
import com.yuan.demojooqjpa.system.service.UserService;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getBaseRepository() {
        return userRepository;
    }

    @Override
    public Page<User> data(UserDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage() - 1, dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<User> data2(UserDto dto) {
        String jpql = "select t from User t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        jpql = SQLUtils.createSQL(jpql, conditions, "order by t.createDate");
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage() - 1, dto.getSize()), map);
    }

    @Override
    public Page<User> data3(UserDto dto) {
        String jpql = "select t.* from User t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        jpql = SQLUtils.createSQL(jpql, conditions, "order by t.createDate");
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage() - 1, dto.getSize()), map);
    }

    @Override
    public Page<User> data4(UserDto dto) {
        Query query = DSL.selectFrom("user").where(Collections.emptyList()).orderBy(DSL.field("createDate").desc());
        return getBaseRepository().findAllByQuery(query, PageRequest.of(dto.getPage() - 1, dto.getSize()));
    }

    @Override
    public List<User> list(UserDto dto) {
        return getBaseRepository().findAll(dto, Sort.by(Sort.Order.desc("createDate")));
    }

    @Override
    public List<User> list2(UserDto dto) {
        String jpql = "select t from User t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        jpql = SQLUtils.createSQL(jpql, conditions, "order by t.createDate");
        return getBaseRepository().findAllByJPQL(jpql, map);
    }

    @Override
    public List<User> list3(UserDto dto) {
        String jpql = "select t.* from User t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        jpql = SQLUtils.createSQL(jpql, conditions, "order by t.createDate");
        return getBaseRepository().findAllByJPQL(jpql, map);
    }

    @Override
    public List<User> list4(UserDto dto) {
        Query query = DSL.selectFrom("user").where(Collections.emptyList()).orderBy(DSL.field("createDate").desc());
        return getBaseRepository().findAllByQuery(query);
    }

}
