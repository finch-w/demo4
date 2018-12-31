package com.yuan.demojooqjpa.commons.dao.impl;

import com.yuan.demojooqjpa.commons.dao.BaseDao;
import com.yuan.demojooqjpa.commons.utils.BeanUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public class BaseDaoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {
    private final EntityManager entityManager;
    private final Class<T> domainClass;

    public BaseDaoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
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
    public void insertAndFlush(T t) {
        insert(t);
        flush();
    }

    @Override
    public void update(T t, BeanUtils.IgnoreMode mode) {
        T tDb = entityManager.find(domainClass, new BeanWrapperImpl(t).getPropertyValue("id"));
        BeanUtils.copyPojo(t, tDb, mode);
        entityManager.refresh(tDb);

    }

    @Override
    public void updateAndFlush(T t, BeanUtils.IgnoreMode mode) {
        update(t, mode);
        flush();
    }

    @Override
    public void deleteByIds(ID[] ids) {
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
    public T getBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    public T getBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    public T getByQuery(org.jooq.Query query) {
        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
        }
        return (T) nativeQuery.getSingleResult();
    }

    @Override
    public List<T> listBySQL(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    public List<T> listBySQL(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    public List<T> listByQuery(org.jooq.Query query) {
        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
        }
        return (List<T>) nativeQuery.getResultList();
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Object... objects) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (int i = 0; i < objects.length; i++) {
            countQuery.setParameter(i + 1, objects[i]);
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        Long count = (Long) countQuery.getSingleResult();
        List<T> result = (List<T>) nativeQuery.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize()).getSingleResult();
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public Page<T> pageBySQL(String sql, Pageable pageable, Map<String, Object> map) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        Long count = (Long) countQuery.getSingleResult();
        List<T> result = (List<T>) nativeQuery.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize()).getSingleResult();
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public Page<T> pageByQuery(org.jooq.Query query, Pageable pageable) {
        String sql = query.getSQL();
        String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        Query countQuery = entityManager.createNativeQuery(countSql);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
            countQuery.setParameter(i + 1, bindValues.get(i));
        }
        Long total = (Long) countQuery.getSingleResult();
        nativeQuery.setFirstResult((int) pageable.getOffset());
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<T> resultList = (List<T>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, total);
    }

    @Override
    public Map<String, Object> getBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) nativeQuery.getSingleResult();
    }

    @Override
    public Map<String, Object> getBySQLInMap(String sql, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) nativeQuery.getSingleResult();
    }

    @Override
    public Map<String, Object> getByQueryInMap(org.jooq.Query query) {
        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) nativeQuery.getSingleResult();
    }

    @Override
    public List<Map<String, Object>> listBySQLInMap(String sql, Object... objects) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map<String, Object>>) nativeQuery.getResultList();
    }

    @Override
    public List<Map<String, Object>> listBySQLInMap(String sql, Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listBySQLInMap(org.jooq.Query query) {
        String sql = query.getSQL();
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map<String, Object>>) nativeQuery.getResultList();
    }

    @Override
    public Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Object... objects) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            nativeQuery.setParameter(i + 1, objects[i]);
            nativeQuery.setParameter(i + 1, objects[i]);
        }
        Long countQuerySingleResult = (Long) countQuery.getSingleResult();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize()).getResultList();
        return new PageImpl<>(resultList, pageable, countQuerySingleResult);
    }

    @Override
    public Page<Map<String, Object>> pageBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        String countSQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query countQuery = entityManager.createNativeQuery(countSQL);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
            nativeQuery.setParameter(entry.getKey(), entry.getValue());
        }
        Long countQuerySingleResult = (Long) countQuery.getSingleResult();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1)).setMaxResults(pageable.getPageSize()).getResultList();
        return new PageImpl<>(resultList, pageable, countQuerySingleResult);
    }

    @Override
    public Page<Map<String, Object>> pageByQueryInMap(org.jooq.Query query, Pageable pageable) {
        String sql = query.getSQL();
        String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
        List<Object> bindValues = query.getBindValues();
        Query nativeQuery = entityManager.createNativeQuery(sql, domainClass);
        Query countQuery = entityManager.createNativeQuery(countSql);
        for (int i = 0; i < bindValues.size(); i++) {
            nativeQuery.setParameter(i + 1, bindValues.get(i));
            countQuery.setParameter(i + 1, bindValues.get(i));
        }
        Long total = (Long) countQuery.getSingleResult();
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        nativeQuery.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        nativeQuery.setMaxResults(pageable.getPageSize());
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) nativeQuery.getResultList();
        return new PageImpl<>(resultList, pageable, total);
    }
}
