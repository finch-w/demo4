package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baseStorageLocation")
public class BaseStorageLocation extends BasePojo {
    private String code;
    private String name;
    private Integer enabled;

    @Builder
    public BaseStorageLocation(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.name = name;
        this.enabled = enabled;
    }
}
