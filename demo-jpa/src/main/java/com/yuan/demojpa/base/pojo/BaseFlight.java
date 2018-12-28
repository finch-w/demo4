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
public class BaseFlight extends BasePojo {
    private String flightNo;
    private String airwaysId;
    private String startAirportId;
    private String arriveAirportId;
    private String transitAirportId;
    private Date startTime;
    private Date arriveTime;
    private Date transitArriveTime;
    private Date transitStartTime;
    private Integer d0;
    private Integer d1;
    private Integer d2;
    private Integer d3;
    private Integer d4;
    private Integer d5;
    private Integer d6;

    @Builder
    public BaseFlight(String id, String createUser, Date createDate, String updateUser, Date updateDate, String flightNo, String airwaysId, String startAirportId, String arriveAirportId, String transitAirportId, Date startTime, Date arriveTime, Date transitArriveTime, Date transitStartTime, Integer d0, Integer d1, Integer d2, Integer d3, Integer d4, Integer d5, Integer d6) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.flightNo = flightNo;
        this.airwaysId = airwaysId;
        this.startAirportId = startAirportId;
        this.arriveAirportId = arriveAirportId;
        this.transitAirportId = transitAirportId;
        this.startTime = startTime;
        this.arriveTime = arriveTime;
        this.transitArriveTime = transitArriveTime;
        this.transitStartTime = transitStartTime;
        this.d0 = d0;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
    }
}
