package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsPack {
  private Integer id;
  private String goodsPackName;
  private String goodsPackCode;
  private Integer typeId;
  private String typeName;
  private double totalPrice;
  private Integer state;
  private String note;
  private Integer num;
  private String createdBy;
  private Date createTime;
  private Date lastUpdateTime;
}
