package com.yuan.demojpa.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {
    private Integer page = 0;
    private Integer size = 100;
    private String sort = "CREATEDATE DESC";
    private String id;
    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date updateDate;
    private Date creatDateStart;
    private Date createDateEnd;
    private Date updateDateStart;
    private Date updateDateEnd;
}
