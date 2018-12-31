package com.yuan.demojpa.commons.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    void insert(T t);

    void update(T t);

    void deleteAllById(ID... ids);

    Optional<T> getOne(Example<T> example);

    T getBySQL(String sql, Object... objects);

    T getBySQL(String sql, Collection collection);

    T getBySQL(String sql, Map<String, Object> map);

    T getByJPQL(String jpql, Object... objects);

    T getByJPQL(String jpql, Collection collection);

    T getByJPQL(String jpql, Map<String, Object> map);

    List<T> listBySQL(String sql, Object... objects);

    List<T> listBySQL(String sql, Collection collection);

    List<T> listBySQL(String sql, Map<String, Object> map);

    List<T> listByJPQL(String jpql, Object... objects);

    List<T> listByJPQL(String jpql, Collection collection);

    List<T> listByJPQL(String jpql, Map<String, Object> map);

    Page<T> pageBySQL(String sql, Pageable pageable, Object... objects);

    Page<T> pageBySQL(String sql, Pageable pageable, Collection collection);

    Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map);

    Page<T> pageByJPQL(String jpql, Pageable pageable, Object... objects);

    Page<T> pageByJPQL(String jpql, Pageable pageable, Collection collection);

    Page<T> pageByJPQL(String jpql, Pageable pageable, Map<String, Object> map);

    Map getBySQLInMap(String sql, Object... objects);

    Map getBySQLInMap(String sql, Collection collection);

    Map getBySQLInMap(String sql, Map<String, Object> map);

    Map getByJPQLInMap(String jpql, Object... objects);

    Map getByJPQLInMap(String jpql, Collection collection);

    Map getByJPQLInMap(String jpql, Map<String, Object> map);

    List<Map> listBySQLInMap(String sql, Object... objects);

    List<Map> listBySQLInMap(String sql, Collection collection);

    List<Map> listBySQLInMap(String sql, Map<String, Object> map);

    Page<Map> pageBySQLInMap(String sql, Pageable pageable, Object... objects);

    Page<Map> pageBySQLInMap(String sql, Pageable pageable, Collection collection);

    Page<Map> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map);

    Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Object... objects);

    Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Collection collection);

    Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map);

}
