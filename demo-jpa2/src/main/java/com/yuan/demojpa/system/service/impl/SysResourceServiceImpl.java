package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.DSLQuery;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysResourceDao;
import com.yuan.demojpa.system.dto.SysResourceDto;
import com.yuan.demojpa.system.pojo.SysResource;
import com.yuan.demojpa.system.service.SysResourceService;
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
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource, String, SysResourceDao> implements SysResourceService {
    private final SysResourceDao sysResourceDao;

    @Autowired
    public SysResourceServiceImpl(SysResourceDao sysResourceDao) {
        this.sysResourceDao = sysResourceDao;
    }

    @Override
    public SysResourceDao getBaseRepository() {
        return sysResourceDao;
    }

    @Override
    public Page<SysResource> selectPage(SysResourceDto dto) {
        return sysResourceDao.findAll(getDtoSpec(dto), PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc(dto.getOrder()))));
    }

    @Override
    public List<SysResource> selectList(SysResourceDto dto) {
        return sysResourceDao.findAll(getDtoSpec(dto), Sort.by(Sort.Order.desc(dto.getOrder())));
    }

    @Override
    public Optional<SysResource> selectOne(SysResourceDto dto) {
        return sysResourceDao.findOne(getDtoSpec(dto));
    }

    @Override
    public Page<SysResource> selectPageJPQL(SysResourceDto dto) {
        return sysResourceDao.findAllByJPQLQuery(getDtoJPQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysResource> selectListJPQL(SysResourceDto dto) {
        return sysResourceDao.findAllByJPQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysResource> selectOneJPQL(SysResourceDto dto) {
        return sysResourceDao.findOneByJPQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Page<SysResource> selectPageSQL(SysResourceDto dto) {
        return sysResourceDao.findAllBySQLQuery(getDtoSQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysResource> selectListSQL(SysResourceDto dto) {
        return sysResourceDao.findAllBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysResource> selectOneSQL(SysResourceDto dto) {
        return sysResourceDao.findOneBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Page<SysResource> selectPageDSL(SysResourceDto dto) {
        return sysResourceDao.findAllBySQLQuery(getDtoDSLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysResource> selectListDSL(SysResourceDto dto) {
        return sysResourceDao.findAllBySQLQuery(getDtoDSLQuery(dto));
    }

    @Override
    public Optional<SysResource> selectOneDSL(SysResourceDto dto) {
        return sysResourceDao.findOneBySQLQuery(getDtoDSLQuery(dto));
    }

    @SuppressWarnings({"ToArrayCallWithZeroLengthArrayArgument", "Duplicates"})
    private Specification<SysResource> getDtoSpec(SysResourceDto dto) {
        return (Specification<SysResource>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(dto.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoJPQLQuery(SysResourceDto dto) {
        StringBuilder stringBuilde = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilde.append(" select sr from sys_resource sr where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilde.append(" and sr.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
        }
        stringBuilde.append(" order by sr.").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilde.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoSQLQuery(SysResourceDto dto) {
        StringBuilder stringBuilde = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilde.append(" select * from sys_resource sr where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilde.append(" and sr.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
        }
        stringBuilde.append(" order by ").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilde.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoDSLQuery(SysResourceDto dto) {
        SelectWhereStep<Record> sys_resource = DSL.selectFrom(DSL.table("sys_resource"));
        ImmutableList.Builder<Condition> builder = ImmutableList.builder();
        if (isNotEmpty(dto.getName())) {
            builder.add(DSL.field("name").contains(dto.getName()));
        }
        return new DSLQuery(sys_resource.where(builder.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }
}
