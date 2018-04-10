package org.slsale.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class User extends Base{
  private String loginCode;
  private String password;
  private String password2;
  private String userName;
  private String sex;
  private Date birthday;
  private String cardType;
  private String cardTypeName;
  private String idCard;
  private String country;
  private String mobile;
  private String email;
  private String userAddress;
  private String postCode;
  private Date createTime;
  private Integer referId;
  private String referCode;
  private Integer roleId;
  private String roleName;
  private String userType;
  private String userTypeName;
  private Integer isStart;
  private Date lastUpdateTime;
  private Date lastLoginTime;
  private String bankAccount;
  private String bankName;
  private String accountHolder;
  private String idCardPicPath;
  private String bankPicPath;
}
