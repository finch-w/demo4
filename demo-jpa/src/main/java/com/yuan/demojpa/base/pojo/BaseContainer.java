package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "baseContainer")
@NoArgsConstructor
@AllArgsConstructor
public class BaseContainer extends BasePojo {
    private String code;
    private String name;
    private String type;
    private Integer enabled;

    @Builder
    public BaseContainer(String id, String createUser, String updateUser, Date createDate, Date updateDate, String code, String name, String type, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.code = code;
        this.name = name;
        this.type = type;
        this.enabled = enabled;
    }
}
