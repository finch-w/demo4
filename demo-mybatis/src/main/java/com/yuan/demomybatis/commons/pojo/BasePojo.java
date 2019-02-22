package com.yuan.demomybatis.commons.pojo;

import com.yuan.demomybatis.commons.utils.KeyGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BasePojo {
    @Id
    @KeySql(genId = KeyGenerator.class)
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name = "createDate", updatable = false)
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "updateDate", insertable = false)
    private Date updateDate;
    @Column(name = "createUser", updatable = false)
    private String createUser;
    @Column(name = "updateUser", insertable = false)
    private String updateUser;
}
