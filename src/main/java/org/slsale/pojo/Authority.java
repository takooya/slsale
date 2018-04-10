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

}
