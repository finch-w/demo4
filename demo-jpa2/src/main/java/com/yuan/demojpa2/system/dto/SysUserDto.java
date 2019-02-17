package com.yuan.demojpa2.system.dto;

import com.yuan.demojpa2.commons.dto.BaseDto;
import com.yuan.demojpa2.system.pojo.SysUser;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDto extends BaseDto<SysUser> {
    private String name;

    @Builder
    public SysUserDto(Integer page, Integer size, String order, String id, String createUser, String updateUser, Date createDate, Date createDateStart, Date createDateEnd, Date updateDate, Date updateDateStart, Date updateDateEnd, String name) {
        super(page, size, order, id, createUser, updateUser, createDate, createDateStart, createDateEnd, updateDate, updateDateStart, updateDateEnd);
        this.name = name;
    }

    @SuppressWarnings("Duplicates")
    public static MapQuery checkInsert(SysUser user) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sql.append("select count(*) from sys_user su where 1 = 1 ");
        if (!StringUtils.isEmpty(user.getUsername())) {
            sql.append(" or su.username = :username ");
            map.put("username", user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getName())) {
            sql.append(" or su.name = :name");
            map.put("name", user.getName());
        }
        return new MapQuery(sql.toString(), map);
    }


    @SuppressWarnings({"ToArrayCallWithZeroLengthArrayArgument", "Duplicates"})
    @Override
    public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder crieriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(super.toPredicate(root, query, crieriaBuilder));
        if (!StringUtils.isEmpty(name)) {
            Predicate or = crieriaBuilder.or(crieriaBuilder.like(root.get("username"), "%" + name + "%"), crieriaBuilder.like(root.get("name"), "%" + name + "%"));
            predicates.add(or);

        }
        return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
    }

    @SuppressWarnings("Duplicates")
    public MapQuery baseConditionQuery() {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sql.append("select id,date_format(createDate,'%Y-%m-%d') as createDate,date_format(updateDate,'%Y-%m-%d') as updateDate,username,name,case is_del when 0 then '有效' when 1 then '失效' end as is_del from sys_user su where 1=1 ");
        if (!StringUtils.isEmpty(name)) {
            sql.append(" and (su.username like concat('%', :name, '%') or su.name like concat('%', :name, '%')) ");
            map.put("name", name);
        }
        if (!StringUtils.isEmpty(getCreateUser())) {
            sql.append(" and createUser in (select id from sys_user s where s.username = :createUser or s.name = :createUser) ");
            map.put("createUser", getCreateUser());
        }
        if (!StringUtils.isEmpty(getUpdateUser())) {
            sql.append(" and updateUser in (select id from sys_user u where u.username = :updateUser or u.name = :updateUser) ");
            map.put("updateUser", getUpdateUser());
        }
        if (!StringUtils.isEmpty(getCreateDate())) {
            sql.append(" and date(createDate) = date(:createDate) ");
            map.put("createDate", getCreateDate());
        }
        if (!StringUtils.isEmpty(getCreateDateStart())) {
            sql.append(" and date(createDate) >= date(:createDateStart) ");
            map.put("createDateStart", getCreateDateStart());
        }
        if (!StringUtils.isEmpty(getCreateDateEnd())) {
            sql.append(" and date(createDate) <= date(:createDateEnd) ");
            map.put("createDateEnd", getCreateDateEnd());
        }
        if (!StringUtils.isEmpty(getUpdateDate())) {
            sql.append(" and date(updateDate) = date(:updateDate) ");
            map.put("updateDate", getUpdateDate());
        }
        if (!StringUtils.isEmpty(getUpdateDateStart())) {
            sql.append(" and date(updateDate) >= date(:updateDateStart) ");
            map.put("updateDateStart", getUpdateDateStart());
        }
        if (!StringUtils.isEmpty(getUpdateDateEnd())) {
            sql.append(" and date(updateDate) <= date(:updateDateEnd) ");
            map.put("updateDateEnd", getUpdateDateEnd());
        }
        return new MapQuery(sql.toString(), map);
    }


}
