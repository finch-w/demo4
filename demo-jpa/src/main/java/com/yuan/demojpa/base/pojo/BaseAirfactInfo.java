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
public class BaseAirfactInfo extends BasePojo {
    private String flightNo;
    private String airfactId;
    private Date startDate;
    private Date endDate;
    private Integer enabled;

    @Builder
    public BaseAirfactInfo(String id, String createUser, Date createDate, String updateUser, Date updateDate, String flightNo, String airfactId, Date startDate, Date endDate, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.flightNo = flightNo;
        this.airfactId = airfactId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enabled = enabled;
    }
}
