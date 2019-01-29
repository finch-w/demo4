package com.yuan.demoquerydsljpa2.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePojo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    @Column(name = "createUser", updatable = false)
    private String createUser;
    @Column(name = "updateUser", insertable = false)
    private String updateUser;
    @Column(name = "createDate", updatable = false)
    private Date createDate = new Date();
    @Column(name = "updateDate", insertable = false)
    private Date updateDate = new Date();
}
