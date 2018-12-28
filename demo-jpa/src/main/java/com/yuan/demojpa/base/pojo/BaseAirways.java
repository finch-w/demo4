package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BaseAirways extends BasePojo {
    private String cnameFull;
    private String cnameShort;
    private String ename;
    private String codeTwo;
    private String codeThree;
    private Integer enabled;

    @Builder
    public BaseAirways(String id, String createUser, Date createDate, String updateUser, Date updateDate, String cnameFull, String cnameShort, String ename, String codeTwo, String codeThree, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.cnameFull = cnameFull;
        this.cnameShort = cnameShort;
        this.ename = ename;
        this.codeTwo = codeTwo;
        this.codeThree = codeThree;
        this.enabled = enabled;
    }
}
