package com.yuan.demojpa.commons.service;

import com.yuan.demojpa.commons.pojo.BasePojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    T save(T t);

    void insert(T t);

    void update(T t);

    @SuppressWarnings({"unchecked"})
    void delete(ID... ids);

    Optional<T> getById(ID id);

    Optional<T> getByExample(T t);

    List<T> findByExample(T t);

    List<T> findByExample(T t, Sort sort);

    Page<T> findByExample(T t, Pageable pageable);

    boolean exist(T t);

    long count(T t);
}
