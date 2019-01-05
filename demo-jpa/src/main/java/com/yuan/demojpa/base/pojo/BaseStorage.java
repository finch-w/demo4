package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "baseStorage")
public class BaseStorage extends BasePojo {
    private String code;
    private String name;
    private Integer enabled;

    @Builder
    public BaseStorage(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.name = name;
        this.enabled = enabled;
    }
}
