package com.yuan.demojooqjpa.commons.pojo;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BasePojo implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
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
