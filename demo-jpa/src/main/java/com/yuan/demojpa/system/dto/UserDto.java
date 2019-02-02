package com.yuan.demojpa.system.dto;

import com.yuan.demojpa.commons.dto.BaseDto;
import com.yuan.demojpa.commons.dto.MapQuery;
import com.yuan.demojpa.system.pojo.SysUser;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto<SysUser> {
    private String name;

    @Builder
    public UserDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ToArrayCallWithZeroLengthArrayArgument"})
    @Override
    public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(super.toPredicate(root, query, crieriaBuilder));
        if (!StringUtils.isEmpty(name)) {

            predicates.add(crieriaBuilder.or(crieriaBuilder.like(root.get("username"), "%" + name + "%"), crieriaBuilder.like(root.get("name"), "%" + name + "%")));
        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }

    public MapQuery getDtoSQL() {
        Map<String, Object> map = new HashMap<>();
        StringBuilder sql = new StringBuilder("select id,createDate,updateDate,createUser,updateUser,username,name from sys_user su where 1=1 ");
        if (!StringUtils.isEmpty(getCreateDate())) {
            sql.append(" and date(createDate) = date(:createDate)");
            map.put("createDate", getCreateDate());
        }
        if (!StringUtils.isEmpty(getCreateDateStart())) {
            sql.append(" and date(createDate) >= date(:createDateStart)");
            map.put("createDateStart", getCreateDateStart());
        }
        if (!StringUtils.isEmpty(getCreateDateEnd())) {
            sql.append(" and date(createDate) >= date(:createDateEnd)");
            map.put("createDateEnd", getCreateDateEnd());
        }
        if (!StringUtils.isEmpty(getUpdateDate())) {
            sql.append(" and date(createDate) = date(:updateDate)");
            map.put("updateDate", getUpdateDate());
        }
        if (!StringUtils.isEmpty(getUpdateDateStart())) {
            sql.append(" and date(createDate) >= date(:updateDateStart)");
            map.put("updateDateStart", getUpdateDateStart());
        }
        if (!StringUtils.isEmpty(getCreateDateEnd())) {
            sql.append(" and date(createDate) >= date(:updateDateEnd)");
            map.put("updateDateEnd", getCreateDateEnd());
        }
        if (!StringUtils.isEmpty(getName())) {
            sql.append(" and (username like concat('%',:name,'%') or name like concat('%',:name,'%'))");
            map.put("name", name);
        }
        return new MapQuery(sql.toString(), map);
    }

}
