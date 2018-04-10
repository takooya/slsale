package org.slsale.dao;

import org.slsale.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @Author takooya
 * @Description
 * @Date:created in 16:03 2018/4/9
 */
@Repository
public interface UserMapper {
    /**  登录 */
    User getLoginUser(User user)throws Exception;
    /**  判断用户是否存在 */
    int loginCodeIsExist(User user)throws Exception;
    int modifyUser(User user)throws Exception;
    /**  注册 */
    int addUser(User user)throws Exception;
}
