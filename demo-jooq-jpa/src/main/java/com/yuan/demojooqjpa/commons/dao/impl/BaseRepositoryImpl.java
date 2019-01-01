package com.yuan.demojooqjpa.commons.dao.impl;

import com.yuan.demojooqjpa.commons.dao.BaseRepository;
import com.yuan.demojooqjpa.commons.utils.BeanUtils;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.jooq.Query;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
@NoRepositoryBean
@Transactional
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    private String getCountSql(String sql) {
        String lowerCaseSql = sql.toLowerCase();
        int fromIndex = lowerCaseSql.indexOf("from");
        return String.format("select count(1) %s", sql.substring(fromIndex));
    }

    @Override
    public void insert(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }

    @Override
    public void update(T t) {
        T tDb = entityManager.find(entityInformation.getJavaType(), entityInformation.getId(t));
        BeanUtils.copyPojo(t, tDb);
        entityManager.refresh(tDb);
        entityManager.flush();
    }

    @Override
    public void deleteAllById(ID... ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        entityManager.flush();
    }

    @Override
    public Optional<T> getOneBySQL(String sql, Object... objects) {
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneBySQL(String sql, Collection collection) {
        return getOneBySQL(sql, collection.toArray());
    }

    @Override
    public Optional<T> getOneBySQL(String sql, Map<String, Object> map) {
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(query::setParameter);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneByQuery(Query query) {
        return getOneBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    public List<T> findAllBySQL(String sql, Object... objects) {
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return nativeQuery.getResultList();
    }

    @Override
    public List<T> findAllBySQL(String sql, Collection collection) {
        return findAllBySQL(sql, collection.toArray());
    }

    @Override
    public List<T> findAllBySQL(String sql, Map<String, Object> map) {
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return nativeQuery.getResultList();
    }

    @Override
    public List<T> findAllByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<T> findAllByJPQL(String jpql, Collection collection) {
        return findAllByJPQL(jpql, collection.toArray());
    }

    @Override
    public List<T> findAllByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<T> findAllByQuery(Query query) {
        return findAllBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Object... objects) {
        String countSql = getCountSql(sql);
        javax.persistence.Query countQuery = entityManager.createNativeQuery(countSql);
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> {
            countQuery.setParameter(i + 1, objects[i]);
            nativeQuery.setParameter(i + 1, objects[i]);
        });
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Collection collection) {
        return findAllBySQL(sql, pageable, collection.toArray());
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        String countSql = getCountSql(sql);
        javax.persistence.Query countQuery = entityManager.createNativeQuery(countSql);
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(countQuery::setParameter);
        map.forEach(nativeQuery::setParameter);
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Object... objects) {
        String countJpql = getCountSql(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        TypedQuery<T> selectQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> {
            countQuery.setParameter(i + 1, objects[i]);
            selectQuery.setParameter(i + 1, objects[i]);
        });
        Long count = countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        selectQuery.setMaxResults(pageable.getPageSize());
        List<T> resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQL(jpql, pageable, collection);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Map<String, Object> map) {
        String countJpql = getCountSql(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        TypedQuery<T> selectQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(countQuery::setParameter);
        map.forEach(selectQuery::setParameter);
        Long count = countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        selectQuery.setMaxResults(pageable.getPageSize());
        List<T> resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllByQuery(Query query, Pageable pageable) {
        return findAllBySQL(query.getSQL(), pageable, query.getBindValues());
    }

    @Override
    public Map getOneBySQLInMap(String sql, Object... objects) {
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        try {
            return (Map) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Map getOneBySQLInMap(String sql, Collection collection) {
        return getOneBySQLInMap(sql, collection.toArray());
    }

    @Override
    public Map getOneBySQLInMap(String sql, Map<String, Object> map) {
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(query::setParameter);
        try {
            return (Map) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        try {
            return (Map) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Collection collection) {
        return getOneByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        try {
            return (Map) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Map getByQueryInMap(Query query) {
        return getOneBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Object... objects) {
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Collection collection) {
        return findAllBySQLInMap(sql, collection.toArray());
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Map<String, Object> map) {
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Collection collection) {
        return findAllByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<Map> findAllByQueryInMap(Query query) {
        return findAllBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Object... objects) {
        String countSql = getCountSql(sql);
        javax.persistence.Query countQuery = entityManager.createNativeQuery(sql);
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        for (int i = 0; i < objects.length; i++) {
            countQuery.setParameter(i + 1, objects[i]);
            query.setParameter(i + 1, objects[i]);
        }
        Long count = (Long) countQuery.getSingleResult();
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Collection collection) {
        return findAllBySQLInMap(sql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        String countSql = getCountSql(sql);
        javax.persistence.Query countQuery = entityManager.createNativeQuery(sql);
        javax.persistence.Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(countQuery::setParameter);
        map.forEach(query::setParameter);
        Long count = (Long) countQuery.getSingleResult();
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Object... objects) {
        String countSql = getCountSql(jpql);
        TypedQuery<Long> conntQuery = entityManager.createQuery(countSql, Long.class);
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        for (int i = 0; i < objects.length; i++) {
            conntQuery.setParameter(i + 1, objects[i]);
            query.setParameter(i + 1, objects[i]);
        }
        Long count = conntQuery.getSingleResult();
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<Map> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQLInMap(jpql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map) {
        String countSql = getCountSql(jpql);
        TypedQuery<Long> conntQuery = entityManager.createQuery(countSql, Long.class);
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(conntQuery::setParameter);
        map.forEach(query::setParameter);
        Long count = conntQuery.getSingleResult();
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<Map> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllByQueryInMap(Query query, Pageable pageable) {
        return findAllByJPQLInMap(query.getSQL(), pageable, query.getBindValues());
    }
}
