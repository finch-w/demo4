package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BaseAttention")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseAttention extends BasePojo {
    private String code;
    private String cname;
    private String ename;
    private Integer enabled;

    @Builder
    public BaseAttention(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String cname, String ename, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.cname = cname;
        this.ename = ename;
        this.enabled = enabled;
    }
}
