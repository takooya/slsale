package org.slsale.service.user;

import org.slsale.pojo.user.User;

/**
 * @Author takooya
 * @Description
 * @Date:created in 22:23 2018/4/8
 */
public interface UserService {
    /**  登录 */
    User getLoginUser(User user)throws Exception;
    /**  注册 */
    int addUser(User user)throws Exception;
}
