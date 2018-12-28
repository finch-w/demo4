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
public class BaseFlightLimitType extends BasePojo {
    private String flightNo;
    private String airwaysId;
    private String goodsTypeId;
    private Date startDate;
    private Date endDate;

    @Builder
    public BaseFlightLimitType(String id, String createUser, Date createDate, String updateUser, Date updateDate, String flightNo, String airwaysId, String goodsTypeId, Date startDate, Date endDate) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.flightNo = flightNo;
        this.airwaysId = airwaysId;
        this.goodsTypeId = goodsTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
