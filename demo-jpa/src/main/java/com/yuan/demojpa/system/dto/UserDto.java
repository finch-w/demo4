package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private String name;
    private String username;
    private Integer enabled;

    @Builder
    public UserDto(Integer page, Integer size, String sort, String id, String createUser, String updateUser, Date createDate, Date updateDate, Date creatDateStart, Date createDateEnd, Date updateDateStart, Date updateDateEnd, String name, String username, Integer enabled) {
        super(page, size, sort, id, createUser, updateUser, createDate, updateDate, creatDateStart, createDateEnd, updateDateStart, updateDateEnd);
        this.name = name;
        this.username = username;
        this.enabled = enabled;
    }
}
