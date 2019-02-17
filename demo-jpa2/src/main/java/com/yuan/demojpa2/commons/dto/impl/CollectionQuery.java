package com.yuan.demojpa2.commons.dto.impl;

import com.yuan.demojpa2.commons.dto.Query;

import java.util.Collection;

public class CollectionQuery implements Query<Collection<Object>> {
    private String sql;
    private Collection<Object> collection;

    public CollectionQuery(String sql, Collection<Object> collection) {
        this.sql = sql;
        this.collection = collection;
    }

    public CollectionQuery(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSQL() {
        return sql;
    }

    @Override
    public Collection<Object> getParams() {
        return collection;
    }
}
