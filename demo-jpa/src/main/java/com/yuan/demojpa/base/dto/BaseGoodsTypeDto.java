package com.yuan.demojpa.base.dto;

import com.yuan.demojpa.base.pojo.BaseGoodsType;
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

@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseGoodsTypeDto extends BaseDto<BaseGoodsType> {
    private String code;
    private String name;
    private Integer isSpecial;
    private Integer isDanger;
    private String airways;

    @Builder
    public BaseGoodsTypeDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String code, String name, Integer isSpecial, Integer isDanger, String airways) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.code = code;
        this.name = name;
        this.isSpecial = isSpecial;
        this.isDanger = isDanger;
        this.airways = airways;
    }

    @Override
    public Predicate toPredicate(Root<BaseGoodsType> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(super.toPredicate(root, query, crieriaBuilder));
        if (!StringUtils.isEmpty(code)) {
            predicates.add(crieriaBuilder.like(root.get("code"), code + "%"));
        }
        if (!StringUtils.isEmpty(name)) {
            Predicate cname = crieriaBuilder.like(root.get("cname"), name + "%");
            Predicate ename = crieriaBuilder.like(root.get("ename"), name + "%");
            Predicate predicate = crieriaBuilder.or(cname, ename);
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(isDanger)) {
            predicates.add(crieriaBuilder.equal(root.get("isDanger"), isDanger));
        }
        if (!StringUtils.isEmpty(isSpecial)) {
            predicates.add(crieriaBuilder.equal(root.get("isSpecial"), isSpecial));
        }
        if (!StringUtils.isEmpty(airways)) {
            predicates.add(crieriaBuilder.like(root.get("airways"), "%" + airways + "%"));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }
}
