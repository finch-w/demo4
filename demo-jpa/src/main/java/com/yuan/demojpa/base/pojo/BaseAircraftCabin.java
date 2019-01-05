package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "baseAircraftCabin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseAircraftCabin extends BasePojo {
    private String aircraft;
    private String code;
    private Double height;
    private Double width;
    private Double length;
    private Double weight;
    private Double volume;
    private Integer enabled;
    private String type;

    @Builder
    public BaseAircraftCabin(String id, String createUser, String updateUser, Date createDate, Date updateDate, String aircraft, String code, Double height, Double width, Double length, Double weight, Double volume, Integer enabled, String type) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.aircraft = aircraft;
        this.code = code;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.volume = volume;
        this.enabled = enabled;
        this.type = type;
    }
}
