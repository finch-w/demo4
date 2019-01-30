package com.yuan.demojooqjpa.commons.dao;

import org.jooq.DSLContext;
import org.jooq.Query;
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
    DSLContext getDslContext();

    void insert(T t);

    void insert(Query query);

    void update(T t);

    void update(Query query);

    @SuppressWarnings("unchecked")
    void delete(ID... ids);

    void delete(Query query);

    Optional<T> findOneBySQL(String sql, Object... objects);

    Optional<T> findOneBySQL(String sql, Collection collection);

    Optional<T> findOneBySQL(String sql, Map<String, Object> map);

    Optional<T> findOneByJPQL(String jpql, Object... objects);

    Optional<T> findOneByJPQL(String jpql, Map<String, Object> map);

    Optional<T> findOneByQuery(Query query);

    List<T> findAllBySQL(String sql, Object... objects);

    List<T> findAllBySQL(String sql, Collection collection);

    List<T> findAllBySQL(String sql, Map<String, Object> map);

    List<T> findAllByJPQL(String jpql, Object... objects);

    List<T> findAllByJPQL(String jpql, Collection collection);

    List<T> findAllByJPQL(String jpql, Map<String, Object> map);

    List<T> findAllByQuery(Query query);

    Page<T> findAllBySQL(String sql, Pageable pageable, Object... objects);

    Page<T> findAllBySQL(String sql, Pageable pageable, Collection collection);

    Page<T> findAllBySQL(String sql, Pageable pageable, Map<String, Object> map);

    Page<T> findAllByJPQL(String jpql, Pageable pageable, Object... objects);

    Page<T> findAllByJPQL(String jpql, Pageable pageable, Collection collection);

    Page<T> findAllByJPQL(String jpql, Pageable pageable, Map<String, Object> map);

    Page<T> findAllByQuery(Query query, Pageable pageable);

    Map findOneBySQLInMap(String sql, Object... objects);

    Map findOneBySQLInMap(String sql, Collection collection);

    Map findOneBySQLInMap(String sql, Map<String, Object> map);

    Map findOneByJPQLInMap(String jpql, Object... objects);

    Map findOneByJPQLInMap(String jpql, Collection collection);

    Map findOneByJPQLInMap(String jpql, Map<String, Object> map);

    Map findByQueryInMap(Query query);

    List<Map> findAllBySQLInMap(String sql, Object... objects);

    List<Map> findAllBySQLInMap(String sql, Collection collection);

    List<Map> findAllBySQLInMap(String sql, Map<String, Object> map);

    List<Map> findAllByJPQLInMap(String jpql, Object... objects);

    List<Map> findAllByJPQLInMap(String jpql, Collection collection);

    List<Map> findAllByJPQLInMap(String jpql, Map<String, Object> map);

    List<Map> findAllByQueryInMap(Query query);

    Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Object... objects);

    Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Collection collection);

    Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Map<String, Object> map);

    Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Object... objects);

    Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Collection collection);

    Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map);

    Page<Map> findAllByQueryInMap(Query query, Pageable pageable);

    <R> Optional<R> findOneBySQL(String sql, Class<R> requiureType, Object... objects);

    <R> Optional<R> findOneBySQL(String sql, Class<R> requiureType, Collection collection);

    <R> Optional<R> findOneBySQL(String sql, Class<R> requiureType, Map<String, Object> map);

    <R> Optional<R> findOneByJPQL(String jpql, Class<R> requiureType, Object... objects);

    <R> Optional<R> findOneByJPQL(String jpql, Class<R> requiureType, Collection collection);

    <R> Optional<R> findOneByJPQL(String jpql, Class<R> requiureType, Map<String, Object> map);

    <R> Optional<R> findOneByQuery(Query query, Class<R> requiureType);

    <R> List<R> findAllBySQL(String sql, Class<R> requireType, Object... objects);

    <R> List<R> findAllBySQL(String sql, Class<R> requireType, Collection collection);

    <R> List<R> findAllBySQL(String sql, Class<R> requireType, Map<String, Object> map);

    <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Object... objects);

    <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Collection collection);

    <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Map<String, Object> map);

    <R> List<R> findAllByQuery(Query query, Class<R> requireType);

    <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Object... objects);

    <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Collection collection);

    <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Map<String, Object> map);

    <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Object... objects);

    <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Collection collection);

    <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Map<String, Object> map);

    <R> Page<R> findAllByQuery(Query query, Pageable pageable, Class<R> requireType);
}
