package com.yuan.demojpa2.system.dto;

import com.yuan.demojpa2.commons.dto.BaseDto;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto extends BaseDto {
    private String name;

    @Builder
    public RoleDto(Integer page, Integer size, String id, String createUser, String updateUser, Date createDate, Date updateDate, Date createDateStart, Date createDateEnd, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, id, createUser, updateUser, createDate, updateDate, createDateStart, createDateEnd, updateDateStart, updateDateEnd);
        this.name = name;
    }
}
