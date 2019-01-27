package com.yuan.demojpa2.commons.dao.impl;

import com.yuan.demojpa2.commons.dao.BaseRepository;
import com.yuan.demojpa2.commons.utils.BeanUtils;
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
import java.util.*;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @SuppressWarnings("Duplicates")
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

    @Transactional
    @Override
    public void deleteByIds(ID... ids) {
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
    public Optional<T> getOneByJPQL(String jpql, Object... objects) {
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
    public Optional<T> getOneByJPQL(String jpql, Collection collection) {
        return getOneByJPQL(jpql, collection.toArray());
    }

    @Override
    public Optional<T> getOneByJPQL(String jpql, Map<String, Object> map) {
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
        return new PageImpl<T>(resultList, pageable, singleResult);
    }

    @Override
    public Page<T> findAllByJPQL(String jpql, Pageable pageable, Collection collection) {
        return findAllByJPQL(jpql, pageable, collection.toArray());
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    @Override
    public Optional<T> getOneBySQL(String sql, Object... objects) {
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
    public Optional<T> getOneBySQL(String sql, Collection collection) {
        return getOneBySQL(sql, collection.toArray());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<T> getOneBySQL(String sql, Map<String, Object> map) {
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
        return new PageImpl<T>(resultList, pageable, singleResult);
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
        return new PageImpl<T>(resultList, pageable, singleResult);
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
        return new PageImpl<T>(resultList, pageable, singleResult);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Optional<R> getOneBySQL(String sql, Class<R> requireType, Object... objects) {
        Query query = entityManager.createNativeQuery(sql, requireType);
        try {
            return Optional.ofNullable((R) query.getSingleResult());
        } catch (NonUniqueResultException e) {
            throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
        }
    }

    @Override
    public <R> Optional<R> getOneBySQL(String sql, Class<R> requireType, Collection collection) {
        return getOneBySQL(sql, requireType, collection.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Optional<R> getOneBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        Query nativeQuery = entityManager.createNativeQuery(sql, requireType);
        map.forEach(nativeQuery::setParameter);
        return Optional.ofNullable((R) nativeQuery.getSingleResult());
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Object... objects) {
        return null;
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Collection collection) {
        return null;
    }

    @Override
    public <R> List<R> findAllBySQL(String sql, Class<R> requireType, Map<String, Object> map) {
        return null;
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Object... objects) {
        return null;
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Collection collection) {
        return null;
    }

    @Override
    public <R> Page<R> findAllBySQL(String sql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        return null;
    }

    @Override
    public <R> Optional<R> getOneByJPQL(String jpql, Class<R> requireType, Object... objects) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> getOneByJPQL(String jpql, Class<R> requireType, Collection collection) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> getOneByJPQL(String jpql, Class<R> requireType, Map<String, Object> map) {
        return Optional.empty();
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Object... objects) {
        return null;
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Collection collection) {
        return null;
    }

    @Override
    public <R> List<R> findAllByJPQL(String jpql, Class<R> requireType, Map<String, Object> map) {
        return null;
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Object... objects) {
        return null;
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Collection collection) {
        return null;
    }

    @Override
    public <R> Page<R> findAllByJPQL(String jpql, Pageable pageable, Class<R> requireType, Map<String, Object> map) {
        return null;
    }

    @Override
    public Optional<Map<String, Object>> getOneBySQLInMap(String sql, Object... objects) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> getOneBySQLInMap(String sql, Collection collection) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> getOneBySQLInMap(String sql, Map<String, Object> map) {
        return Optional.empty();
    }

    @Override
    public List<Map<String, Object>> findAllBySQLInMap(String sql, Object... objects) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAllBySQLInMap(String sql, Collection collection) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAllBySQLInMap(String sql, Map<String, Object> map) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllBySQLInMap(String sql, Pageable pageable, Object... objects) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllBySQLInMap(String sql, Pageable pageable, Collection collection) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllBySQLInMap(String sql, Pageable pageable, Map<String, Object> map) {
        return null;
    }

    @Override
    public Optional<Map<String, Object>> getOneByJPQLInMap(String jpql, Object... objects) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> getOneByJPQLInMap(String jpql, Collection collection) {
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> getOneByJPQLInMap(String jpql, Map<String, Object> map) {
        return Optional.empty();
    }

    @Override
    public List<Map<String, Object>> findAllByJPQLInMap(String jpql, Object... objects) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAllByJPQLInMap(String jpql, Collection collection) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAllByJPQLInMap(String jpql, Map<String, Object> map) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllByJPQLInMap(String jpql, Pageable pageable, Object... objects) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllByJPQLInMap(String jpql, Pageable pageable, Collection collection) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> findAllByJPQLInMap(String jpql, Pageable pageable, Map<String, Object> map) {
        return null;
    }


}
