package com.yuan.demojpa.commons.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePojo {
    @Id
    @GeneratedValue(generator = "EMP_ID_GEN")
    @UuidGenerator(name = "EMP_ID_GEN")
    private String id;
    @Column(updatable = false)
    private String createUser;
    @Column(updatable = false)
    private Date createDate = new Date();
    @Column(insertable = false)
    private String updateUser;
    @Column(insertable = false)
    private Date updateDate = new Date();
}
