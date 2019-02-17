package com.yuan.demojpa.commons.dto;

public interface Query<paramsType> {
    String getSQL();

    paramsType getParams();
}
