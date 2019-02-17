package com.yuan.demojpa2.commons.dto;

public interface Query<paramsType> {
    String getSQL();

    paramsType getParams();
}
