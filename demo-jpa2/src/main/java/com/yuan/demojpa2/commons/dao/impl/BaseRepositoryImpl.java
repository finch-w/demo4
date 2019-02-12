package com.yuan.demojpa2.commons.dao.impl;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yuan.demojpa2.commons.dao.BaseRepository;
import com.yuan.demojpa2.commons.utils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.*;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ?> entityInformation;
    private final JPQLQueryFactory queryFactory;
    @Autowired
    private DSLContext dslContext;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public JPQLQueryFactory getQueryFactory() {
        return queryFactory;
    }

    @Override
    public DSLContext getDslContext() {
        return dslContext;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @SuppressWarnings({"Duplicates", "StringBufferReplaceableByString"})
    private String generateCountSQL(String sql) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(1) from (");
        stringBuilder.append(sql);
        stringBuilder.append(") ");
        stringBuilder.append(UUID.randomUUID().toString().replaceAll("-", ""));
        return stringBuilder.toString();
    }

    @Transactional
    @Override
    public void insert(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public void delete(ID... ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        entityManager.flush();
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    @Override
    public void update(T t) {
        T tDb = entityManager.find(entityInformation.getJavaType(), entityInformation.getId(t));
        BeanUtils.copyPojo(t, tDb);
        entityManager.refresh(tDb);
        entityManager.flush();
    }


    @SuppressWarnings("Duplicates")
    @Override
    public Optional<T> findOneByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
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
    public Optional<T> findOneByJPQL(String jpql, Collection collection) {
        return findOneByJPQL(jpql, collection.toArray());
    }

    @Override
    public Optional<T> findOneByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }


    @SuppressWarnings("Duplicates")
    @Override
    public List<T> findAllByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
        }
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


    @SuppressWarnings("Duplicates")
    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<T> resultList = query.getResultList();
        Long singleResult = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQL(jpql, pageable, collection.toArray());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public Optional<T> findOneBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        try {
            return Optional.ofNullable((T) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> findOneBySQL(String sql, Collection collection) {
        return findOneBySQL(sql, collection.toArray());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<T> findOneBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        try {
            return Optional.ofNullable((T) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<T> findOneByQuery(org.jooq.Query query) {
        return findOneBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    @SuppressWarnings({"Duplicates", "unchecked"})
    public List<T> findAllBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    public List<T> findAllBySQL(String sql, Collection collection) {
        return findAllBySQL(sql, collection.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    public List<T> findAllByQuery(org.jooq.Query query) {
        return findAllBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    @SuppressWarnings({"Duplicates", "unchecked"})
    public Page<T> findAllBySQL(String sql, Pageable pageable, Object... objects) {
        Query query = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        Query countQuery = entityManager.createNativeQuery(generateCountSQL(sql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<T> resultList = (List<T>) query.getResultList();
        Long singleResult = (Long) countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }

    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Collection collection) {
        return findAllBySQL(sql, pageable, collection.toArray());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public Page<T> findAllBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        Query countQuery = entityManager.createNativeQuery(generateCountSQL(sql), Long.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<T> resultList = (List<T>) query.getResultList();
        Long singleResult = (Long) countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }

    @Override
    public Page<T> findAllByQuery(org.jooq.Query query, Pageable pageable) {
        return findAllBySQL(query.getSQL(), pageable, query.getBindValues());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<T> resultList = query.getResultList();
        Long singleResult = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, singleResult);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Object... objects) {
        Query query = entityManager.createNativeQuery(sql, requireType);
        try {
            return Optional.ofNullable((R) query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Collection collection) {
        return findOneBySQL(sql, requireType, collection.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Optional<R> findOneBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        map.forEach(nativeQuery::setParameter);
        try {
            return Optional.ofNullable((R) nativeQuery.getSingleResult());
        } catch (Exception e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> findOneByQuery(org.jooq.Query query, Class<R> requireType) {
        return findOneBySQL(query.getSQL(), requireType, query.getBindValues());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (List<R>) nativeQuery.getResultList();
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Collection collection) {
        return findAllBySQL(sql, requireType, collection.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        map.forEach(nativeQuery::setParameter);
        return (List<R>) nativeQuery.getResultList();
    }

    @Override
    public <R> List<R> findAllByQuery(org.jooq.Query query, Class<R> requireType) {
        return findAllBySQL(query.getSQL(), requireType, query.getBindValues());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        Query countQuery = entityManager.createNativeQuery(sql, Long.class);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageSize());
        nativeQuery.setMaxResults(pageable.getPageSize());
        return new PageImpl<>((List<R>) nativeQuery.getResultList(), pageable, (Long) countQuery.getSingleResult());
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Collection collection) {
        return findAllBySQL(sql, pageable, requireType, collection.toArray());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        Query countQuery = entityManager.createNativeQuery(sql, Long.class);
        map.forEach(nativeQuery::setParameter);
        map.forEach(countQuery::setParameter);
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageSize());
        nativeQuery.setMaxResults(pageable.getPageSize());
        return new PageImpl<>((List<R>) nativeQuery.getResultList(), pageable, (Long) countQuery.getSingleResult());
    }

    @Override
    public <R> Page<R> findAllByQuery(org.jooq.Query query, Pageable pageable, Class<R> requireType) {
        return findAllBySQL(query.getSQL(), pageable, requireType, query.getBindValues());
    }

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
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
        return findAllByJPQL(jpql, requireType, collection.toArray());
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Map<String, Object> map) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        map.forEach(query::setParameter);
        return query.getResultList();
    }


    @SuppressWarnings("Duplicates")
    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Object... objects) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<R> list = query.getResultList();
        Long result = countQuery.getSingleResult();
        return new PageImpl<>(list, pageable, result);
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Collection collection) {
        return findAllByJPQL(jpql, pageable, requireType, collection.toArray());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        TypedQuery<R> query = entityManager.createQuery(jpql, requireType);
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        map.forEach(query::setParameter);
        map.forEach(countQuery::setParameter);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        List<R> list = query.getResultList();
        Long result = countQuery.getSingleResult();
        return new PageImpl<>(list, pageable, result);
    }

    @SuppressWarnings({"Duplicates"})
    @Override
    public Optional<Map> findOneBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        try {
            return Optional.ofNullable((Map) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<Map> findOneBySQLInMap(String sql, Collection collection) {
        return findOneBySQLInMap(sql, collection.toArray());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public Optional<Map> findOneBySQLInMap(String sql, Map<String, Object> map) {

        Query nativeQuery = entityManager.createNativeQuery(sql);
        map.forEach(nativeQuery::setParameter);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        try {
            return Optional.ofNullable((Map<String, Object>) nativeQuery.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public Optional<Map> findOneByQueryInMap(org.jooq.Query query) {
        return findOneBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @SuppressWarnings({"unchecked", "Duplicates", "deprecation"})
    @Override
    public List<Map> findAllBySQLInMap(String sql, Object... objects) {
        Session session = (Session) entityManager.getDelegate();
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map>) nativeQuery.getResultList();
    }

    @Override
    public List<Map> findAllBySQLInMap(String sql, Collection collection) {
        return findAllBySQLInMap(sql, collection.toArray());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public List<Map> findAllBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        map.forEach(nativeQuery::setParameter);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map>) nativeQuery.getResultList();
    }

    @Override
    public List<Map> findAllByQueryInMap(org.jooq.Query query) {
        return findAllBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Object... objects) {
        Query countQuery = entityManager.createNativeQuery(generateCountSQL(sql), Long.class);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            countQuery.setParameter(i + 1, objects[i]);
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = (List<Map>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Collection collection) {
        return findAllBySQLInMap(sql, pageable, collection.toArray());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public Page<Map> findAllBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        Query countQuery = entityManager.createNativeQuery(generateCountSQL(sql), Long.class);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        map.forEach(countQuery::setParameter);
        map.forEach(nativeQuery::setParameter);
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = (List<Map>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<Map> findAllByQueryInMap(org.jooq.Query query, Pageable pageable) {
        return findAllBySQLInMap(query.getSQL(), pageable, query.getBindValues());
    }

    @SuppressWarnings({"Duplicates"})
    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Object... objects) {
        Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
        }
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return Optional.ofNullable((Map) query.getSingleResult());
    }

    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Collection collection) {
        return findOneByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public Optional<Map> findOneByJPQLInMap(String jpql, Map<String, Object> map) {
        Query query = entityManager.createQuery(jpql);
        map.forEach(query::setParameter);
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return Optional.ofNullable((Map) query.getSingleResult());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Object... objects) {
        Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
        }
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map>) query.getResultList();
    }

    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Collection collection) {
        return findAllByJPQLInMap(jpql, collection.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map> findAllByJPQLInMap(String jpql, Map<String, Object> map) {
        Query query = entityManager.createQuery(jpql);
        map.forEach(query::setParameter);
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map>) query.getResultList();
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Object... objects) {
        Query query = entityManager.createQuery(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i + 1, objects[i]);
            countQuery.setParameter(i + 1, objects[i]);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = (List<Map>) query.getResultList();
        Long count = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, count);

    }

    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQLInMap(jpql, pageable, collection.toArray());
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Override
    public Page<Map> findAllByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map) {
        Query query = entityManager.createQuery(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(generateCountSQL(jpql), Long.class);
        map.forEach(query::setParameter);
        map.forEach(countQuery::setParameter);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = (List<Map>) query.getResultList();
        Long count = countQuery.getSingleResult();
        return new PageImpl<>(resultList, pageable, count);
    }


}
