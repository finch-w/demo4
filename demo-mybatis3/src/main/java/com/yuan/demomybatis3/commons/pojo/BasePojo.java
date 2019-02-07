package com.yuan.demomybatis3.commons.pojo;


import com.gitee.hengboy.mybatis.enhance.common.annotation.Column;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Id;
import com.gitee.hengboy.mybatis.enhance.common.enums.KeyGeneratorTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePojo {
    @Id(generatorType = KeyGeneratorTypeEnum.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "createUser", updateable = false)
    private String createUser;
    @Column(name = "updateUser", insertable = false)
    private String updateUser;
    @Column(name = "createDate", updateable = false)
    private Date createDate;
    @Column(name = "updateDate", insertable = false)
    private Date updateDate;


}
