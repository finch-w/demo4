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
@Table(name = "baseAirways")
public class BaseAirways extends BasePojo {
    private String cname;
    @Column(name = "cnameShort")
    private String cnameShort;
    @Column(name = "ename")
    private String ename;
    @Column(name = "codeThree")
    private String codeThree;
    @Column(name = "codeTwo")
    private String codeTwo;
    @Column(name = "enabled")
    private Integer enabled;

    @Builder
    public BaseAirways(String id, String createUser, String updateUser, Date createDate, Date updateDate, String cname, String cnameShort, String ename, String codeThree, String codeTwo, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.cname = cname;
        this.cnameShort = cnameShort;
        this.ename = ename;
        this.codeThree = codeThree;
        this.codeTwo = codeTwo;
        this.enabled = enabled;
    }
}
