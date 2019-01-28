package com.yuan.demojooqjpa.system.dto;

import com.yuan.demojooqjpa.commons.dto.BaseDto;
import com.yuan.demojooqjpa.system.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jooq.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto<User> {
    private String name;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        return super.toPredicate(root, query, crieriaBuilder);
    }


}
