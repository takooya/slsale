package org.slsale.pojo.user;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @Author takooya
 * @Description
 * @Date:created in 21:19 2018/4/8
 */
@Data
public class User {
    private String loginCode;
    private String password;
    private String password2;
    private String userName;
}
