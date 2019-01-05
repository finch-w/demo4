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
public class BaseAirport extends BasePojo {
    private String cname;
    @Column(name = "cnameShort")
    private String cnameShort;
    private String ename;
    @Column(name = "codeThree")
    private String codeThree;
    private String city;
    private Integer enabled;

    @Builder
    public BaseAirport(String id, String createUser, String updateUser, Date createDate, Date updateDate, String cname, String cnameShort, String ename, String codeThree, String city, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.cname = cname;
        this.cnameShort = cnameShort;
        this.ename = ename;
        this.codeThree = codeThree;
        this.city = city;
        this.enabled = enabled;
    }
}
