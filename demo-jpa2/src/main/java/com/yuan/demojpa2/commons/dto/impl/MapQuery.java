package com.yuan.demojpa2.commons.dto.impl;

import com.yuan.demojpa2.commons.dto.Query;

import java.util.Map;

public class MapQuery implements Query<Map<String, Object>> {
    private String sql;
    private Map<String, Object> map;

    public MapQuery(String sql, Map<String, Object> map) {
        this.sql = sql;
        this.map = map;
    }

    public MapQuery(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSQL() {
        return sql;
    }

    @Override
    public Map<String, Object> getParams() {
        return map;
    }

}
