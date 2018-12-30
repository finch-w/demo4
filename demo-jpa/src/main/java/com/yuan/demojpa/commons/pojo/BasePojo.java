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

/**
 * 基础持久类
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePojo {
    @Id
    @GeneratedValue(generator = "EMP_ID_GEN")
    @UuidGenerator(name = "EMP_ID_GEN")
    @Column(name = "id")
    private String id;//主键
    @Column(updatable = false, name = "createUser")
    private String createUser;//创建人主键
    @Column(updatable = false, name = "createDate")
    private Date createDate = new Date();//创建日期
    @Column(insertable = false, name = "updateUser")
    private String updateUser;//修改人主键
    @Column(insertable = false, name = "updateDate")
    private Date updateDate = new Date();//修改日期


}
