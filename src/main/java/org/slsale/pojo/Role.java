package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role extends Base {
  private String roleCode;
  private String roleName;
  private Date createDate;
  private Integer isStart;
  private String createdBy;
}
