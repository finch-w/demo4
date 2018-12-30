package com.yuan.demojooqjpa.commons.dao;


import com.yuan.demojooqjpa.commons.utils.BeanUtils;
import org.jooq.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseDao<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    void insert(T t);

    void insertAndFlush(T t);

    void update(T t, BeanUtils.IgnoreMode mode);

    void updateAndFlush(T t, BeanUtils.IgnoreMode mode);

    void deleteByIds(ID[] ids);

    T getOne(Example<T> example);

    T getBySQL(String sql, Object... objects);

    T getBySQL(String sql, Map<String, Object> map);

    T getByQuery(Query query);

    List<T> listBySQL(String sql, Object... objects);

    List<T> listBySQL(String sql, Map<String, Object> map);

    List<T> listByQuery(Query query);

    Page<T> pageBySQL(String sql, Pageable pageable, Object... objects);

    Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map);

    Page<T> pageByQuery(Query query, Pageable pageable);

    Map<String, Object> getBySQLInMap(String sql, Object... objects);

    Map<String, Object> getBySQLInMap(String sql, Map<String, Object> map);

    Map<String, Object> getByQueryInMap(Query query);

    List<Map<String, Object>> listBySQLInMap(String sql, Object... objects);

    List<Map<String, Object>> listBySQLInMap(String sql, Map<String, Object> map);

    List<Map<String, Object>> listBySQLInMap(Query query);

    Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Object... objects);

    Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map);

    Page<Map<String, Object>> pageByQueryInMap(Query query, Pageable pageable);
}
