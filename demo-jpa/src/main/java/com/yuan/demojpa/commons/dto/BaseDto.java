package com.yuan.demojpa.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto<T> implements Serializable, Specification<T> {
    private Integer page = 1;
    private Integer size = 100;
    private String order;
    private String id;
    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date createDateStart;
    private Date createDateEnd;
    private Date updateDate;
    private Date updateDateStart;
    private Date updateDateEnd;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(id)) {
            CriteriaBuilder.In<Object> idIn = crieriaBuilder.in(root.get("id"));
            for (String s : id.split(",")) {
                idIn.value(s);
            }
            predicates.add(idIn);
        }
        if (!StringUtils.isEmpty(createUser)) {
            CriteriaBuilder.In<Object> createUserIn = crieriaBuilder.in(root.get("createUser"));
            for (String createUserOne : createUser.split(",")) {
                createUserIn.value(createUserOne);
            }
            predicates.add(createUserIn);
        }
        if (!StringUtils.isEmpty(updateUser)) {
            CriteriaBuilder.In<Object> updateUserIn = crieriaBuilder.in(root.get("updateUser"));
            for (String updateUserOne : updateUser.split(",")) {
                updateUserIn.value(updateUserOne);
            }
            predicates.add(updateUserIn);
        }
        if (!StringUtils.isEmpty(createDate)) {
            predicates.add(crieriaBuilder.equal(root.get("createDate"), createDate));
        }
        if (!StringUtils.isEmpty(createDateStart)) {
            predicates.add(crieriaBuilder.greaterThanOrEqualTo(root.get("createDate"), createDateStart));
        }
        if (!StringUtils.isEmpty(createDateEnd)) {
            predicates.add(crieriaBuilder.lessThanOrEqualTo(root.get("createDate"), createDateEnd));
        }

        if (!StringUtils.isEmpty(updateDate)) {
            predicates.add(crieriaBuilder.equal(root.get("updateDate"), updateDate));
        }
        if (!StringUtils.isEmpty(updateDateStart)) {
            predicates.add(crieriaBuilder.greaterThanOrEqualTo(root.get("updateDate"), updateDateStart));
        }
        if (!StringUtils.isEmpty(updateDateEnd)) {
            predicates.add(crieriaBuilder.lessThanOrEqualTo(root.get("updateDate"), updateDateEnd));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }
}
