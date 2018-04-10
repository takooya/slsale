package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Function extends Base{
  private String functionCode;
  private String functionName;
  private String funcUrl;
  private Integer parentId;
  private Date creationTime;
  /**  为方便操作,产生的属性roleId,表中无此数据 */
  private Integer roleId;
}
