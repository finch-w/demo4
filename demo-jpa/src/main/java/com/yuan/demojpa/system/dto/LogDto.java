package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import com.yuan.demojpa.system.pojo.Log;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDto extends BaseDto<Log> {
    private String operator;
    private String operation;
    private String content;
    private String ipAddress;

    @Builder
    public LogDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String operator, String operation, String content, String ipAddress) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.operator = operator;
        this.operation = operation;
        this.content = content;
        this.ipAddress = ipAddress;
    }
}
