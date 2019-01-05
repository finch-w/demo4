package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaseDangerType extends BasePojo {
    private String code;
    private String cname;
    @Column(name = "cnameShort")
    private String cnameShort;
    private String parent;
    private Integer enabled;

    @Builder
    public BaseDangerType(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String cname, String cnameShort, String parent, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.cname = cname;
        this.cnameShort = cnameShort;
        this.parent = parent;
        this.enabled = enabled;
    }
}
