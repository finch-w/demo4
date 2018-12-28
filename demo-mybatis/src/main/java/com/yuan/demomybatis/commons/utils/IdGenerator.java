package com.yuan.demomybatis.commons.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

public class IdGenerator implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString();
    }
}
