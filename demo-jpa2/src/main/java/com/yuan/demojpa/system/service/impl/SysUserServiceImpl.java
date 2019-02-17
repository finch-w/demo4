package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysUserDao;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserDao> implements SysUserService {
    private final SysUserDao sysUserDao;

    @Autowired
    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public SysUserDao getBaseRepository() {
        return sysUserDao;
    }

    @Override
    public Page<SysUser> selectPage(SysUserDto dto) {
        return sysUserDao.findAll(getDtoSpecification(dto), PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc(dto.getOrder()))));
    }

    @Override
    public List<SysUser> selectList(SysUserDto dto) {
        return sysUserDao.findAll(getDtoSpecification(dto), Sort.by(Sort.Order.desc(dto.getOrder())));
    }

    @Override
    public Optional<SysUser> selectOne(SysUserDto dto) {
        return sysUserDao.findOne(getDtoSpecification(dto));
    }

    @Override
    public Page<SysUser> selectPageSQL(SysUserDto dto) {
        return sysUserDao.findAllBySQLQuery(getDtoSQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysUser> selectListSQL(SysUserDto dto) {
        return sysUserDao.findAllBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysUser> selectOneSQL(SysUserDto dto) {
        return sysUserDao.findOneBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public boolean checkInsert(SysUser user) {
        return sysUserDao.findOneBySQLQuery(getCheckInsertQuery(user), Long.class).map(aLong -> aLong > 0).orElse(false);
    }

    private Query getCheckInsertQuery(SysUser user) {
        String sql = "select count(*) from sys_user su where 1 = 1 and su.username = :username";
        return new MapQuery(sql, ImmutableMap.of("username", user.getUsername()));
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    private Specification<SysUser> getDtoSpecification(SysUserDto dto) {
        return (Specification<SysUser>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(dto.getName())) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("username"), "%" + dto.getName() + "%"), criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%")));
            }
            if (isNotEmpty(dto.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("isDel"), dto.getStatus()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    private Query getDtoSQLQuery(SysUserDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select id,createDate,updateDate,createUser,updateUser,username,name,password,is_del from sys_user su where 1 = 1 ");
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and (username like concat('%', :name, '%') or name like concat('%', :name, '%')) ");
            builder.put("name", dto.getName());
        }
        if (isNotEmpty(dto.getId())) {
            stringBuilder.append(" and id in (:id) ");
            builder.put("id", dto.getId().split(","));
        }
        if (isNotEmpty(dto.getCreateDate())) {
            stringBuilder.append(" and date(createDate) = date(:createDate) ");
            builder.put("createDate", dto.getCreateDate());
        }
        if (isNotEmpty(dto.getCreateDateStart())) {
            stringBuilder.append(" and date(createDate) >= date(:createDateStart) ");
            builder.put("createDateStart", dto.getCreateDateStart());
        }
        if (isNotEmpty(dto.getCreateDateEnd())) {
            stringBuilder.append(" and date(createDate) <= date(:createDateEnd) ");
            builder.put("createDateEnd", dto.getCreateDateEnd());
        }
        if (isNotEmpty(dto.getUpdateDate())) {
            stringBuilder.append(" and date(updateDate) = date(:updateDate) ");
            builder.put("updateDate", dto.getUpdateDate());
        }
        if (isNotEmpty(dto.getUpdateDateStart())) {
            stringBuilder.append(" and date(updateDate) >= date(:updateDateStart) ");
            builder.put("updateDateStart", dto.getUpdateDateStart());
        }
        if (isNotEmpty(dto.getUpdateDateEnd())) {
            stringBuilder.append(" and date(updateDate) <= date(:updateDateEnd) ");
            builder.put("updateDateEnd", dto.getUpdateDateEnd());
        }
        if (isNotEmpty(dto.getCreateUser())) {
            stringBuilder.append(" and createUser in (select id from sys_user where username like concat('%', :createUser, '%') or name like concat('%', :createUser, '%')) ");
            builder.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and updateUser in (select id from sys_user where username like concat('%', :updateUser, '%') or name like concat('%', :updateUser, '%')) ");
            builder.put("updateUser", dto.getUpdateUser());
        }
        if (isNotEmpty(dto.getStatus())) {
            stringBuilder.append(" and is_del = :status ");
            builder.put("status", dto.getStatus());
        }
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }
}
