package com.yuan.demojpa.config;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
public class QueryDSLContext {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public QueryDSLContext(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    public void insert(Object object) {
        entityManager.persist(object);
    }

    public Object save(Object object) {
        return entityManager.merge(object);
    }

    public JPAQuery<?> query() {
        return queryFactory.query();
    }

    public JPAUpdateClause update(EntityPath<?> entityPath) {
        return queryFactory.update(entityPath);
    }

    public JPADeleteClause delete(EntityPath<?> entityPath) {
        return queryFactory.delete(entityPath);
    }
}
