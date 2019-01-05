package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baseSpeicalType")
public class BaseSpeicalType extends BasePojo {
    private String code;
    private String canme;
    private String ename;
    @Column(name = "cnameShort")
    private String cnameShort;
    private String parent;
    private Integer enabled;


    @Builder
    public BaseSpeicalType(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String canme, String ename, String cnameShort, String parent, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.canme = canme;
        this.ename = ename;
        this.cnameShort = cnameShort;
        this.parent = parent;
        this.enabled = enabled;
    }
}
