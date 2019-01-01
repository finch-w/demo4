package com.yuan.demomybatis2.commons.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePojo implements Serializable {
    @TableId(type = IdType.UUID, value = "id")
    private String id;
    @TableField(value = "createUser", fill = FieldFill.INSERT)
    private String createUser;
    @TableField(value = "updateUser", fill = FieldFill.UPDATE)
    private String updateUser;
    @TableField(value = "createDate", fill = FieldFill.INSERT)
    private Date createDate = new Date();
    @TableField(value = "updateDate", fill = FieldFill.UPDATE)
    private Date updateDate = new Date();
}
