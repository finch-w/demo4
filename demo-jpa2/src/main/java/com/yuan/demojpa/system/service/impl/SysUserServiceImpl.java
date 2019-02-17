package com.yuan.demojpa.system.service.impl;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<SysUser> selectList(SysUserDto dto) {
        return sysUserDao.findAll(getDtoSpecification(dto), Sort.by(Sort.Order.desc(dto.getOrder())));
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
    public boolean checkInsert(SysUser user) {
        return sysUserDao.findOneBySQLQuery(getCheckInsertQuery(user), Long.class).map(aLong -> aLong > 0).orElse(false);
    }

    private Specification<SysUser> getDtoSpecification(SysUserDto dto) {
        return (Specification<SysUser>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotEmpty(dto.getName())){
                predicates.add(criteriaBuilder.like(root.get()))
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    private Query getCheckInsertQuery(SysUser user) {
        return null;
    }

    private Query getDtoSQLQuery(SysUserDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        return new MapQuery(stringBuilder.toString(), map);
    }
}
