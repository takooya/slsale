package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsInfo {
  private Integer id;
  private String goodsSn;
  private String goodsName;
  private String goodsFormat;
  private double marketPrice;
  private double realPrice;
  private Integer state;
  private String note;
  private Integer num;
  private String unit;
  private Date createTime;
  private Date lastUpdateTime;
  private String createdBy;
}
