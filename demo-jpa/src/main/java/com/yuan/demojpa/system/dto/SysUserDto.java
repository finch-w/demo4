package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import com.yuan.demojpa.system.pojo.SysUser;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDto extends BaseDto<SysUser> {
    private String name;

    @Builder
    public SysUserDto(Integer page, Integer size, String order, String id, Collection<String> ids, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, order, id, ids, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
    }


}
