package com.yuan.demojpa.system.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.DSLQuery;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysUserDao;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysAuthority;
import com.yuan.demojpa.system.pojo.SysRole;
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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserDao> implements SysUserService, UserDetailsService {
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
            if (isNotEmpty(dto.getName())) {
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%");
                Predicate username = criteriaBuilder.like(root.get("username"), "%" + dto.getName() + "%");
                predicates.add(criteriaBuilder.or(name, username));
            }

            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    private Query getDtoJPQLQuery(SysUserDto dto) {
        StringBuilder stringBuilder = new StringBuilder();
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        stringBuilder.append("select su from SysUser su where 1=1 ");
        if (isNotEmpty(dto.getName())) {
            stringBuilder.append(" and (su.username like concat('%',:name,'%') or su.name like concat('%',:name,'%') ");
            builder.put("name", dto.getName());
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
        stringBuilder.append(" order by ").append(dto.getOrder()).append(" desc ");
        return new MapQuery(stringBuilder.toString(), builder.build());
    }

    @SuppressWarnings("Duplicates")
    private Query getDtoDSLQuery(SysUserDto dto) {
        SelectWhereStep<Record> sys_user = DSL.selectFrom(DSL.table("sys_user"));
        ImmutableList.Builder<Condition> conditions = ImmutableList.builder();
        if (isNotEmpty(dto.getName())) {
            conditions.add(DSL.or(DSL.field("username").contains(dto.getName()), DSL.field("name").contains(dto.getName())));
        }
        return new DSLQuery(sys_user.where(conditions.build()).orderBy(DSL.field(dto.getOrder()).desc()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.findOneBySQL("select * from sys_user su where su.username = ?", username).orElse(null);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            StringBuilder authorises = new StringBuilder();
            List<SysRole> roles = sysUserDao.findAllBySQL("select * from sys_role sr where sr.id in (select roleId from sys_user_role sur where sur.userId = ?)", SysRole.class, sysUser.getId());
            roles.iterator().forEachRemaining(role -> authorises.append(role.getName()).append(","));
            List<SysAuthority> sysAuthorities = sysUserDao.findAllBySQL("select * from sys_authority sa where sa.id in (select authorizeId from sys_role_authorize sra where sra.roleId in (?))", SysAuthority.class, (Object[]) authorises.toString().split(","));
            sysAuthorities.iterator().forEachRemaining(authority -> authorises.append(authority.getName()).append(","));
            sysUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(authorises.toString()));
            return sysUser;
        }
    }
}
