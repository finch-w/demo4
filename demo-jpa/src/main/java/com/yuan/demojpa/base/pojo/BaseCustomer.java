package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "BaseCustomer")
@Table(name = "baseCustomer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomer extends BasePojo {
    private String cname;//中文名
    @Column(name = "cnameShort")
    private String cnameShort;//中文名简称
    private String ename;//英文名
    @Column(name = "enameShort")
    private String enameShort;//英文名简称
    private String linkman;//联系人
    @Column(name = "1linkNumber")
    private String linkNumber;//联系电话
    private String type;//客户类型
    private String group;//客户分组
    private String payment;//结算方式
    private String city;//所在城市

    @Builder
    public BaseCustomer(String id, String createUser, String updateUser, Date createDate, Date updateDate, String cname, String cnameShort, String ename, String enameShort, String linkman, String linkNumber, String type, String group, String payment, String city) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.cname = cname;
        this.cnameShort = cnameShort;
        this.ename = ename;
        this.enameShort = enameShort;
        this.linkman = linkman;
        this.linkNumber = linkNumber;
        this.type = type;
        this.group = group;
        this.payment = payment;
        this.city = city;
    }
}
