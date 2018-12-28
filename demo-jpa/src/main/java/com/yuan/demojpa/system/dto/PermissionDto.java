package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionDto extends BaseDto {
    private String name;

    @Builder
    public PermissionDto(Integer page, Integer size, String sort, String id, String createUser, String updateUser, Date createDate, Date updateDate, Date creatDateStart, Date createDateEnd, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, sort, id, createUser, updateUser, createDate, updateDate, creatDateStart, createDateEnd, updateDateStart, updateDateEnd);
        this.name = name;
    }
}
