package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDangerType extends BasePojo {
    private String cname;
    private String ename;
    private String parent;
    private Integer enabled;

    @Builder
    public BaseDangerType(String id, String createUser, Date createDate, String updateUser, Date updateDate, String cname, String ename, String parent, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.cname = cname;
        this.ename = ename;
        this.parent = parent;
        this.enabled = enabled;
    }
}
