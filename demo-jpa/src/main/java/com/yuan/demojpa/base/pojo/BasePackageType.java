package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BasePackageType extends BasePojo {
    private String code;
    private String cname;
    private String ename;
    private String parent;
    private Integer enabled;

    @Builder
    public BasePackageType(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String cname, String ename, String parent, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.cname = cname;
        this.ename = ename;
        this.parent = parent;
        this.enabled = enabled;
    }
}
