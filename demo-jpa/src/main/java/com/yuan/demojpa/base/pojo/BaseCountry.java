package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BaseCountry extends BasePojo {
    private String name;
    private String region;

    @Builder
    public BaseCountry(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String region) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.region = region;
    }
}
