package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.utils.BeanUtils;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    void save(User user);

    void insert(User user);

    void update(User user, BeanUtils.IgnoreMode mode);

    void delete(String[] ids);

    Optional<User> get(String id);

    List<Map<String, Object>> list(UserDto userDto);

    Page<Map<String, Object>> page(UserDto userDto);


    long check(User user);

    User get(User user);
}
