package com.yuan.demojpa.commons.dto.impl;

import org.jooq.Query;

public class DSLQuery extends CollectionQuery {
    public DSLQuery(Query query, com.querydsl.core.Query query1) {

        super(query.getSQL(), query.getBindValues());
    }
}
