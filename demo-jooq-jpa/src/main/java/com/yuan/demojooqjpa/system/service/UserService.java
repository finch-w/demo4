package com.yuan.demojooqjpa.system.service;

import com.yuan.demojooqjpa.commons.service.BaseService;
import com.yuan.demojooqjpa.system.dto.UserDto;
import com.yuan.demojooqjpa.system.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseService<User, String> {
    Page<User> data(UserDto dto);

    Page<User> data2(UserDto dto);

    Page<User> data3(UserDto dto);

    Page<User> data4(UserDto dto);

    List<User> list(UserDto dto);

    List<User> list2(UserDto dto);

    List<User> list3(UserDto dto);

    List<User> list4(UserDto dto);
}
