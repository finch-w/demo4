package com.yuan.demojpa.commons.repository;

import com.yuan.demojpa.commons.utils.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRespository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    void insert(T t);

    void insertAndFlush(T t);

    void update(T t, BeanUtils.IgnoreMode mode);

    void updateAndFlush(T t, BeanUtils.IgnoreMode mode);

    void deleteAllByIds(ID[] ids);

    T getOne(Example<T> example);

    T getBySQL(String sql, Object... objects);

    T getBySQL(String sql, Map<String, Object> map);

    List<T> listBySQL(String sql, Object... objects);

    List<T> listBySQL(String sql, Map<String, Object> map);

    Page<T> pageBySQL(String sql, Pageable pageable, Object... objects);

    Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map);

    Map<String, Object> getBySQLInMap(String sql, Object... objects);

    Map<String, Object> getBySQLInMap(String sql, Map<String, Object> map);

    List<Map<String, Object>> listBySQLInMap(String sql, Object... objects);

    List<Map<String, Object>> listBySQLInMap(String sql, Map<String, Object> map);

    Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Object... objects);

    Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map);

}
