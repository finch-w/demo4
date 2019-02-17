package com.yuan.demojpa2.commons.dto;

public interface Query<PARAMTYPE> {
    String getSQL();

    PARAMTYPE getParams();
}
