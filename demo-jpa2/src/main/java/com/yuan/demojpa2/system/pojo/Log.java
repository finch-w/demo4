package com.yuan.demojpa2.system.pojo;


import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log")
public class Log extends BasePojo {
    @Column(name = "operater")
    private String operator;//操作人
    @Column(name = "ipAddress")
    private String ipAddress;//IP地址
    @Column(name = "hostName")
    private String hostName;//主机名
    @Column(name = "operation")
    private String operation;//操作；
    @Column(name = "content")
    private String content;

    @Builder
    public Log(String id, String createUser, String updateUser, Date createDate, Date updateDate, String operater, String ipAddress, String hostName, String operation, String content) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.operator = operater;
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.operation = operation;
        this.content = content;
    }
}
