package com.yuan.demomybatis.commons.pojo;

import com.yuan.demomybatis.commons.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePojo {
    @Id
    @KeySql(genId = IdGenerator.class)
    private String id;
    @Column(updatable = false)
    private String createUser;
    @Column(insertable = false)
    private String updateUser;
    @Column(updatable = false)
    private Date createDate = new Date();
    @Column(insertable = false)
    private Date updateDate = new Date();
}
