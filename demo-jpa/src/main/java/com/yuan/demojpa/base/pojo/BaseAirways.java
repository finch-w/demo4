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
public class BaseAirways extends BasePojo {
    private String cname;
    private String cnameShort;
    private String ename;
    private String codeThree;
    private String codeTwo;
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
