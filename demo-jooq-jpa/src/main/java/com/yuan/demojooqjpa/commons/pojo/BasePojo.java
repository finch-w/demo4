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
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    @Column(insertable = true, updatable = false)
    private Long createUser ;
    @Column(insertable = false, updatable = true)
    private Long updateUser ;
    @Column(insertable = true, updatable = false)
    private Date createDate = new Date();
    @Column(updatable = true, insertable = false)
    private Date updateDate = new Date();
}
