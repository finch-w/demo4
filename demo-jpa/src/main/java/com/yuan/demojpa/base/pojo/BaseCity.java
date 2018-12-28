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
public class BaseCity extends BasePojo {
    private String name;
    private String mergeName;
    private String countryId;
    private String parentCityId;
    private Integer level;

    @Builder
    public BaseCity(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String mergeName, String countryId, String parentCityId, Integer level) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.mergeName = mergeName;
        this.countryId = countryId;
        this.parentCityId = parentCityId;
        this.level = level;
    }
}
