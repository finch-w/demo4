package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomer extends BasePojo {
    private String cnameFull;
    private String cnameShort;
    private String enameFull;

    @Builder
    public BaseCustomer(String id, String createUser, Date createDate, String updateUser, Date updateDate, String cnameFull, String cnameShort, String enameFull) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.cnameFull = cnameFull;
        this.cnameShort = cnameShort;
        this.enameFull = enameFull;
    }
}
