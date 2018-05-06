package org.slsale.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Affiche extends Base {
  private String code;
  private String title;
  private String content;
  private String publisher;
  private Date publishTime;
  private Date startTime;
  private Date endTime;
}
