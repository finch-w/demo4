package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "baseFlightAircraft")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseAircraft extends BasePojo {
    private String code;
    private String codeTwo;
    private String cname;
    private String ename;
    private String type;
    private Integer enabled;

    @Builder
    public BaseAircraft(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String codeTwo, String cname, String ename, String type, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.codeTwo = codeTwo;
        this.cname = cname;
        this.ename = ename;
        this.type = type;
        this.enabled = enabled;
    }
}
