package com.yuan.demojpa.commons.dao.impl;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.commons.utils.BeanUtils;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
@NoRepositoryBean
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final EntityInformation<T, ?> entityInformation;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }


    private String generatorCountSQL(String sql) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(1) from (");
        stringBuilder.append(sql);
        stringBuilder.append(") COUNTSUM");
        return stringBuilder.toString();
    }

    @Override
    @Transactional
    public void insert(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void update(T t) {
        T tDb = entityManager.find(entityInformation.getJavaType(), entityInformation.getId(t));
        BeanUtils.copyPojo(t, tDb);
        entityManager.refresh(tDb);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteAllById(ID... ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        entityManager.flush();
    }

    @Override
    public Optional<T> getOneBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
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
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Object... objects) {
        TypedQuery<T> nativeQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Collection collection) {
        return getOneByJPQL(jpql, collection.toArray());
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> nativeQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable((T) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public List<T> findAllBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return nativeQuery.getResultList();
    }

    @Override
    public List<T> findAllBySQL(String sql, Collection collection) {
        return findAllBySQL(sql, collection.toArray());
    }

    @Override
    public List<T> findAllBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
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
    public Page<T> findAllBySQL(String sql, Pageable pageable, Object... objects) {
        String countSql = generatorCountSQL(sql);
        Query countQuery = entityManager.createNativeQuery(countSql);
        Query selectQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> {
            countQuery.setParameter(i + 1, objects[i]);
            selectQuery.setParameter(i + 1, objects[i]);
        });
        Long count = (Long) countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        selectQuery.setMaxResults(pageable.getPageSize());
        List resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Collection collection) {
        return findAllBySQL(sql, pageable, collection.toArray());
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        String countSql = generatorCountSQL(sql);
        Query countQuery = entityManager.createNativeQuery(countSql);
        Query selectQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach((key, value) -> {
            countQuery.setParameter(key, value);
            selectQuery.setParameter(key, value);
        });
        Long count = (Long) countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        selectQuery.setMaxResults(pageable.getPageSize());
        List resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Object... objects) {
        String countJpql = generatorCountSQL(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        TypedQuery<T> selectQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> {
            countQuery.setParameter(i + 1, objects[i]);
            selectQuery.setParameter(i + 1, objects[i]);
        });
        Long count = (Long) countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        selectQuery.setMaxResults(pageable.getPageSize());
        List resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQL(jpql, pageable, collection);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Map<String, Object> map) {
        String countJpql = generatorCountSQL(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        TypedQuery<T> selectQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach((key, value) -> {
            countQuery.setParameter(key, value);
            selectQuery.setParameter(key, value);
        });
        Long count = (Long) countQuery.getSingleResult();
        selectQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        selectQuery.setMaxResults(pageable.getPageSize());
        List resultList = selectQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Map getOneBySQLInMap(String sql, Object... objects) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return (Map) query.getSingleResult();
    }

    @Override
    public Map getOneBySQLInMap(String sql, Collection collection) {
        return getOneBySQLInMap(sql, collection.toArray());
    }

    @Override
    public Map getOneBySQLInMap(String sql, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(query::setParameter);
        return (Map) query.getSingleResult();
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getSingleResult();
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Collection collection) {
        return getOneByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public Map getOneByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        return null;
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Object... objects) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Collection collection) {
        return findAllBySQLInMap(sql, collection.toArray());
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
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
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Object... objects) {
        String countSql = generatorCountSQL(sql);
        Query countQuery = entityManager.createNativeQuery(countSql);
        Query nativeQuery = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
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
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Collection collection) {
        return findAllBySQLInMap(sql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        String countSql = generatorCountSQL(sql);
        Query countQuery = entityManager.createNativeQuery(countSql);
        Query nativeQuery = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(countQuery::setParameter);
        map.forEach(nativeQuery::setParameter);
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Object... objects) {
        String countSql = generatorCountSQL(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);
        TypedQuery<Map> nativeQuery = entityManager.createQuery(jpql, Map.class);
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
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQLInMap(jpql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map) {
        String countSql = generatorCountSQL(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);
        TypedQuery<Map> nativeQuery = entityManager.createQuery(jpql, Map.class);
        map.forEach(countQuery::setParameter);
        map.forEach(nativeQuery::setParameter);
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }
}
