package com.yuan.demojpa.commons.dto;

public interface Query<PARAMTYPE> {
    String getSQL();

    PARAMTYPE getParams();
}
