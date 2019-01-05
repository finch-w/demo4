package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baseFlightCabin")
public class BaseFlightCabin extends BasePojo {
    @Column(name = "flightNo")
    private String flightNo;
    private Date startDate;
    private Date endDate;

    @Builder
    public BaseFlightCabin(String id, String createUser, String updateUser, Date createDate, Date updateDate, String flightNo, Date startDate, Date endDate) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.flightNo = flightNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
