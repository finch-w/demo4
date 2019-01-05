package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFlight extends BasePojo {
    @Column(name = "flightNo")
    private String flightNo;
    @Column(name = "startAirport")
    private String startAirport;
    @Column(name = "endAirport")
    private String endAirport;
    @Column(name = "transAirport")
    private String transAirport;
    @Column(name = "startTime")
    private Time startTime;
    @Column(name = "endTime")
    private Time endTime;
    @Column(name = "transArriveTime")
    private Time transArriveTime;
    @Column(name = "transStartTime")
    private Time transStartTime;
    private String aircraft;
    private Integer d0;
    private Integer d2;
    private Integer d3;
    private Integer d4;
    private Integer d5;
    private Integer d6;
    private String airways;
    private Integer enabled;

    @Builder
    public BaseFlight(String id, String createUser, String updateUser, Date createDate, Date updateDate, String flightNo, String startAirport, String endAirport, String transAirport, Time startTime, Time endTime, Time transArriveTime, Time transStartTime, String aircraft, Integer d0, Integer d2, Integer d3, Integer d4, Integer d5, Integer d6, String airways, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.flightNo = flightNo;
        this.startAirport = startAirport;
        this.endAirport = endAirport;
        this.transAirport = transAirport;
        this.startTime = startTime;
        this.endTime = endTime;
        this.transArriveTime = transArriveTime;
        this.transStartTime = transStartTime;
        this.d0 = d0;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
        this.airways = airways;
        this.enabled = enabled;
        this.aircraft = aircraft;
    }
}
