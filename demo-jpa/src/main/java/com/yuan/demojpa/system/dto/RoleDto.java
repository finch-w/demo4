package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import com.yuan.demojpa.system.pojo.SysRole;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto<SysRole> {
    private String name;

    @Builder
    public RoleDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    @Override
    public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(super.toPredicate(root, query, crieriaBuilder));
        if (!StringUtils.isEmpty(name)) {
            predicates.add(crieriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }
}
