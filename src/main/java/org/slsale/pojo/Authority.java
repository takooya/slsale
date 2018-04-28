package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Authority extends Base {
    private Integer roleId;
    private Integer functionId;
    private Integer userTypeId;
    private Date creationTime;
    private String createdBy;

    public Authority() {
    }

    public Authority(Integer roleId, Integer functionId, Integer userTypeId, Date creationTime, String createdBy) {
        this.roleId = roleId;
        this.functionId = functionId;
        this.userTypeId = userTypeId;
        this.creationTime = creationTime;
        this.createdBy = createdBy;
    }
}
