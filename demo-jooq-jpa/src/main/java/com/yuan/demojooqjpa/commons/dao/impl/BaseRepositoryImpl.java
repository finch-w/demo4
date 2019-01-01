package com.yuan.demojooqjpa.commons.dao.impl;

import com.yuan.demojooqjpa.commons.dao.BaseRepository;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
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

    @Override
    public void insert(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }

    @Override
    public void update(T t) {
        T tdb = entityManager.find(entityInformation.getJavaType(), entityInformation.getId(t));
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(t);
        PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
        Set<String> set = new HashSet<>();
        Arrays.stream(descriptors).map(FeatureDescriptor::getName).forEachOrdered(name -> {
            Object value = beanWrapper.getPropertyValue(name);
            if (!StringUtils.isEmpty(value)) {
                set.add(name);
            }
        });
        BeanUtils.copyProperties(t, tdb, set.toArray(new String[set.size()]));
        entityManager.refresh(tdb);
        entityManager.flush();
    }

    @Override
    public void deleteAllById(ID... ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        entityManager.flush();
    }

    @Override
    public Optional<T> getOne(Example<T> example) {
        Specification<T> specification = (Specification<T>) (root, query, criteriaBuilder) -> QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
        Optional<T> optional = new Optional<>(getQuery(specification, Sort.unsorted()).getSingleResult());
        optional.orElse(null);
        return optional;
    }

    @Override
    public T getBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEach(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    public T getBySQL(String sql, Collection collection) {
        return getBySQL(sql, collection.toArray());
    }

    @Override
    public T getBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    public T getByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getSingleResult();
    }

    @Override
    public T getByJPQL(String jpql, Collection collection) {
        return getByJPQL(jpql, collection.toArray());
    }

    @Override
    public T getByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(query::setParameter);
        return query.getSingleResult();
    }

    @Override
    public T getByQuery(org.jooq.Query query) {
        return getBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    public List<T> listBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEach(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return nativeQuery.getResultList();
    }

    @Override
    public List<T> listBySQL(String sql, Collection collection) {
        return listBySQL(sql, collection.toArray());
    }

    @Override
    public List<T> listBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return nativeQuery.getResultList();
    }

    @Override
    public List<T> listByJPQL(String jpql, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<T> listByJPQL(String jpql, Collection collection) {
        return listByJPQL(jpql, collection.toArray());
    }

    @Override
    public List<T> listByJPQL(String jpql, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<T> listByQuery(org.jooq.Query query) {
        return listBySQL(query.getSQL(), query.getBindValues());
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEach(value -> nativeQuery.setParameter(value + 1, objects[value]));
        return createSqlDomainPage(pageable, nativeQuery);
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Collection collection) {
        return pageBySQL(sql, pageable, collection.toArray());
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return createSqlDomainPage(pageable, nativeQuery);
    }

    @Override
    public Page<T> pageByJPQL(String jpql, Pageable pageable, Object... objects) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEachOrdered(value -> query.setParameter(value, objects[value]));
        return createJpqlDomainPage(pageable, query);
    }

    @Override
    public Page<T> pageByJPQL(String jpql, Pageable pageable, Collection collection) {
        return pageByJPQL(jpql, pageable, collection.toArray());
    }

    @Override
    public Page<T> pageByJPQL(String jpql, Pageable pageable, Map<String, Object> map) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityInformation.getJavaType());
        map.forEach(query::setParameter);
        return createJpqlDomainPage(pageable, query);
    }

    @Override
    public Page<T> pageByQuery(org.jooq.Query query, Pageable pageable) {
        return pageBySQL(query.getSQL(), pageable, query.getBindValues());
    }


    @Override
    public Map getBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = getSQLMapQuery(sql, objects);
        return (Map) nativeQuery.getSingleResult();
    }

    @Override
    public Map getBySQLInMap(String sql, Collection collection) {
        return getBySQLInMap(sql, collection.toArray());
    }

    @Override
    public Map getBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(nativeQuery::setParameter);
        return (Map) nativeQuery.getSingleResult();
    }

    @Override
    public Map getByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getSingleResult();
    }

    @Override
    public Map getByJPQLInMap(String jpql, Collection collection) {
        return getByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public Map getByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        return query.getSingleResult();
    }

    @Override
    public Map getByQueryInMap(org.jooq.Query query) {
        return getBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @Override
    public List<Map> listBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = getSQLMapQuery(sql, objects);
        return (List<Map>) nativeQuery.getResultList();
    }

    @Override
    public List<Map> listBySQLInMap(String sql, Collection collection) {
        return listBySQLInMap(sql, collection.toArray());
    }

    @Override
    public List<Map> listBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(nativeQuery::setParameter);
        return (List<Map>) nativeQuery.getResultList();
    }

    @Override
    public List<Map> listByJPQLInMap(String jpql, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return query.getResultList();
    }

    @Override
    public List<Map> listByJPQLInMap(String jpql, Collection collection) {
        return listByJPQLInMap(jpql, collection.toArray());
    }

    @Override
    public List<Map> listByJPQLInMap(String jpql, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<Map> listByQueryInMap(org.jooq.Query query) {
        return listBySQLInMap(query.getSQL(), query.getBindValues());
    }

    @Override
    public Page<Map> pageBySQLInMap(String sql, Pageable pageable, Object... objects) {
        Query nativeQuery = getSQLMapQuery(sql, objects);
        return createSQLMapPage(pageable, nativeQuery);
    }


    @Override
    public Page<Map> pageBySQLInMap(String sql, Pageable pageable, Collection collection) {
        return pageBySQLInMap(sql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        map.forEach(nativeQuery::setParameter);
        return createSQLMapPage(pageable, nativeQuery);
    }

    @Override
    public Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Object... objects) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        IntStream.range(0, objects.length).forEachOrdered(i -> query.setParameter(i + 1, objects[i]));
        return createJpqlMapPage(pageable, query);
    }

    @Override
    public Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Collection collection) {
        return pageByJPQLInMap(jpql, pageable, collection.toArray());
    }

    @Override
    public Page<Map> pageByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map) {
        TypedQuery<Map> query = entityManager.createQuery(jpql, Map.class);
        map.forEach(query::setParameter);
        return createJpqlMapPage(pageable, query);
    }

    @Override
    public Page<Map> pageByQueryInMap(org.jooq.Query query, Pageable pageable) {
        return pageBySQLInMap(query.getSQL(), pageable, query.getBindValues());
    }




/*
重复复用
 */

    private Query getSQLMapQuery(String sql, Object[] objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEach(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return nativeQuery;
    }

    private Page<Map> createSQLMapPage(Pageable pageable, Query nativeQuery) {
        int size = nativeQuery.getResultList().size();
        nativeQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<Map> resultList = (List<Map>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, size);
    }

    private Page<T> createJpqlDomainPage(Pageable pageable, TypedQuery<T> query) {
        return createDomainPage(pageable, query);
    }

    private Page<T> createSqlDomainPage(Pageable pageable, Query nativeQuery) {
        return createDomainPage(pageable, nativeQuery);
    }

    private Page<T> createDomainPage(Pageable pageable, Query nativeQuery) {
        int size = nativeQuery.getResultList().size();
        nativeQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, size);
    }

    private Page<Map> createJpqlMapPage(Pageable pageable, TypedQuery<Map> query) {
        int size = query.getResultList().size();
        query.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        query.setMaxResults(pageable.getPageSize());
        List<Map> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, size);
    }
}
