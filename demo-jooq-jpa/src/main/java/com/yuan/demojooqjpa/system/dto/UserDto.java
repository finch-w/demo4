package com.yuan.demojooqjpa.system.dto;

import com.yuan.demojooqjpa.commons.dto.BaseDto;
import com.yuan.demojooqjpa.system.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto<User> {
}
