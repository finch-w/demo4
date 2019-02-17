package com.yuan.demojpa.commons.dto.impl;

import com.yuan.demojpa.commons.dto.Query;

public class ArraysQuery implements Query<Object[]> {
    private String sql;
    private Object[] objects;

    public ArraysQuery(String sql, Object... objects) {
        this.sql = sql;
        this.objects = objects;
    }

    @Override
    public String getSQL() {
        return this.sql;
    }

    @Override
    public Object[] getParams() {
        return this.objects;
    }

}
