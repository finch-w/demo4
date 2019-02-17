package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.DSLQuery;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysUserDao;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.SysUserService;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectWhereStep;
import org.jooq.impl.DSL;
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
    public Page<SysUser> selectPageJPQL(SysUserDto dto) {
        return sysUserDao.findAllByJPQLQuery(getDtoJPQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysUser> selectListJPQL(SysUserDto dto) {
        return sysUserDao.findAllByJPQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysUser> selectOneJPQL(SysUserDto dto) {
        return sysUserDao.findOneBySQLQuery(getDtoSQLQuery(dto));
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
    public Page<SysUser> selectPageDSL(SysUserDto dto) {
        return sysUserDao.findAllBySQLQuery(getDtoDSLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysUser> selectListDSL(SysUserDto dto) {
        return sysUserDao.findAllBySQLQuery(getDtoDSLQuery(dto));
    }

    @Override
    public Optional<SysUser> selectOneDSL(SysUserDto dto) {
        return sysUserDao.findOneBySQLQuery(getDtoDSLQuery(dto));
    }

    @Override
    public boolean checkInsert(SysUser user) {
        return sysUserDao.findOneBySQLQuery(getCheckInsertQuery(user), Long.class).map(aLong -> aLong > 0).orElse(false);
    }

    private Query getCheckInsertQuery(SysUser user) {
        String sql = "select count(*) from sys_user su where 1 = 1 and su.username = :username";
        return new MapQuery(sql, ImmutableMap.of("username", user.getUsername()));
    }

    @SuppressWarnings({"ToArrayCallWithZeroLengthArrayArgument", "MismatchedQueryAndUpdateOfCollection"})
    private Specification<SysUser> getDtoSpecification(SysUserDto dto) {
        return (Specification<SysUser>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    private Query getDtoJPQLQuery(SysUserDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append("select su from SysUser su where 1=1 ");
        if (isNotEmpty(dto.getId())) {
            stringBuilder.append(" and su.id in (:id) ");
            builder.put("id", dto.getId().split(","));
        }
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and (su.username like concat('%',:name,'%') or su.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
        }
        if (isNotEmpty(dto.getCreateUser())) {
            stringBuilder.append(" and su.createUser in (select su.id from SysUser su where su.username like concat('%',:createUser,'%'))");
            builder.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and su.updateUser in (select su.id from SysUser su where su.username like concat('%',:createUser,'%'))");
            builder.put("updateUser", dto.getUpdateUser());
        }
        if (isNotEmpty(dto.getCreateDate())) {
            stringBuilder.append(" and date(su.createDate) = date(:createDate) ");
            builder.put("createDate", dto.getCreateDate());
        }
        if (isNotEmpty(dto.getCreateDateStart())) {
            stringBuilder.append(" and date(su.createDate) >= date(:createDateStart) ");
            builder.put("createDateStart", dto.getCreateDateStart());
        }
        if (isNotEmpty(dto.getCreateDateEnd())) {
            stringBuilder.append(" and date(su.createDate) <= date(:createDateEnd) ");
            builder.put("createDateEnd", dto.getCreateDateEnd());
        }
        if (isNotEmpty(dto.getUpdateDate())) {
            stringBuilder.append(" and date(su.updateDate) = date(:updateDate) ");
            builder.put("updateDate", dto.getUpdateDate());
        }
        if (isNotEmpty(dto.getUpdateDateStart())) {
            stringBuilder.append(" and date(su.updateDate) >= date(:updateDateStart) ");
            builder.put("updateDateStart", dto.getUpdateDateStart());
        }
        if (isNotEmpty(dto.getUpdateDateEnd())) {
            stringBuilder.append(" and date(su.updateDate) <= date(:updateDateEnd) ");
            builder.put("updateDateEnd", dto.getCreateDateEnd());
        }
        stringBuilder.append(" order by su.").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilder.toString());
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
            stringBuilder.append(" and createUser = :createUser ");
            builder.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and updateUser = :updateUser ");
            builder.put("updateUser", dto.getUpdateUser());
        }
        if (isNotEmpty(dto.getStatus())) {
            stringBuilder.append(" and is_del = :status ");
            builder.put("status", dto.getStatus());
        }
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    private Query getDtoDSLQuery(SysUserDto dto) {
        SelectWhereStep<Record> sys_user = DSL.selectFrom(DSL.table("sys_user"));
        ImmutableList.Builder<Condition> conditions = ImmutableList.builder();
        if (isNotEmpty(dto.getId())) {
            conditions.add(DSL.field("id").in(dto.getId().split(",")));
        }
        if (isNotEmpty(dto.getName())) {
            conditions.add(DSL.or(DSL.field("username").contains(dto.getName()), DSL.field("name").contains(dto.getName())));
        }
        if (isNotEmpty(dto.getCreateDate())) {
            conditions.add(DSL.field("createDate").eq(DSL.date(dto.getCreateDate())));
        }
        if (isNotEmpty(dto.getCreateDateStart())) {
            conditions.add(DSL.field("createDate").ge(DSL.date(dto.getCreateDateStart())));
        }
        if (isNotEmpty(dto.getCreateDateEnd())) {
            conditions.add(DSL.field("createDate").le(DSL.date(dto.getCreateDateEnd())));
        }
        if (isNotEmpty(dto.getUpdateDate())) {
            conditions.add(DSL.field("updateDate").eq(DSL.date(dto.getUpdateDate())));
        }
        if (isNotEmpty(dto.getUpdateDateStart())) {
            conditions.add(DSL.field("updateDate").ge(DSL.date(dto.getUpdateDateStart())));
        }
        if (isNotEmpty(dto.getUpdateDateEnd())) {
            conditions.add(DSL.field("updateDate").le(DSL.date(dto.getUpdateDateEnd())));
        }
        if (isNotEmpty(dto.getCreateUser())) {
            conditions.add(DSL.field("createUser").eq(dto.getCreateUser()));
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            conditions.add(DSL.field("updateUser").eq(dto.getUpdateUser()));
        }
        return new DSLQuery(sys_user.where(conditions.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }
}
