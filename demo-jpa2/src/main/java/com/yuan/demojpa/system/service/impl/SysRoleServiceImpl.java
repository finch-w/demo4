package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysRoleDao;
import com.yuan.demojpa.system.dto.SysRoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import com.yuan.demojpa.system.service.SysRoleService;
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
            stringBuilder.append(" and createUser in (select id from sys_user su where su.username like concat('%':createUser '%') or su.name like concat('%', :createUser, '%')) ");
            builder.put("createUser", dto.getCreateUser());
        }
        if (isNotEmpty(dto.getUpdateUser())) {
            stringBuilder.append(" and updateUser in (select id from sys_user su where su.username like concat('%':createUser '%') or su.name like concat('%', :createUser, '%')) ");
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
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    private Specification<SysRole> getDtoSpec(SysRoleDto dto) {
        return (Specification<SysRole>) (root, query, criteriaBuilder) -> null;
    }
}
