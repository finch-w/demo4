package com.yuan.demojpa.dao.impl;

import com.yuan.demojpa.commons.repository.BaseRespository;
import com.yuan.demojpa.commons.utils.BeanUtils;
import org.eclipse.persistence.config.ResultType;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.eclipse.persistence.config.QueryHints.RESULT_TYPE;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRespository<T, ID> {
    private final EntityManager entityManager;
    private final Class<T> domainClass;


    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }

    @Override
    @Transactional
    public void insert(T t) {
        entityManager.persist(t);
    }

    @Override
    @Transactional
    public void insertAndFlush(T t) {
        insert(t);
        flush();
    }

    @Override
    @Transactional
    public void update(T t, BeanUtils.IgnoreMode mode) {
        T tDb = entityManager.find(domainClass, new BeanWrapperImpl(t).getPropertyValue("id"));
        BeanUtils.copyPojo(t, tDb, mode);
        entityManager.refresh(tDb);
    }

    @Override
    @Transactional
    public void updateAndFlush(T t, BeanUtils.IgnoreMode mode) {
        update(t, mode);
        flush();
    }

    @Override
    public void deleteAllByIds(ID[] ids) {
        Arrays.stream(ids).forEach(this::deleteById);
        flush();
    }

    @Override
    @Transactional
    public T getOne(Example<T> example) {
        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
            }
        };
        return getQuery(specification, Sort.unsorted()).getSingleResult();
    }

    @Override
    @Transactional
    public T getBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 0, objects[i]);
        }
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    @Transactional
    public T getBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    @Transactional
    public List<T> listBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    @Transactional
    public List<T> listBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    @Transactional
    public Page<T> pageBySQL(String sql, Pageable pageable, Object... objects) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            countQuery.setParameter(i + 1, objects[i]);
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        Long count = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult(Math.toIntExact(pageable.getOffset()));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<T> resultList = (List<T>) countQuery.getResultList();
        return new PageImpl<T>(resultList, pageable, count);
    }

    @Override
    @Transactional
    public Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query sqlQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
            sqlQuery.setParameter(entry.getKey(), entry.getValue());
        }
        Long count = (Long) countQuery.getSingleResult();

        List<T> resultList = sqlQuery.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize()).getResultList();
        return new PageImpl<T>(resultList, pageable, count);
    }

    @Override
    @Transactional
    public Map<String, Object> getBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.setHint(RESULT_TYPE, ResultType.Map);
        return (Map<String, Object>) nativeQuery.getSingleResult();
    }

    @Override
    @Transactional
    public Map<String, Object> getBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        nativeQuery.setHint(RESULT_TYPE, ResultType.Map);
        return (Map<String, Object>) nativeQuery.getSingleResult();
    }

    @Override
    @Transactional
    public List<Map<String, Object>> listBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.setHint(RESULT_TYPE, ResultType.Map);
        return (List<Map<String, Object>>) nativeQuery.getResultList();
    }

    @Override
    @Transactional
    public List<Map<String, Object>> listBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        nativeQuery.setHint(RESULT_TYPE, ResultType.Map);
        return ((ArrayList<Map<String, Object>>) nativeQuery.getResultList());
    }

    @Override
    @Transactional
    public Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Object... objects) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query sqlQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            countQuery.setParameter(i + 1, objects[i]);
            sqlQuery.setParameter(i + 1, objects[i]);
        }
        Long count = (Long) countQuery.getSingleResult();
        List<Map<String, Object>> resultList = ((List<Map<String, Object>>) sqlQuery.setHint(RESULT_TYPE, ResultType.Map).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList());
        return new PageImpl<Map<String, Object>>(resultList, pageable, count);
    }

    @Override
    @Transactional
    public Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query sqlQuery = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
            sqlQuery.setParameter(entry.getKey(), entry.getValue());
        }
        Long count = (Long) countQuery.getSingleResult();
        List<Map<String, Object>> resultList = sqlQuery.setHint(RESULT_TYPE, ResultType.Map).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        return new PageImpl<Map<String, Object>>(resultList, pageable, count);
    }


}
