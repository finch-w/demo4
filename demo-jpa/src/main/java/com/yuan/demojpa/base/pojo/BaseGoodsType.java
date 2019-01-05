package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "baseGoodsType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseGoodsType extends BasePojo {
    private String code;
    private String cname;
    private String ename;
    private String parent;
    private String airways;
    @Column(name = "isSpecial")
    private Integer isSpecial;
    private String special;
    @Column(name = "isDanger")
    private Integer isDanger;
    private String danger;

    @Builder
    public BaseGoodsType(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String cname, String ename, String parent, String airways, Integer isSpecial, String special, Integer isDanger, String danger) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.cname = cname;
        this.ename = ename;
        this.parent = parent;
        this.airways = airways;
        this.isSpecial = isSpecial;
        this.special = special;
        this.isDanger = isDanger;
        this.danger = danger;
    }
}
