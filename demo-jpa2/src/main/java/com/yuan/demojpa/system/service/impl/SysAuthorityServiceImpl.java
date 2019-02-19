package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.DSLQuery;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysAuthorityDao;
import com.yuan.demojpa.system.dto.SysAuthorityDto;
import com.yuan.demojpa.system.pojo.SysAuthority;
import com.yuan.demojpa.system.service.SysAuthorityService;
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
public class SysAuthorityServiceImpl extends BaseServiceImpl<SysAuthority, String, SysAuthorityDao> implements SysAuthorityService {
    private final SysAuthorityDao sysAuthorityDao;

    @Autowired
    public SysAuthorityServiceImpl(SysAuthorityDao sysAuthorityDao) {
        this.sysAuthorityDao = sysAuthorityDao;
    }

    @Override
    public SysAuthorityDao getBaseRepository() {
        return sysAuthorityDao;
    }

    @Override
    public Page<SysAuthority> selectPage(SysAuthorityDto dto) {
        return sysAuthorityDao.findAll(getDtoSpec(dto), PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc(dto.getOrder()))));
    }

    @Override
    public List<SysAuthority> selectList(SysAuthorityDto dto) {
        return sysAuthorityDao.findAll(getDtoSpec(dto), Sort.by(Sort.Order.desc(dto.getOrder())));
    }

    @Override
    public Optional<SysAuthority> selectOne(SysAuthorityDto dto) {
        return sysAuthorityDao.findOne(getDtoSpec(dto));
    }

    @Override
    public Page<SysAuthority> selectPageSQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllBySQLQuery(getDtoSQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysAuthority> selectListSQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Optional<SysAuthority> selectOneSQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findOneBySQLQuery(getDtoSQLQuery(dto));
    }

    @Override
    public Page<SysAuthority> selectPageJPQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllByJPQLQuery(getDtoJPQLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysAuthority> selectListJPQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllByJPQLQuery(getDtoJPQLQuery(dto));
    }

    @Override
    public Optional<SysAuthority> selectOneJPQL(SysAuthorityDto dto) {
        return sysAuthorityDao.findOneByJPQLQuery(getDtoJPQLQuery(dto));
    }

    @Override
    public Page<SysAuthority> selectPageDSL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllByJPQLQuery(getDtoDSLQuery(dto), PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysAuthority> selectListDSL(SysAuthorityDto dto) {
        return sysAuthorityDao.findAllBySQLQuery(getDtoDSLQuery(dto));
    }

    @Override
    public Optional<SysAuthority> selectOneDSL(SysAuthorityDto dto) {
        return sysAuthorityDao.findOneBySQLQuery(getDtoDSLQuery(dto));
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    private Specification<SysAuthority> getDtoSpec(SysAuthorityDto dto) {
        return (Specification<SysAuthority>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(dto.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoJPQLQuery(SysAuthorityDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append(" select sa from SysAuthority sa where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and sa.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
        }
        stringBuilder.append(" order by sa.").append(dto.getOrder()).append(" desc  ");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoSQLQuery(SysAuthorityDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append(" select id, createDate, updateDate, createUser, updateUser, name from sys_authority sa where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and sa.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
        }
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc  ");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    private Query getDtoDSLQuery(SysAuthorityDto dto) {
        SelectWhereStep<Record> sys_authority = DSL.selectFrom(DSL.table("sys_authority"));
        ImmutableList.Builder<Condition> builder = ImmutableList.builder();
        if (isNotEmpty(dto.getName())) {
            builder.add(DSL.field("name").contains(dto.getName()));
        }
        return new DSLQuery(sys_authority.where(builder.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }


}
