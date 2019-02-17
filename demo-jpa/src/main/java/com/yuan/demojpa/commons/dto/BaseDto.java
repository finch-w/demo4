package com.yuan.demojpa.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto<T> implements Serializable {
    private Integer page = 1;
    private Integer size = 100;
    private String order = "createDate";
    private String id;
    private @Singular
    Collection<String> ids;
    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date createDateStart;
    private Date createDateEnd;
    private Date updateDate;
    private Date updateDateStart;
    private Date updateDateEnd;
}
