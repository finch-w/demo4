package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String username;
    private String name;
    private Integer enabled;

    @Builder
    public UserDto(Integer page, Integer size, String sort, String id, String createUser, String updateUser, Date createDate, Date updateDate, Date creatDateStart, Date createDateEnd, Date updateDateStart, Date updateDateEnd, String username, String name, Integer enabled) {
        super(page, size, sort, id, createUser, updateUser, createDate, updateDate, creatDateStart, createDateEnd, updateDateStart, updateDateEnd);
        this.username = username;
        this.name = name;
        this.enabled = enabled;
    }
}
