package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baseFlightLimtType")
public class BaseFlightLimtType extends BasePojo {
    private String flightNo;
    private String type;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
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
