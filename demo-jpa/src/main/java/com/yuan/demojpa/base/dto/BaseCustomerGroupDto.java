package com.yuan.demojpa.base.dto;

import com.yuan.demojpa.base.pojo.BaseCustomerGroup;
import com.yuan.demojpa.commons.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerGroupDto extends BaseDto<BaseCustomerGroup> {
    private String name;
    private Integer enabled;

    @Builder
    public BaseCustomerGroupDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name, Integer enabled) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
        this.enabled = enabled;
    }

    @Override
    public Predicate toPredicate(Root<BaseCustomerGroup> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(super.toPredicate(root, query, crieriaBuilder));
        if (!StringUtils.isEmpty(name)) {
            predicates.add(crieriaBuilder.like(root.get("name"), name + "%"));
        }
        if (!StringUtils.isEmpty(enabled)) {
            predicates.add(crieriaBuilder.equal(root.get("enabled"), enabled));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }
}
