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
public class BaseFlightCabin extends BasePojo {
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
