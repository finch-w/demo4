package com.yuan.demojpa.commons.dao.impl;

import com.yuan.demojpa.commons.dao.BaseRepository;
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
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

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
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            Object value = beanWrapper.getPropertyValue(name);
            if (!StringUtils.isEmpty(value)) {
                set.add(name);
            }
        }
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
    public T getOne(Example<T> example) {
        Specification<T> specification = (Specification<T>) (root, query, criteriaBuilder) -> QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
        return getQuery(specification, Sort.unsorted()).getSingleResult();
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
    public Page<T> pageBySQL(String sql, Pageable pageable, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        IntStream.range(0, objects.length).forEach(value -> nativeQuery.setParameter(value + 1, objects[value]));
        return getPage(pageable, nativeQuery);
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Collection collection) {
        return pageBySQL(sql, pageable, collection.toArray());
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, entityInformation.getJavaType());
        map.forEach(nativeQuery::setParameter);
        return getPage(pageable, nativeQuery);
    }

    @Override
    public Map getBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = getMapQuery(sql, objects);
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
    public List<Map> listBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = getMapQuery(sql, objects);
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
    public Page<Map> pageBySQLInMap(String sql, Pageable pageable, Object... objects) {
        Query nativeQuery = getMapQuery(sql, objects);
        return mapPageCreator(pageable, nativeQuery);
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
        return mapPageCreator(pageable, nativeQuery);
    }

    private Page<T> getPage(Pageable pageable, Query nativeQuery) {
        int size = nativeQuery.getResultList().size();
        nativeQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List resultList = nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, size);
    }

    private Page<Map> mapPageCreator(Pageable pageable, Query nativeQuery) {
        int size = nativeQuery.getResultList().size();
        nativeQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<Map> resultList = (List<Map>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, size);
    }

    private Query getMapQuery(String sql, Object[] objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        IntStream.range(0, objects.length).forEach(i -> nativeQuery.setParameter(i + 1, objects[i]));
        return nativeQuery;
    }
}
