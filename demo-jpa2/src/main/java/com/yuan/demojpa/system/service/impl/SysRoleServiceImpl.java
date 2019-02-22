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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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


    @Override
    public boolean checkInsert(SysRole role) {
        return sysRoleDao.count(getCheckInsertSpec(role)) > 0;
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    private Specification<SysRole> getCheckInsertSpec(SysRole role) {
        return (Specification<SysRole>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(role.getName())) {
                predicates.add(criteriaBuilder.equal(root.get("name"), role.getName()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    @SuppressWarnings({"ToArrayCallWithZeroLengthArrayArgument", "Duplicates"})
    private Specification<SysRole> getDtoSpec(SysRoleDto dto) {
        return (Specification<SysRole>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(dto.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    private Query getDtoJPQLQuery(SysRoleDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> immutableMap = ImmutableMap.builder();
        stringBuilder.append("select sr from SysRole sr where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and sr.name like concat('%',:name,'%')");
            immutableMap.put("name", dto.getName());
        }
        stringBuilder.append(" order by sr.").append(dto.getOrder()).append(" desc");
        return new MapQuery(stringBuilder.toString(), immutableMap.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoSQLQuery(SysRoleDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append(" select id, createDate, updateDate, createUser, updateUser, name from sys_role sr where 1 = 1 ");
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoDSLQuery(SysRoleDto dto) {
        SelectWhereStep<Record> sys_role = DSL.selectFrom(DSL.table("sys_role"));
        ImmutableList.Builder<Condition> conditions = ImmutableList.builder();
        if (isNotEmpty(dto.getName())) {
            conditions.add(DSL.field("name").contains(dto.getName()));
        }
        return new DSLQuery(sys_role.where(conditions.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }

}
