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
public class BaseGoodsType extends BasePojo {
    private String cname;
    private String ename;
    private String parent;
    private Integer isSpecial;
    private String specialId;
    private Integer isDanger;
    private String dangerId;
    private String airwaysId;

    @Builder
    public BaseGoodsType(String id, String createUser, Date createDate, String updateUser, Date updateDate, String cname, String ename, String parent, Integer isSpecial, String specialId, Integer isDanger, String dangerId, String airwaysId) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.cname = cname;
        this.ename = ename;
        this.parent = parent;
        this.isSpecial = isSpecial;
        this.specialId = specialId;
        this.isDanger = isDanger;
        this.dangerId = dangerId;
        this.airwaysId = airwaysId;
    }
}
