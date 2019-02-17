package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.DSLQuery;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysRoleDao;
import com.yuan.demojpa.system.dto.SysRoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import com.yuan.demojpa.system.service.SysRoleService;
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

import java.util.List;
import java.util.Optional;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String, SysRoleDao> implements SysRoleService {
    private final SysRoleDao sysRoleDao;

    @Autowired
    public SysRoleServiceImpl(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    @Override
    public SysRoleDao getBaseRepository() {
        return sysRoleDao;
    }

    @Override
    public Page<SysRole> selectPage(SysRoleDto dto) {
        return sysRoleDao.findAll(getDtoSpec(dto), PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc(dto.getOrder()))));
    }

    @Override
    public List<SysRole> selectList(SysRoleDto dto) {
        return sysRoleDao.findAll(getDtoSpec(dto), Sort.by(Sort.Order.desc(dto.getOrder())));
    }

    @Override
    public Optional<SysRole> selectOne(SysRoleDto dto) {
        return sysRoleDao.findOne(getDtoSpec(dto));
    }

    @Override
    public Page<SysRole> selectPageSQL(SysRoleDto dto) {
        return sysRoleDao.findAllBySQLQuery(getDtoSQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysRole> selectListSQL(SysRoleDto dto) {
        return sysRoleDao.findAllBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysRole> selectOneSQL(SysRoleDto dto) {
        return sysRoleDao.findOneBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Page<SysRole> selectPageJPQL(SysRoleDto dto) {
        return sysRoleDao.findAllByJPQLQuery(getDtoJPQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysRole> selectListJPQL(SysRoleDto dto) {
        return sysRoleDao.findAllByJPQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysRole> selectOneJPQL(SysRoleDto dto) {
        return sysRoleDao.findOneByJPQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Page<SysRole> selectPageDSL(SysRoleDto dto) {
        return sysRoleDao.findAllBySQLQuery(getDtoDSLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysRole> selectListDSL(SysRoleDto dto) {
        return sysRoleDao.findAllBySQLQuery(getDtoDSLQuery(dto));
    }

    @Override
    public Optional<SysRole> selectOneDSL(SysRoleDto dto) {
        return sysRoleDao.findOneBySQLQuery(getDtoDSLQuery(dto));
    }

    private Specification<SysRole> getDtoSpec(SysRoleDto dto) {
        return (Specification<SysRole>) (root, query, criteriaBuilder) -> null;
    }

    private Query getDtoJPQLQuery(SysRoleDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> immutableMap = ImmutableMap.builder();
        stringBuilder.append("select sr from SysRole sr where 1=1 ");
        if (isNotEmpty(dto.getId())) {
            stringBuilder.append(" and sr.id in (:id) ");
            immutableMap.put("id", dto.getId().split(","));
        }
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and sr.name like concat('%',:name,'%')");
            immutableMap.put("name", dto.getName());
        }
        if (isNotEmpty(dto.getCreateUser())) {
            stringBuilder.append(" and sr.createUser = :createUser ");
            immutableMap.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and sr.updateUser = :updateUser");
            immutableMap.put("updateUser", dto.getUpdateUser());
        }
        if (isNotEmpty(dto.getCreateDate())) {
            stringBuilder.append(" and date(sr.createDate) = date(:createDate) ");
            immutableMap.put("createDate", dto.getCreateDate());
        }
        if (isNotEmpty(dto.getCreateDateStart())) {
            stringBuilder.append(" and date(sr.createDate) >= date(:createDateStart) ");
            immutableMap.put("createDateStart", dto.getCreateDateStart());
        }
        if (isNotEmpty(dto.getCreateDateEnd())) {
            stringBuilder.append(" and date(sr.createDateEnd) <= date(:createDateEnd) ");
            immutableMap.put("createDateEnd", dto.getCreateDateEnd());
        }
        if (isNotEmpty(dto.getUpdateDate())) {
            stringBuilder.append(" and date(sr.updateDate) = date(:updateDate) ");
            immutableMap.put("updateDate", dto.getUpdateDate());
        }
        if (isNotEmpty(dto.getUpdateDateStart())) {
            stringBuilder.append(" and date(sr.updateDate) >= date(:updateDateStart) ");
            immutableMap.put("updateDateStart", dto.getUpdateDateStart());
        }
        if (isNotEmpty(dto.getUpdateDateEnd())) {
            stringBuilder.append(" and date(sr.updateDate) <= date(:getUpdateDateEnd) ");
            immutableMap.put("updateDateEnd", dto.getUpdateDateEnd());
        }
        stringBuilder.append(" order by sr.").append(dto.getOrder()).append(" desc");
        return new MapQuery(stringBuilder.toString(), immutableMap.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoSQLQuery(SysRoleDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append(" select id, createDate, updateDate, createUser, updateUser, name from sys_role sr where 1 = 1 ");
        if (isNotEmpty(dto.getId())) {
            stringBuilder.append(" and id in (:id) ");
            builder.put("id", dto.getId().split(","));
        }
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and name like concat('%', :name, '%') ");
            builder.put("name", dto.getName());
        }
        if (isNotEmpty(dto.getCreateUser())) {
            stringBuilder.append(" and createUser = :createUser ");
            builder.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and updateUser = :updateUser ");
            builder.put("updateUser", dto.getUpdateUser());
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
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoDSLQuery(SysRoleDto dto) {
        SelectWhereStep<Record> sys_role = DSL.selectFrom(DSL.table("sys_role"));
        ImmutableList.Builder<Condition> conditions = ImmutableList.builder();
        if (isNotEmpty(dto.getId())) {
            conditions.add(DSL.field("id").in(dto.getId().split(",")));
        }
        if (isNotEmpty(dto.getName())) {
            conditions.add(DSL.field("name").contains(dto.getName()));
        }
        if (isNotEmpty(dto.getCreateUser())) {
            conditions.add(DSL.field("createUser").eq(dto.getCreateUser()));
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            conditions.add(DSL.field("updateUser").eq(dto.getUpdateUser()));
        }
        if (isNotEmpty(dto.getCreateDate())) {
            conditions.add(DSL.field("createDate").eq(DSL.date(dto.getCreateDate())));
        }
        if (isNotEmpty(dto.getCreateDateStart())) {
            conditions.add(DSL.field("createDate").ge(DSL.date(dto.getCreateDateStart())));
        }
        if (isNotEmpty(dto.getCreateDateEnd())) {
            conditions.add(DSL.field("createDate").le(DSL.date(dto.getUpdateDateEnd())));
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
        return new DSLQuery(sys_role.where(conditions.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }

}
