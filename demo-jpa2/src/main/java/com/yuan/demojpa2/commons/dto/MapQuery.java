package com.yuan.demojpa2.commons.dto;

import java.util.HashMap;
import java.util.Map;

public class MapQuery {
    private String sql;
    private Map<String, Object> map = new HashMap<>();

    public MapQuery(String sql) {
        this.sql = sql;
    }

    public MapQuery(String sql, Map<String, Object> map) {
        this.sql = sql;
        this.map = new HashMap<>(map);
    }

    public String getSql() {
        return sql;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
