package com.yuan.demojpa.commons.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.commons.utils.BeanUtils;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.EntityInformation;
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
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final EntityInformation<T, ?> entityInformation;
    private final EntityPathResolver resolver;

    private final JPAQueryFactory queryFactory;

    @Autowired
    private DSLContext dslContext;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
        this.resolver = resolver;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public JPAQueryFactory getQueryFactory() {
        return this.queryFactory;
    }

    @Override
    public DSLContext getDslContext() {
        return this.dslContext;
    }


    private String generatorCountSQL(String sql) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(1) from (");
        stringBuilder.append(sql);
        stringBuilder.append(") ");
        stringBuilder.append(UUID.randomUUID().toString().replaceAll("-", ""));
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
    public void delete(ID... ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        entityManager.flush();
    }

    @Override
    public Optional<T> findOneBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> findOneBySQL(String sql, Collection collection) {
        return findOneBySQL(sql, collection.toArray());
    }

    @Override
    public Optional<T> findOneBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> findOneByQuery(org.jooq.Query query) {
        return findOneBySQL(query.getSQL(), query.getBindValues());
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
    public List<T> findAllByQuery(org.jooq.Query query) {
        return findAllBySQL(query.getSQL(), query.getBindValues());
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
    public Page<T> findAllByQuery(org.jooq.Query query, Pageable pageable) {
        return findAllBySQL(query.getSQL(), pageable, query.getBindValues());
    }

    @Override
    public Optional<T> findOneByJPQL(String jpql, Object... objects) {
        TypedQuery<T> nativeQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> nativeQuery.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(((T) nativeQuery.getSingleResult()));
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> findOneByJPQL(String jpql, Collection collection) {
        return findOneByJPQL(jpql, collection.toArray());
    }

    @Override
    public Optional<T> findOneByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> nativeQuery = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable((T) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
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
    public Optional<Map> findOneBySQLInMap(String sql, Object... objects) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable((Map) query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<Map> findOneBySQLInMap(String sql, Collection collection) {
        return findOneBySQLInMap(sql, collection.toArray());
    }

    @Override
    public Optional<Map> findOneBySQLInMap(String sql, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql).setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(query::setParameter);
        try {
            return Optional.ofNullable((Map) query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<Map> findOneByQueryInMap(org.jooq.Query query) {
        return findOneBySQLInMap(query.getSQL(), query.getBindValues());
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
    public List<Map> findAllByQueryInMap(org.jooq.Query query) {
        return findAllBySQLInMap(query.getSQL(), query.getBindValues());
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
    public Page<Map> findAllByQueryInMap(org.jooq.Query query, Pageable pageable) {
        return findAllBySQLInMap(query.getSQL(), pageable, query.getBindValues());
    }

    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Collection collection) {
        return findOneByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
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

    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        try {
            return Optional.ofNullable((R) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Collection collection) {
        return findOneBySQL(sql, requireType, collection.toArray());
    }

    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable((R) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> findOneByQuery(org.jooq.Query query, Class<R> requireType) {
        return findOneBySQL(query.getSQL(), requireType, query.getBindValues());
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (List<R>) nativeQuery.getSingleResult();
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Collection collection) {
        return findAllBySQL(sql, requireType, collection.toArray());
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        map.forEach(nativeQuery::setParameter);
        return (List<R>) nativeQuery.getSingleResult();
    }

    @Override
    public <R> List<R> findAllByQuery(org.jooq.Query query, Class<R> requireType) {
        return findAllBySQL(query.getSQL(), requireType, query.getBindValues());
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        Query countQuery = entityManager.createNativeQuery(generatorCountSQL(sql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<R> resultList = (List<R>) nativeQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Collection collection) {
        return findAllBySQL(sql, pageable, requireType, collection.toArray());
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        Query countQuery = entityManager.createNativeQuery(generatorCountSQL(sql), Long.class);
        map.forEach(nativeQuery::setParameter);
        map.forEach(countQuery::setParameter);
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<R> resultList = (List<R>) nativeQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public <R> Page<R> findAllByQuery(org.jooq.Query query, Pageable pageable, Class<R> requireType) {
        return findAllBySQL(query.getSQL(), pageable, requireType, query.getBindValues());
    }

    @Override
    public <R> Optional<R> findOneByJPQL(String jpql, Class<R> requireType, Object... objects) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
        }
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> findOneByJPQL(String jpql, Class<R> requireType, Collection collection) {
        return findOneByJPQL(jpql, requireType, collection.toArray());
    }

    @Override
    public <R> Optional<R> findOneByJPQL(String jpql, Class<R> requireType, Map<String, Object> map) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        map.forEach(query::setParameter);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Object... objects) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
        }
        return query.getResultList();
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Collection collection) {
        return findAllBySQL(jpql, requireType, collection.toArray());
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Map<String, Object> map) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Object... objects) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        TypedQuery<Long> countQuery = entityManager.createQuery(generatorCountSQL(jpql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<R> resultList = query.getResultList();
        Long singleResult = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Collection collection) {
        return findAllBySQL(jpql, pageable, requireType, collection.toArray());
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        TypedQuery<Long> countQuery = entityManager.createQuery(generatorCountSQL(jpql), Long.class);
        map.forEach(query::setParameter);
        map.forEach(countQuery::setParameter);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<R> resultList = query.getResultList();
        Long singleResult = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }


}
