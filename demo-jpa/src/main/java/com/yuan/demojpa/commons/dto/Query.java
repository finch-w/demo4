package com.yuan.demojpa.commons.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query {
    private String sql;
    private List<Object> objects = new ArrayList<>();

    public Query(String sql) {
        this.sql = sql;
    }

    public Query(String sql, Object... objects) {
        this.sql = sql;
        this.objects = Arrays.asList(objects);
    }

    public Query(String sql, List<Object> objects) {
        this.sql = sql;
        this.objects = objects;
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getObjects() {
        return objects;
    }
}
