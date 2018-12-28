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
public class BaseAirport extends BasePojo {
    private String cnameFull;
    private String cnameShort;
    private String enameFull;
    private String cityId;
    private String codeThree;
    private Integer enabled;

    @Builder
    public BaseAirport(String id, String createUser, Date createDate, String updateUser, Date updateDate, String cnameFull, String cnameShort, String enameFull, String cityId, String codeThree, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.cnameFull = cnameFull;
        this.cnameShort = cnameShort;
        this.enameFull = enameFull;
        this.cityId = cityId;
        this.codeThree = codeThree;
        this.enabled = enabled;
    }
}
