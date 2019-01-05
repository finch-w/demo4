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
public class BaseFlightLimtType extends BasePojo {
    private String flightNo;
    private String type;
    private Date startDate;
    private Date endDate;

    @Builder
    public BaseFlightLimtType(String id, String createUser, String updateUser, Date createDate, Date updateDate, String flightNo, String type, Date startDate, Date endDate) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.flightNo = flightNo;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
