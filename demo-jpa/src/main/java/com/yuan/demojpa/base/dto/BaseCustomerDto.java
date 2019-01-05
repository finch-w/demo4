package com.yuan.demojpa.base.dto;

import com.yuan.demojpa.base.pojo.BaseCustomer;
import com.yuan.demojpa.commons.dto.BaseDto;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerDto extends BaseDto<BaseCustomer> {
    private String name;
    private String type;
    private String group;
    private String city;

    @Builder
    public BaseCustomerDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name, String type, String group, String city) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
        this.type = type;
        this.group = group;
        this.city = city;
    }

    @Override
    public Predicate toPredicate(Root<BaseCustomer> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(name)) {
            Predicate cname = crieriaBuilder.like(root.get("cname"), name + "%");
            Predicate cnameShort = crieriaBuilder.like(root.get("cnameShort"), name + "%");
            Predicate ename = crieriaBuilder.like(root.get("ename"), name + "%");
            Predicate enameShort = crieriaBuilder.like(root.get("enameShort"), name + "%");
            Predicate predicate = crieriaBuilder.or(cname, cnameShort, ename, enameShort);
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(type)) {
            predicates.add(crieriaBuilder.equal(root.get("type"), type));
        }
        if (!StringUtils.isEmpty(group)) {
            predicates.add(crieriaBuilder.equal(root.get("group"), group));
        }
        if (!StringUtils.isEmpty(city)) {
            predicates.add(crieriaBuilder.equal(root.get("city"), city));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }
}
