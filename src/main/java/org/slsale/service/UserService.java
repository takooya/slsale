package org.slsale.service;

import org.slsale.pojo.User;

/**
 * @Author takooya
 * @Description
 * @Date:created in 22:23 2018/4/8
 */
public interface UserService {
    /**  登录 */
    User getLoginUser(User user)throws Exception;
    /**  判断用户是否存在 */
    int loginCodeIsExist(User user)throws Exception;

    int modifyUser(User user)throws Exception;
    /**  注册 */
    int addUser(User user)throws Exception;
    /**  获得相应条件的用户数 */
    int count(User user)throws Exception;
}
